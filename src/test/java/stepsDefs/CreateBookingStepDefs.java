package stepsDefs;

import apis.CreateBookingApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import sharedStepContext.SharedStepContext;
import util.ApiRequestHelper;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class CreateBookingStepDefs {

    private final SharedStepContext sharedStepContext;
    private final CreateBookingApi createBookingApi;
    private Map<String,Object> requestPayload;
    private  Response createBookingApiResponse;

    public CreateBookingStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.createBookingApi = new CreateBookingApi();
    }

    @Given("I have a valid request for creating booking with following params")
    public void iHaveAValidRequestForCreatingBookingWithFollowingParams(List<Map<String,String>> requestDataList) {

        Map<String,String> requestDataMap  = requestDataList.getFirst();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        int checkInPlusDays = Integer.parseInt(requestDataMap.get("checkInPlusDays"));
        int checkOutPlusDays = Integer.parseInt(requestDataMap.get("checkoutPlusDays"));
        String checkInDate =  TestDataHelper.getFutureDate(checkInPlusDays,dateFormatter);
        String checkOutDate =  TestDataHelper.getFutureDate(checkOutPlusDays,dateFormatter);

        this.requestPayload = ApiRequestHelper.createBookingPayload(
                requestDataMap.get("firstName"),
                requestDataMap.get("lastName"),
                Integer.parseInt(requestDataMap.get("totalPrice")),
                Boolean.parseBoolean(requestDataMap.get("depositPaid")),
                checkInDate,
                checkOutDate,
                requestDataMap.get("additionalneeds")
                );


    }


    @Given("I have a valid request for creating booking with following params as a Map and total price {int}")
    public void iHaveAValidRequestForCreatingBookingWithFollowingParamsAsAMapAndTotalPrice(int totalPrice,Map<String ,String>requestDataMap) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        int checkInPlusDays = Integer.parseInt(requestDataMap.get("checkInPlusDays"));
        int checkOutPlusDays = Integer.parseInt(requestDataMap.get("checkoutPlusDays"));
        String checkInDate =  TestDataHelper.getFutureDate(checkInPlusDays,dateFormatter);
        String checkOutDate =  TestDataHelper.getFutureDate(checkOutPlusDays,dateFormatter);

        this.requestPayload = ApiRequestHelper.createBookingPayload(
                requestDataMap.get("firstName"),
                requestDataMap.get("lastName"),
                totalPrice,
                Boolean.parseBoolean(requestDataMap.get("depositPaid")),
                checkInDate,
                checkOutDate,
                requestDataMap.get("additionalneeds")

        );
        this.sharedStepContext.createBookingApiRequest = requestPayload;


    }

    @When("I send the request to create booking API")
    public void iSendTheRequestToCreateBookingAPI() {
        this.createBookingApiResponse = this.createBookingApi.createNewBooking(this.requestPayload);
        this.sharedStepContext.apiResponse =  this.createBookingApiResponse;
    }

    @And("create booking API response has valid booking ID")
    public void createBookingAPIResponseHasValidBookingID() {
        this.createBookingApiResponse.then().body("bookingid",is(Matchers.greaterThan(0)));
    }


    @When("Booking id has been saved in shared context")
    public void bookingIdHasBeenSavedInSharedContext() {
        this.sharedStepContext.bookingId = this.createBookingApiResponse.jsonPath().getInt("bookingid");

    }
}
