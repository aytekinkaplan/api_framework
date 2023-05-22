package stepdefinitions;

import audience_management_test_data.Headers;
import base_url_set_up.AudienceManagementBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.Matchers;
import org.testng.Assert;
import pojo.ResponseTagAndTagGroupPojo;
import pojo.TagAndTagGroupPojo;
import pojo.TagAndTagGroupResponseDataPositive;
import utilities.ConfigReader;

import java.io.IOException;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static java.util.function.Predicate.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasValue;

public class TagGroupUpdateStepDefs extends AudienceManagementBaseURL {

    TagAndTagGroupPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    Response response;
    ObjectMapper obj;


    {
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","group","fourth","update");
    }


    @Given("user creates request body for tag group update with non existing organizer")
    public void user_creates_request_body_for_tag_group_update_with_non_existing_organizer() {
        requestBody = new TagAndTagGroupPojo(670,"05dd3509-e225-4e2a-92d8-605f841353cc","Ahmedi",
                "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
    }
    @When("user sends post request for tag group update with non existing organizer")
    public void user_sends_post_request_for_tag_group_update_with_non_existing_organizer() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        //we need to change tag from hooks class
        response.prettyPrint();
    }
    @Then("user validates organizer not found error for tag group update with {string} status code")
    public void user_validates_organizer_not_found_error_for_tag_group_update_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"),ConfigReader.getProperty("concept"),
                null,null);

        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        System.out.println(actualData);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with non existing tag group")
    public void user_creates_request_body_with_non_existing_tag_group() {
        requestBody = new TagAndTagGroupPojo(671,"05dd3509-e225-4e2a-92d8-605f841354cc","blossom",
                "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
    }
    @When("When user sends post request with non existing tag group")
    public void when_user_sends_post_request_with_non_existing_tag_group() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates tag group not found error with {string} status code")
    public void user_validates_tag_group_not_found_error_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_non_exist"),
                ConfigReader.getProperty("concept_tag_group_non_existing"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        System.out.println(actualData);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body for tag group update which is not linked to organizer")
    public void user_creates_request_body_for_tag_group_update_which_is_not_linked_to_organizer() {
        requestBody = new TagAndTagGroupPojo(132,"05dd3509-e225-4e2a-92d8-605f841353cc","blossom",
                "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
    }
    @When("When user sends post request with tag group update request body is not linked to organizer")
    public void when_user_sends_post_request_with_tag_group_update_request_body_is_not_linked_to_organizer() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates tag group is not linked to organizer error with {string} status code for update ep")
    public void user_validates_tag_group_is_not_linked_to_organizer_error_with_status_code_for_update_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_not_linked"),
                ConfigReader.getProperty("concept_tag_group_not_linked"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body for with archived tag group")
    public void user_creates_request_body_for_with_archived_tag_group() {
        requestBody = new TagAndTagGroupPojo(370,"17a7457e-0e8d-4877-9b41-8018c38498b8","blossom",
                null,null,null,null);
    }
    @When("user sends post request with archived tag group")
    public void user_sends_post_request_with_archived_tag_group() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group is archived error with {string} status code for update ep")
    public void user_validates_tag_group_is_archived_error_with_status_code_for_update_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_archived_group"),
                ConfigReader.getProperty("concept_tag_group_non_existing"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with existing name for organizer")
    public void user_creates_request_body_with_existing_name_for_organizer() {
        requestBody = new TagAndTagGroupPojo(671,"99735555-695a-4f26-9bf5-2f2402afd226","Pheobie",
                "c5d0e57e-8dd4-425e-81b4-ef67a01dc7f5",null,null,null);
    }
    @When("user sends post request for tag group update with existing name")
    public void user_sends_post_request_for_tag_group_update_with_existing_name() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates name already exist error for update ep with {string} status code")
    public void user_validates_name_already_exist_error_for_update_ep_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_name_exists_group"),
                ConfigReader.getProperty("concept"), ConfigReader.getProperty("message"),null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getMessage(),responseBody.getMessage());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with archived name for organizer")
    public void user_creates_request_body_with_archived_name_for_organizer() {
        requestBody = new TagAndTagGroupPojo(671,"05dd3509-e225-4e2a-92d8-605f841353cc","Bogdanovic",
                "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
    }
    @When("user sends post request for tag group update with archived name")
    public void user_sends_post_request_for_tag_group_update_with_archived_name() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates tag group update with empty response with {string} status code")
    public void user_validates_tag_group_update_with_empty_response_with_status_code(String status_code) throws IOException {
        requestBody = new TagAndTagGroupPojo(671,"05dd3509-e225-4e2a-92d8-605f841353cc","Bogdanovic",
               "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        String rspnseBody = response.getBody().asString();
        Assert.assertTrue(rspnseBody.isEmpty() || rspnseBody.isBlank() || rspnseBody.equals("null"));
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);

    }

    @Given("user creates request body for tag group update without parent_id")
    public void user_creates_request_body_for_tag_group_update_without_parent_id() {

    }
    @When("user sends post request for tag group update without parent_id")
    public void user_sends_post_request_for_tag_group_update_without_parent_id() {

    }

    @Given("user creates request body with same parent_id and tag_group_id")
    public void user_creates_request_body_with_same_parent_id_and_tag_group_id() {


    }
    @When("user sends post request for tag group update with same parent_id and tag_group_id")
    public void user_sends_post_request_for_tag_group_update_with_same_parent_id_and_tag_group_id() {

    }
    @Then("user validates parent_id and id can not be same error with {string} status code")
    public void user_validates_parent_id_and_id_can_not_be_same_error_with_status_code(String status_code) {

    }

    @Given("user creates request body with non existing parent_id for uppate ep")
    public void user_creates_request_body_with_non_existing_parent_id_for_uppate_ep() {

    }
    @When("user sends post request for tag group update with non existing parent_id")
    public void user_sends_post_request_for_tag_group_update_with_non_existing_parent_id() {

    }
    @Then("user validates parent group not found error with {string} status code")
    public void user_validates_parent_group_not_found_error_with_status_code(String status_code) {

    }

    @Given("user creates request body with parent_id which has not link to organizer for uppate ep")
    public void user_creates_request_body_with_parent_id_which_has_not_link_to_organizer_for_uppate_ep() {

    }
    @When("user sends post request for tag group update with parent_id which has not link to organizer")
    public void user_sends_post_request_for_tag_group_update_with_parent_id_which_has_not_link_to_organizer() {

    }
    @Then("user validates parent group is not link to organizer error with {string} status code for update ep")
    public void user_validates_parent_group_is_not_link_to_organizer_error_with_status_code_for_update_ep(String status_code) {

    }

    @Given("user creates request body for with archived parent group for update ep")
    public void user_creates_request_body_for_with_archived_parent_group_for_update_ep() {

    }
    @When("user sends post request with archived parent group for update ep")
    public void user_sends_post_request_with_archived_parent_group_for_update_ep() {

    }
    @Then("user validates parent group is archived error with {string} status code for update ep")
    public void user_validates_parent_group_is_archived_error_with_status_code_for_update_ep(String status_code) {

    }

    @Given("user creates request body for update ep")
    public void user_creates_request_body_for_update_ep() {

    }
    @When("user sends post request for tag group update")
    public void user_sends_post_request_for_tag_group_update() {

    }



}
