package stepsDefs;

import io.cucumber.java.en.Then;
import sharedStepContext.SharedStepContext;

public class ResponseValidationStepDefs {



    private final SharedStepContext sharedStepContext;

    public ResponseValidationStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
    }

    @Then("API Response should have HTTP Status Code {int}")
    public void apiResponseShouldHaveHTTPStatusCode(int statusCode) {
        this.sharedStepContext.apiResponse.then().assertThat().statusCode(statusCode);
    }
}
