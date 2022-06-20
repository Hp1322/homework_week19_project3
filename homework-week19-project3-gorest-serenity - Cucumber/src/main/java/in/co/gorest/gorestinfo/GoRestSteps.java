package in.co.gorest.gorestinfo;

import in.co.gorest.constants.EndPoints;
import in.co.gorest.model.GoRestPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class GoRestSteps {

        @Step("Creating user with name : {0}, gender : {1}, email : {2} and status : {3}")
        public ValidatableResponse createUser(String name, String gender, String email, String status) {
            GoRestPojo goRestPojo = new GoRestPojo();
            goRestPojo.setName(name);
            goRestPojo.setGender(gender);
            goRestPojo.setEmail(email);
            goRestPojo.setStatus(status);

            return SerenityRest.given().log().all()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                    .body(goRestPojo)
                    .when()
                    .post()
                    .then();
        }
    @Step("Getting the user information with userID : {0}")
    public HashMap<String, Object> getUserInfoByID(int userID){
        HashMap<String, Object> userMap = SerenityRest.given().log().all().
                when()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userID", userID)
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return userMap;
    }
    @Step("Updating user with userID {0}, name : {1}, gender : {2}, email : {3} and status : {4}")
    public ValidatableResponse updateUser(int userID,String name, String gender, String email, String status) {
        GoRestPojo goRestPojo = new GoRestPojo();
        goRestPojo.setName(name);
        goRestPojo.setGender(gender);
        goRestPojo.setEmail(email);
        goRestPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .body(goRestPojo)
                .pathParam("userID", userID)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }
    @Step("Deleting user information with userID : {0}")
    public ValidatableResponse deleteUser(int userID){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Getting user information with userID: {0}")
    public ValidatableResponse getUserById(int userID){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
    @Step("Getting all users from application")
    public ValidatableResponse getAllUsers(){
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then();

    }
    }
