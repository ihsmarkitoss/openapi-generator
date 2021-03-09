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

import io.swagger.models.Path;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.XML;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.codegen.*;
import org.openapitools.codegen.meta.features.*;
import org.openapitools.codegen.templating.mustache.OnChangeLambda;
import org.openapitools.codegen.utils.ModelUtils;
import org.openapitools.codegen.utils.URLPathUtils;
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

import static org.openapitools.codegen.utils.StringUtils.camelize;
import static org.openapitools.codegen.utils.StringUtils.underscore;


public class JavaAWSGenerator extends DefaultCodegen implements CodegenConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(JavaAWSGenerator.class);

    public static final String OUTPUT_NAME = "outputFile";
    public final static String AWSExtensionOperation = "x-amazon-apigateway-integration";
    public final static String AWSPassthrougBehavior = "passthroughBehavior";
    public final static String AWSType = "type";
    public final static String AWSUri = "uri";
    public final static String AWSHttpMethod = "httpMethod";
    public final static String AWSTimeoutInMillis = "timeoutInMillis";
    public final static String AWSContentHandling = "contentHandling";
    public final static String AWSResponses = "responses";

    enum TargetInfrastructureLanguage {INFRASTRUCTURE_LANGUAGE_SAM, INFRASTRUCTURE_LANGUAGE_TERRAFORM};

    protected TargetInfrastructureLanguage targetInfrastructureLanguage = TargetInfrastructureLanguage.INFRASTRUCTURE_LANGUAGE_TERRAFORM;
    protected String awsContentHandling = "CONVERT_TO_TEXT";
    protected String awsType = "aws_proxy";
    protected Integer awsTimeoutInMillis = 29000;
    protected String awsPassthroughBehavior = "when_no_match";

    protected String outputFile = "openapi-aws/openapi.yaml";

    public JavaAWSGenerator() {
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


    @Override
    public void processOpenAPI(OpenAPI openAPI) {

        //openAPI.addExtension("x-amazon-apigateway-gateway-responses", "This is a test");
        Paths paths = openAPI.getPaths();
        for (PathItem path : paths.values()) {
            Map<PathItem.HttpMethod, Operation> operations = path.readOperationsMap();
            for (Map.Entry<PathItem.HttpMethod, Operation> operationsEntry : operations.entrySet()) {
                Operation operation = operationsEntry.getValue();
                PathItem.HttpMethod httpMethod = operationsEntry.getKey();
                Map<String,Object> extension = new HashMap<String,Object>();

                extension.put(AWSUri, getAWSARNUri(operation.getOperationId(),this.targetInfrastructureLanguage));
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

    private String getAWSARNUri(String lambdaName, TargetInfrastructureLanguage targetInfrastructureLanguage) {
        String arnUri = null;
        switch (targetInfrastructureLanguage) {
            case INFRASTRUCTURE_LANGUAGE_SAM:
                arnUri = "{\"Fn::Sub\": \"arn:aws:apigateway:${AWS::Region}:lambda:path/2015-03-31/functions/${\"" + lambdaName + "\"Function.Arn}/invocations\"}";
                break;
            case INFRASTRUCTURE_LANGUAGE_TERRAFORM:
                arnUri = "arn:aws:apigateway:${region}:lambda:path/2015-03-31/functions/${" + lambdaName + "}/invocations";
                break;
            default:
                // ICODE Error handling goes here.
        }
        return arnUri;
    }
}
