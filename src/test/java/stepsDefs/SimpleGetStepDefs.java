package stepsDefs;

import apis.GetBookingApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import sharedStepContext.SharedStepContext;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimpleGetStepDefs {


    private GetBookingApi getBookingApi;
    private final SharedStepContext sharedStepContext;

    public SimpleGetStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.getBookingApi = new GetBookingApi();
    }


    @Given("I prepare a simple HTTP GET request")
    public void iPrepareASimpleHTTPGETRequest() {
        this.getBookingApi = new GetBookingApi();
    }

    @When("I send the request to API")
    public void iSendTheRequestToAPI() {
        this.sharedStepContext.apiResponse = this.getBookingApi.getAllBookingIds();

    }

    @When("I send the request to API with bookingID {int}")
    public void iSendTheRequestToAPIWithBookingID(int bookingId) {
        this.sharedStepContext.apiResponse = this.getBookingApi.getBookingById(bookingId);


    }

    @When("I send the request to API with invalid bookingID {int}")
    public void iSendTheRequestToAPIWithInvalidBookingID(int bookingID) {
        this.sharedStepContext.apiResponse = this.getBookingApi.getBookingById(10000);


    }


    @And("Extract the booking id from the response of get all bookings")
    public void extractTheBookingIdFromTheResponseOfGetAllBookings() {
        List<Integer> allBookingIdList = this.getBookingApi.getAllBookingIds()
                                                           .then().extract().response().jsonPath().getList("bookingid");

        System.out.println("Booking id list: ");
        System.out.println(allBookingIdList);
        System.out.println(allBookingIdList.size());

        this.sharedStepContext.bookingId = allBookingIdList.get(11);

    }

    @And("I retrieve the booking using booking id")
    public void iRetrieveTheBookingUsingBookingId() {
        this.sharedStepContext.apiResponse = this.getBookingApi.getBookingById(this.sharedStepContext.bookingId);
    }

    @SuppressWarnings("unchecked")
    @And("get booking API response should have fields same as create request")
    public void getBookingAPIResponseShouldHaveFieldsSameAsCreateRequest() {

        var createBookingRequest = this.sharedStepContext.createBookingApiRequest;
        var bookingDates = (Map<String, String>) createBookingRequest.get("bookingdates");
        this.validateRetrievedBookingDataFromGetApi(
                (String) createBookingRequest.get("firstname"),
                (String) createBookingRequest.get("lastname"),
                (Boolean) createBookingRequest.get("depositpaid"),
                (String) createBookingRequest.get("additionalneeds"),
                (Integer) createBookingRequest.get("totalprice"),
                bookingDates.get("checkin"),
                bookingDates.get("checkout"),
                this.sharedStepContext.apiResponse);

    }


    private void validateRetrievedBookingDataFromGetApi(String firstName, String lastName, Boolean depositPaid,
                                                        String additionalNeeds, Integer totalPrice, String checkInDate,
                                                        String checkOutDate, Response getBookingByIdApiResponse) {
        getBookingByIdApiResponse
                .then().assertThat().statusCode(200)
                .and().body("firstname", is(equalTo(firstName)))
                .and().body("lastname", is(equalTo(lastName)))
                .and().body("totalprice", is(equalTo(totalPrice)))
                .and().body("depositpaid", is(equalTo(depositPaid)))
                .and().body("additionalneeds", is(equalTo(additionalNeeds)))
                .and().rootPath("bookingdates")
                .and().body("checkin", equalTo(checkInDate))
                .and().body("checkout", equalTo(checkOutDate))
                .and().detachRootPath("bookingdates");
    }
}