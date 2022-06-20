package in.co.gorest.gorestinfo;

import in.co.gorest.testbase.TestBase;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class GoRestDataDrivenTest extends TestBase {

    static String name = "Priyala Shah" + TestUtils.getRandomValue();
    static String email = "priyala_shah" + TestUtils.getRandomValue() + "@nitzsche.com";
    static String gender = "male";
    static String status = "active";
    static int userID;

    @Steps
    GoRestSteps goRestSteps;

    @Title("This will create new user")
    @Test
    public void creatingUser() {
        ValidatableResponse response = goRestSteps.createUser(name, gender, email, status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);

    }

    @Title("Verify if the user was added to the list")
      @Test
    public void verifyUserAddedToList() {
        HashMap<String, Object> userMap = goRestSteps.getUserInfoByID(userID);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println("user map : " + userMap);

    }

    @Title("Update the user information and verify updated information")
    @Test
    public void updateUserAndVerify() {

        name = name + "_updated";
        goRestSteps.updateUser(userID, name, gender, email, status).log().all().statusCode(200);
        HashMap<String, Object> userMap = goRestSteps.getUserInfoByID(userID);
          Assert.assertThat(userMap, hasValue(name));
    }

    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void deleteUserAndVerify() {
        goRestSteps.deleteUser(userID).statusCode(204);
        goRestSteps.getUserById(userID).statusCode(404);
    }


}
