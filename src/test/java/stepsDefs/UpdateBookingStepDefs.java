package stepsDefs;

import apis.UpdateBookingApi;
import io.cucumber.java.en.And;
import sharedStepContext.SharedStepContext;
import util.ApiRequestHelper;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class UpdateBookingStepDefs {

    private final UpdateBookingApi updateBookingApi;
    private final SharedStepContext sharedStepContext;
    private Map<String, Object> requestPayload;


    public UpdateBookingStepDefs(SharedStepContext sharedStepContext) {
        this.updateBookingApi = new UpdateBookingApi();
        this.sharedStepContext = sharedStepContext;
    }

    @And("I prepare a request for update booking api")
    public void iPrepareARequestForUpdateBookingApi(List<Map<String, String>> requestDataList) {

        Map<String, String> requestDataMap = requestDataList.getFirst();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        int checkInPlusDays = Integer.parseInt(requestDataMap.get("checkInPlusDays"));
        int checkOutPlusDays = Integer.parseInt(requestDataMap.get("checkoutPlusDays"));
        String checkInDate = TestDataHelper.getFutureDate(checkInPlusDays, dateFormatter);
        String checkOutDate = TestDataHelper.getFutureDate(checkOutPlusDays, dateFormatter);


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

    @And("I send the request to update booking api")
    public void iSendTheRequestToUpdateBookingApi() {

        int bookingID = this.sharedStepContext.bookingId;
        System.out.println("Update api" + bookingID);
        String username = System.getenv("RESTFUL_BOOKER_USERNAME");
        String password = System.getenv("RESTFUL_BOOKER_PASSWORD");
        this.sharedStepContext.apiResponse = this.updateBookingApi
                .updateBooking(this.requestPayload, bookingID,username, password);
    }
}
