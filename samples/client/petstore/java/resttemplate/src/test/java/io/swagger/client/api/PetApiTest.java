/*
 * Swagger Petstore
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * OpenAPI spec version: 1.0.0
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import java.io.File;
import io.swagger.client.model.ModelApiResponse;
import io.swagger.client.model.Pet;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PetApi
 */
@Ignore
public class PetApiTest {

    private final PetApi api = new PetApi();

    
    /**
     * Add a new pet to the store
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addPetTest() {
        Pet pet = null;
        api.addPet(pet);

        // TODO: test validations
    }
    
    /**
     * Deletes a pet
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deletePetTest() {
        Long petId = null;
        String apiKey = null;
        api.deletePet(petId, apiKey);

        // TODO: test validations
    }
    
    /**
     * Finds Pets by status
     *
     * Multiple status values can be provided with comma separated strings
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findPetsByStatusTest() {
        List<String> status = null;
        List<Pet> response = api.findPetsByStatus(status);

        // TODO: test validations
    }
    
    /**
     * Finds Pets by tags
     *
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findPetsByTagsTest() {
        List<String> tags = null;
        List<Pet> response = api.findPetsByTags(tags);

        // TODO: test validations
    }
    
    /**
     * Find pet by ID
     *
     * Returns a single pet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getPetByIdTest() {
        Long petId = null;
        Pet response = api.getPetById(petId);

        // TODO: test validations
    }
    
    /**
     * Update an existing pet
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updatePetTest() {
        Pet pet = null;
        api.updatePet(pet);

        // TODO: test validations
    }
    
    /**
     * Updates a pet in the store with form data
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updatePetWithFormTest() {
        Long petId = null;
        String name = null;
        String status = null;
        api.updatePetWithForm(petId, name, status);

        // TODO: test validations
    }
    
    /**
     * uploads an image
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void uploadFileTest() {
        Long petId = null;
        String additionalMetadata = null;
        File file = null;
        ModelApiResponse response = api.uploadFile(petId, additionalMetadata, file);

        // TODO: test validations
    }
    
}
