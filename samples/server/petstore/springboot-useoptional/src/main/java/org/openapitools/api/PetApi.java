/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.4.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.ModelApiResponse;
import org.openapitools.model.Pet;
import org.springframework.core.io.Resource;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "pet", description = "the pet API")
public interface PetApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Add a new pet to the store", nickname = "addPet", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = {
            @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @AuthorizationScope(scope = "read:pets", description = "read your pets")
            })
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/pet",
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> addPet(@ApiParam(value = "Pet object that needs to be added to the store" ,required=true )  @Valid @RequestBody Pet pet) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Deletes a pet", nickname = "deletePet", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = {
            @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @AuthorizationScope(scope = "read:pets", description = "read your pets")
            })
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid pet value") })
    @RequestMapping(value = "/pet/{petId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deletePet(@ApiParam(value = "Pet id to delete",required=true) @PathVariable("petId") Long petId,@ApiParam(value = "" ) @RequestHeader(value="api_key", required=false) Optional<String> apiKey) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Finds Pets by status", nickname = "findPetsByStatus", notes = "Multiple status values can be provided with comma separated strings", response = Pet.class, responseContainer = "List", authorizations = {
        @Authorization(value = "petstore_auth", scopes = {
            @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @AuthorizationScope(scope = "read:pets", description = "read your pets")
            })
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Pet.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value") })
    @RequestMapping(value = "/pet/findByStatus",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Pet>> findPetsByStatus(@NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "available, pending, sold", defaultValue = "new ArrayList<>()") @Valid @RequestParam(value = "status", required = true, defaultValue="new ArrayList<>()") List<String> status) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],  \"name\" : \"doggie\",  \"id\" : 0,  \"category\" : {    \"name\" : \"default-name\",    \"id\" : 6  },  \"tags\" : [ {    \"name\" : \"name\",    \"id\" : 1  }, {    \"name\" : \"name\",    \"id\" : 1  } ],  \"status\" : \"available\"}");
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    ApiUtil.setExampleResponse(request, "application/xml", "<Pet>  <id>123456789</id>  <name>doggie</name>  <photoUrls>    <photoUrls>aeiou</photoUrls>  </photoUrls>  <tags>  </tags>  <status>aeiou</status></Pet>");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Finds Pets by tags", nickname = "findPetsByTags", notes = "Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.", response = Pet.class, responseContainer = "List", authorizations = {
        @Authorization(value = "petstore_auth", scopes = {
            @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @AuthorizationScope(scope = "read:pets", description = "read your pets")
            })
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Pet.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/pet/findByTags",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Pet>> findPetsByTags(@NotNull @ApiParam(value = "Tags to filter by", required = true, defaultValue = "new ArrayList<>()") @Valid @RequestParam(value = "tags", required = true, defaultValue="new ArrayList<>()") List<String> tags) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],  \"name\" : \"doggie\",  \"id\" : 0,  \"category\" : {    \"name\" : \"default-name\",    \"id\" : 6  },  \"tags\" : [ {    \"name\" : \"name\",    \"id\" : 1  }, {    \"name\" : \"name\",    \"id\" : 1  } ],  \"status\" : \"available\"}");
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    ApiUtil.setExampleResponse(request, "application/xml", "<Pet>  <id>123456789</id>  <name>doggie</name>  <photoUrls>    <photoUrls>aeiou</photoUrls>  </photoUrls>  <tags>  </tags>  <status>aeiou</status></Pet>");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Find pet by ID", nickname = "getPetById", notes = "Returns a single pet", response = Pet.class, authorizations = {
        @Authorization(value = "api_key")
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Pet.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Pet not found") })
    @RequestMapping(value = "/pet/{petId}",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Pet> getPetById(@ApiParam(value = "ID of pet to return",required=true) @PathVariable("petId") Long petId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],  \"name\" : \"doggie\",  \"id\" : 0,  \"category\" : {    \"name\" : \"default-name\",    \"id\" : 6  },  \"tags\" : [ {    \"name\" : \"name\",    \"id\" : 1  }, {    \"name\" : \"name\",    \"id\" : 1  } ],  \"status\" : \"available\"}");
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    ApiUtil.setExampleResponse(request, "application/xml", "<Pet>  <id>123456789</id>  <name>doggie</name>  <photoUrls>    <photoUrls>aeiou</photoUrls>  </photoUrls>  <tags>  </tags>  <status>aeiou</status></Pet>");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Update an existing pet", nickname = "updatePet", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = {
            @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @AuthorizationScope(scope = "read:pets", description = "read your pets")
            })
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Pet not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/pet",
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.PUT)
    default ResponseEntity<Void> updatePet(@ApiParam(value = "Pet object that needs to be added to the store" ,required=true )  @Valid @RequestBody Pet pet) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Updates a pet in the store with form data", nickname = "updatePetWithForm", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = {
            @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @AuthorizationScope(scope = "read:pets", description = "read your pets")
            })
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/pet/{petId}",
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> updatePetWithForm(@ApiParam(value = "ID of pet that needs to be updated",required=true) @PathVariable("petId") Long petId,@ApiParam(value = "Updated name of the pet") @RequestParam(value="name", required=false)  String name,@ApiParam(value = "Updated status of the pet") @RequestParam(value="status", required=false)  String status) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "uploads an image", nickname = "uploadFile", notes = "", response = ModelApiResponse.class, authorizations = {
        @Authorization(value = "petstore_auth", scopes = {
            @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
            @AuthorizationScope(scope = "read:pets", description = "read your pets")
            })
    }, tags={ "pet", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
    @RequestMapping(value = "/pet/{petId}/uploadImage",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    default ResponseEntity<ModelApiResponse> uploadFile(@ApiParam(value = "ID of pet to update",required=true) @PathVariable("petId") Long petId,@ApiParam(value = "Additional data to pass to server") @RequestParam(value="additionalMetadata", required=false)  String additionalMetadata,@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"code\" : 0,  \"type\" : \"type\",  \"message\" : \"message\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
