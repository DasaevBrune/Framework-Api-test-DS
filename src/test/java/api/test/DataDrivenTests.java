package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class DataDrivenTests extends BaseTest {

    private static final Logger logger = Logger.getLogger(DataDrivenTests.class.getName());

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String firstName, String lastName, String userEmail, String password, String phone) {
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndpoints.createUser(userPayload);

        logger.info("Response status code for createUser: " + response.getStatusCode());
        logger.info("Response body for createUser: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {
        Response response = UserEndpoints.deleteUser(userName);

        logger.info("Response status code for deleteUser: " + response.getStatusCode());
        logger.info("Response body for deleteUser: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().asString(), "{\"code\":200,\"type\":\"unknown\",\"message\":\"" + userName + "\"}");
    }
}
