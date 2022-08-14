package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.*;

public class StepDefinition {
    Response response;

    @Given("Post AccountHash API")
    public void setupEndPoint() {
        baseURI = "http://localhost:8032/";
        basePath = "sha256-hash/";
    }

    @When("Provide Valid AccountNumber")
    public void sendRequest() {
        response = (Response) given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("accountHash", 10000001)
                .post("/{accountHash}").
                then()
                .statusCode(200).extract();
    }


    @When("Provide Invalid Path")
    public void sendRequestInvalid() {
        response = (Response) given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("accountHash", 10000001)
                .post("http://localhost:8032/shass256-hash/{accountHash}").
                then()
                .statusCode(404).extract();
    }

    @And("response contains AccountHashNumber equals {string}")
    public void responseContainsAccountHashNumberEquals(String arg0) {
        if (response.statusCode() == 404)
            Assertions.assertEquals(arg0, "Not_Found");
        else if (response.statusCode() == 200)
            Assertions.assertEquals(arg0, "IsPosted");
    }

    @Then("Status_code equals {int}")
    public void status_codeEquals(int statusCode) {
        if (statusCode == 200)
            Assertions.assertEquals(statusCode, response.getStatusCode());
        else if (statusCode == 400)
            Assertions.assertEquals(statusCode, response.getStatusCode());

    }
}
