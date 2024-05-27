package api.endpoints;

import api.payload.Pet;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ResourceBundle;

public class PetEndpoints {

    private static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response createPet(Pet pet) {
        String postUrl = getURL().getString("post_pet_url");
        return RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(pet)
                .post(postUrl);
    }

    public static Response readPet(long petId) {
        String getUrl = getURL().getString("get_pet_url").replace("{petId}", String.valueOf(petId));
        return RestAssured.given()
                .accept("application/json")
                .get(getUrl);
    }

    public static Response updatePet(Pet pet) {
        String updateUrl = getURL().getString("update_pet_url");
        return RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(pet)
                .put(updateUrl);
    }

    public static Response deletePet(long petId) {
        String deleteUrl = getURL().getString("delete_pet_url").replace("{petId}", String.valueOf(petId));
        return RestAssured.given()
                .accept("application/json")
                .delete(deleteUrl);
    }
}
