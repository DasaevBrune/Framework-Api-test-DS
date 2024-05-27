package api.test;

import api.endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("Starting test: testPostUser");
        logger.info("Creating user with ID: " + userPayload.getId());
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
        Assert.assertEquals(response.jsonPath().getString("message"), String.valueOf(userPayload.getId()));
    }

    @Test(priority=2)
    public void testGetUserByName() {
        logger.info("Starting test: testGetUserByName");
        logger.info("Fetching user with username: " + userPayload.getUsername());
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), userPayload.getId());
        Assert.assertEquals(response.jsonPath().getString("username"), userPayload.getUsername());
        Assert.assertEquals(response.jsonPath().getString("firstName"), userPayload.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), userPayload.getLastName());
        Assert.assertEquals(response.jsonPath().getString("email"), userPayload.getEmail());
        Assert.assertEquals(response.jsonPath().getString("password"), userPayload.getPassword());
        Assert.assertEquals(response.jsonPath().getString("phone"), userPayload.getPhone());
        Assert.assertEquals(response.jsonPath().getInt("userStatus"), userPayload.getUserStatus());
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        logger.info("Starting test: testUpdateUserByName");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);

        Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().body();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        Assert.assertEquals(responseAfterUpdate.jsonPath().getString("firstName"), userPayload.getFirstName());
        Assert.assertEquals(responseAfterUpdate.jsonPath().getString("lastName"), userPayload.getLastName());
        Assert.assertEquals(responseAfterUpdate.jsonPath().getString("email"), userPayload.getEmail());
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        logger.info("Starting test: testDeleteUserByName");
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void testGetUserWithInvalidName() {
        String invalidUsername = "invalidUsername";
        logger.info("Attempting to get user with invalid username: " + invalidUsername);

        Response response = UserEndpoints.readUser(invalidUsername);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getInt("code"), 1);
        Assert.assertEquals(response.jsonPath().getString("type"), "error");
        Assert.assertEquals(response.jsonPath().getString("message"), "User not found");
    }
}
