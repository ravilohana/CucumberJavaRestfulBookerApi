package stepsDefs;

import apis.DeleteBookingApi;
import io.cucumber.java.en.And;
import sharedStepContext.SharedStepContext;

public class DeleteBookingStepDefs {


    private final DeleteBookingApi deleteBookingApi;
    private final SharedStepContext sharedStepContext;

    public DeleteBookingStepDefs(SharedStepContext sharedStepContext) {
        this.deleteBookingApi = new DeleteBookingApi();
        this.sharedStepContext = sharedStepContext;
    }

    @And("we send request to delete booking API")
    public void weSendRequestToDeleteBookingAPI() {

        int bookingID = this.sharedStepContext.bookingId;
        System.out.println("Update api" + bookingID);
        String username = System.getenv("RESTFUL_BOOKER_USERNAME");
        String password = System.getenv("RESTFUL_BOOKER_PASSWORD");
        this.sharedStepContext.apiResponse = this.deleteBookingApi
                .deleteBookingApiById(bookingID,username, password);

    }
}
