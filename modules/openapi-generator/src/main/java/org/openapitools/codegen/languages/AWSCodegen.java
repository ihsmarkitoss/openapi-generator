/*
 * Copyright 2018 OpenAPI-Generator Contributors (https://openapi-generator.tech)
 * Copyright 2018 SmartBear Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openapitools.codegen.languages;

import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.parser.util.SchemaTypeUtil;
import org.openapitools.codegen.*;
import org.openapitools.codegen.meta.features.*;
import org.openapitools.codegen.templating.mustache.OnChangeLambda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap.Builder;
import com.samskivert.mustache.Mustache.Lambda;

import io.swagger.v3.oas.models.Operation;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * TODO
 * - Add an option for the lambda language -e.g. javascript
 * - Add sub-option for the lambda language runtime - node12 or python38
 * - Add options to control generation of lambda code.
 * - Add option to override existing lambda code.
 * - Add option to pass in lambda layers information - not sure how to do this.
 * - Add option to control generation of infrastructure code
 * - Add option to control generation of decorated spec file.
 * - Add option to provide SAM template file to decorate.
 * - Terraform: Create lambda variables file.
 * - Terraform: Documentation on how to use the generated terraform files.
 * - SAM: Decorate the provided SAM template file.
 *
 * - Complete the Spec annotation
 *   - Needs response option
 *   - check other annotations needed agains AWS documentation.
 */

