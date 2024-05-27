package api.test;

import api.endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetTest extends BaseTest {

    @Test(priority = 1)
    public void testPostPet() {
        logger.info("Starting test: testPostPet");
        logger.info("Creating pet");
        Response response = PetEndpoints.createPet(petPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), petPayload.getName());
        Assert.assertEquals(response.jsonPath().getString("status"), petPayload.getStatus());
    }

    @Test(priority = 2)
    public void testGetPetById() {
        logger.info("Starting test: testGetPetById");
        Response response = PetEndpoints.readPet(this.petPayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), petPayload.getId());
        Assert.assertEquals(response.jsonPath().getString("name"), petPayload.getName());
        Assert.assertEquals(response.jsonPath().getString("status"), petPayload.getStatus());
    }

    @Test(priority = 3)
    public void testUpdatePet() {
        logger.info("Starting test: testUpdatePet");
        petPayload.setName(faker.animal().name());
        petPayload.setStatus("sold");

        Response response = PetEndpoints.updatePet(petPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);

        Response responseAfterUpdate = PetEndpoints.readPet(this.petPayload.getId());
        responseAfterUpdate.then().log().body();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        Assert.assertEquals(responseAfterUpdate.jsonPath().getString("name"), petPayload.getName());
        Assert.assertEquals(responseAfterUpdate.jsonPath().getString("status"), petPayload.getStatus());
    }

    @Test(priority = 4)
    public void testDeletePet() {
        logger.info("Starting test: testDeletePet");
        Response response = PetEndpoints.deletePet(this.petPayload.getId());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
