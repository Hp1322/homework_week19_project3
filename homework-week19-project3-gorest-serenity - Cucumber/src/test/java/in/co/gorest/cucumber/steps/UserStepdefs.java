package in.co.gorest.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.co.gorest.gorestinfo.GoRestSteps;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class UserStepdefs {

    static String name = "Priyala Shah" + TestUtils.getRandomValue();
    static String email = "priyala_shah" + TestUtils.getRandomValue() + "@nitzsche.com";
    static String gender = "male";
    static String status = "active";
    static int userID;
    static ValidatableResponse response;

    @Steps
    GoRestSteps goRestSteps;

    @Given("^I am on homepage of application of user$")
    public void iAmOnHomepageOfApplicationOfUser() {
    }

    @When("^I send Get request to list endpoint of user$")
    public void iSendGetRequestToListEndpointOfUser() {
        response = goRestSteps.getAllUsers();
    }

    @Then("^I must get back a valid status code (\\d+) of user$")
    public void iMustGetBackAValidStatusCodeOfUser(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);

    }

    @When("^I send Post request to list endpoint of user$")
    public void iSendPostRequestToListEndpointOfUser() {
        response = goRestSteps.createUser(name, gender, email, status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @When("^I send Put request to list endpoint of user$")
    public void iSendPutRequestToListEndpointOfUser() {

        name = name + "_updated";
        response = goRestSteps.updateUser(userID, name, gender, email, status).log().all().statusCode(200);
        HashMap<String, Object> userMap = goRestSteps.getUserInfoByID(userID);
        Assert.assertThat(userMap, hasValue(name));
    }

    @When("^I send Delete request to list endpoint of user$")
    public void iSendDeleteRequestToListEndpointOfUser() {
        response = goRestSteps.deleteUser(userID);
        response.assertThat().statusCode(204);
    }

    @And("^I validate if user is deleted$")
    public void iValidateIfUserIsDeleted() {
        response = goRestSteps.getUserById(userID);
        response.assertThat().statusCode(404);
    }

    @Then("^I validate total records 20$")
    public void iValidateTotalRecords () {
        response = goRestSteps.getAllUsers();
        List<?> records = response.log().all().extract().path("total");
        Assert.assertEquals(20, records.size());

    }



    @And("^I validate status of all Id$")
    public void iValidateStatusOfAllId() {
        String gender1 = response.log().all().extract().path("[1].status");
        Assert.assertEquals("inactive", gender1);
    }


    @And("^I validate name \"([^\"]*)\" of Id (\\d+)$")
    public void iValidateNameOfId(String name, int arg1){
        String name01 = response.log().all().extract().path("[3].name");
        Assert.assertEquals(name, name01);
    }

    @And("^I validate email \"([^\"]*)\" of Id (\\d+)$")
    public void iValidateEmailOfId(String expected, int arg1){
        String email = response.log().all().extract().path("[1].email");
        Assert.assertEquals(expected, email);
    }

    @And("^I validate gender \"([^\"]*)\" of Id (\\d+)$")
    public void iValidateGenderOfId(String expected, int arg1){
        String gender = response.log().all().extract().path("[4].gender");
        Assert.assertEquals(expected, gender);
    }
}