public class AWSCodegen extends DefaultCodegen implements CodegenConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(AWSCodegen.class);

    public static final String INFRASTRUCTURE_FRAMEWORK = "infrastructureFramework";
    public static final String INFRASTRUCTURE_FRAMEWORK_DESC = "The target infrastructure framework version.";


    public static final String OUTPUT_NAME = "outputFile";
    public final static String AWSExtensionOperation = "x-amazon-apigateway-integration";
    public final static String AWSPassthrougBehavior = "passthroughBehavior";
    public final static String AWSType = "type";
    public final static String AWSUri = "uri";
    public final static String AWSHttpMethod = "httpMethod";
    public final static String AWSTimeoutInMillis = "timeoutInMillis";
    public final static String AWSContentHandling = "contentHandling";
    public final static String AWSResponses = "responses";

    protected String awsContentHandling = "CONVERT_TO_TEXT";
    protected String awsType = "aws_proxy";
    protected Integer awsTimeoutInMillis = 29000;
    protected String awsPassthroughBehavior = "when_no_match";

    // Project Variable, determined from target framework. Not intended to be user-settable.
    private static AWSCodegen.InfrastructureFramework defaultInfrastructureFramework = InfrastructureFramework.TERRAFORM;
    protected String infrastructureFrameworkString = defaultInfrastructureFramework.name;
    protected InfrastructureFramework infrastructureFramework = defaultInfrastructureFramework;
    private CliOption infrastructureFrameworkCLI = new CliOption(AWSCodegen.INFRASTRUCTURE_FRAMEWORK,AWSCodegen.INFRASTRUCTURE_FRAMEWORK_DESC);

    protected String outputFile = "openapi-aws/openapi.yaml";

    public AWSCodegen() {
        super();

        modifyFeatureSet(features -> features
                .documentationFeatures(EnumSet.allOf(DocumentationFeature.class))
                .dataTypeFeatures(EnumSet.allOf(DataTypeFeature.class))
                .wireFormatFeatures(EnumSet.allOf(WireFormatFeature.class))
                .securityFeatures(EnumSet.allOf(SecurityFeature.class))
                .globalFeatures(EnumSet.allOf(GlobalFeature.class))
                .parameterFeatures(EnumSet.allOf(ParameterFeature.class))
                .schemaSupportFeatures(EnumSet.allOf(SchemaSupportFeature.class))
        );

        embeddedTemplateDir = templateDir = "JavaAWS";
        outputFolder = "generated-code/JavaAWS";
        cliOptions.add(CliOption.newString(OUTPUT_NAME, "Output filename").defaultValue(outputFile));
        supportingFiles.add(new SupportingFile("README.md", "", "README.md"));

        for (AWSCodegen.InfrastructureFramework infrastructureFrameworkLoop : AWSCodegen.InfrastructureFramework.values()) {
            infrastructureFrameworkCLI.addEnum(infrastructureFrameworkLoop.name, infrastructureFrameworkLoop.description);
        }
        infrastructureFrameworkCLI.defaultValue(AWSCodegen.defaultInfrastructureFramework.name);
        infrastructureFrameworkCLI.setOptValue(AWSCodegen.defaultInfrastructureFramework.name);
        cliOptions.add(infrastructureFrameworkCLI);

    }

    @Override
    public CodegenType getTag() {
        return CodegenType.DOCUMENTATION;
    }

    @Override
    public String getName() {
        return "openapi-aws";
    }

    @Override
    public String getHelp() {
        return "Creates a static openapi.yaml file (OpenAPI spec v3) suitable for use with AWS API Gateway.";
    }

    @Override
    public void processOpts() {
        super.processOpts();
        if (additionalProperties.containsKey(OUTPUT_NAME)) {
            outputFile = additionalProperties.get(OUTPUT_NAME).toString();
        }
        LOGGER.info("Output file [outputFile={}]", outputFile);
        supportingFiles.add(new SupportingFile("openapi.mustache", outputFile));

        String infrastructureFrameworkName = (String) additionalProperties.getOrDefault(AWSCodegen.INFRASTRUCTURE_FRAMEWORK, AWSCodegen.defaultInfrastructureFramework.name);
        LOGGER.info(AWSCodegen.INFRASTRUCTURE_FRAMEWORK +": returned value is {}",  infrastructureFrameworkName);
        boolean strategyMatched = false;
        this.infrastructureFramework = AWSCodegen.InfrastructureFramework.findByName(infrastructureFrameworkName);
        LOGGER.info(AWSCodegen.INFRASTRUCTURE_FRAMEWORK +": value is {}",  this.infrastructureFramework.name);

        setCliOption(infrastructureFrameworkCLI);

        // Set the template directory based on the infrastructure type.

    }

    @Override
    protected Builder<String, Lambda> addMustacheLambdas() {
        return super.addMustacheLambdas()
                .put("onchange", new OnChangeLambda());
    }

    /**
     * Group operations by resourcePath so that operations with same path and
     * different http method can be rendered one after the other.
     */
    @Override
    public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation
            co, Map<String, List<CodegenOperation>> operations) {
        List<CodegenOperation> opList = operations.computeIfAbsent(resourcePath,
                k -> new ArrayList<>());
        opList.add(co);
    }

    @Override
    public Map<String, Object> postProcessSupportingFileData(Map<String, Object> objs) {
        generateYAMLSpecFile(objs);
        return super.postProcessSupportingFileData(objs);
    }

    @Override
    public String escapeQuotationMark(String input) {
        // just return the original string
        return input;
    }

    @Override
    public String escapeUnsafeCharacters(String input) {
        // just return the original string
        return input;
    }

    /**
     * Add in the AWS API Gateway Specific tags
     * They should look like
     *
     *       x-amazon-apigateway-integration:
     *         uri: arn:aws:apigateway:${region}:lambda:path/2015-03-31/functions/${GetIntegrations}/invocations
     *         responses:
     *           default:
     *             statusCode: "200"
     *         passthroughBehavior: when_no_match
     *         httpMethod: POST
     *         timeoutInMillis: 29000
     *         contentHandling: CONVERT_TO_TEXT
     *         type: aws_proxy
     *
     * @param openAPI - The spec to be processed.
     */

    private void setCliOption(CliOption cliOption) throws IllegalArgumentException {
        if (additionalProperties.containsKey(cliOption.getOpt())) {
            // TODO Hack - not sure why the empty strings become boolean.
            Object obj = additionalProperties.get(cliOption.getOpt());
            if (!SchemaTypeUtil.BOOLEAN_TYPE.equals(cliOption.getType())) {
                if (obj instanceof Boolean) {
                    obj = "";
                    additionalProperties.put(cliOption.getOpt(), obj);
                }
            }
            cliOption.setOptValue(obj.toString());
        } else {
            additionalProperties.put(cliOption.getOpt(), cliOption.getOptValue());
        }
        if (cliOption.getOptValue() == null) {
            cliOption.setOptValue(cliOption.getDefault());
            throw new IllegalArgumentException(cliOption.getOpt() + ": Invalid value '" + additionalProperties.get(cliOption.getOpt()).toString() + "'" +
                    ". " + cliOption.getDescription());
        }
    }


    @Override
    public void processOpenAPI(OpenAPI openAPI) {

        // ICODE -> Check if the AWS vendor extensions are already present and if they should be replaced, augumented or skipped when present.
        //openAPI.addExtension("x-amazon-apigateway-gateway-responses", "This is a test");
        Paths paths = openAPI.getPaths();
        for (PathItem path : paths.values()) {
            Map<PathItem.HttpMethod, Operation> operations = path.readOperationsMap();
            for (Map.Entry<PathItem.HttpMethod, Operation> operationsEntry : operations.entrySet()) {
                Operation operation = operationsEntry.getValue();
                PathItem.HttpMethod httpMethod = operationsEntry.getKey();
                Map<String,Object> extension = new HashMap<String,Object>();

                extension.put(AWSUri, getAWSARNUri(operation.getOperationId()));
                extension.put(AWSHttpMethod, httpMethod);
                extension.put(AWSPassthrougBehavior, awsPassthroughBehavior);
                extension.put(AWSTimeoutInMillis, awsTimeoutInMillis);
                extension.put(AWSType, awsType);
                extension.put(AWSContentHandling, awsContentHandling);

                // Build the response structure:
                // ICODE -> need to review the whole responses section.
                Map<String,Object> responses = new HashMap<String,Object>();
                ApiResponses apiResponses = operation.getResponses();
                for (Map.Entry<String, ApiResponse> responsesEntry : apiResponses.entrySet()) {
                    ApiResponse apiResponse = responsesEntry.getValue();
                    String key = responsesEntry.getKey();
                    Map<String,Object> response = new HashMap<String,Object>();
                    response.put("Key:", key);
                    response.put("value:", apiResponse.getDescription());
                    responses.put(key,response);
                }
                //extension.put(AWSResponses, responses);

                operation.addExtension(AWSExtensionOperation, extension);
            }
        }
    }

    private String getAWSARNUri(String lambdaName) {
        String arnUri = null;
        LOGGER.info("Generating for {}", this.infrastructureFramework);
        switch (this.infrastructureFramework) {
            case TERRAFORM:
                LOGGER.info("Found Tf {}", this.infrastructureFramework);
                arnUri = "arn:aws:apigateway:${region}:lambda:path/2015-03-31/functions/${" + lambdaName + "}/invocations";
                break;
            case CLOUDFORMATION:
            case SAM:
                LOGGER.info("Found CFN {}", this.infrastructureFramework);
                arnUri = "{\"Fn::Sub\": \"arn:aws:apigateway:${AWS::Region}:lambda:path/2015-03-31/functions/${\"" + lambdaName + "\"Function.Arn}/invocations\"}";
                break;
            default:
                // ICODE Error handling goes here.
        }
        return arnUri;
    }




    @SuppressWarnings("Duplicates")
    private enum  InfrastructureFramework {
        TERRAFORM("Terraform", "HashiCorp Terraform tool and HCL language."),
        CLOUDFORMATION("CloudFormation", "AWS CloudFormation infrastructure definition language."),
        SAM("SAM", "AWS Serverless Application Model infrastructure definition language.");
        protected String name;
        protected String description;

        InfrastructureFramework(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public static InfrastructureFramework findByName(String _name) {
            InfrastructureFramework infrastructureFramework = null;
            for (InfrastructureFramework val : InfrastructureFramework.values()) {
                if (val.name.equals(_name)) {
                    infrastructureFramework = val;
                    break;
                }
            }
            return infrastructureFramework;
        }
    }
}
