Feature: Update API of restful booker api
  Update restful booker api should update newly created and existing  booking

  Scenario: Create a new booking and update all the fields
    Given I have a valid request for creating booking with following params
      | firstName | lastName | depositPaid | additionalNeeds | totalPrice | checkInPlusDays | checkoutPlusDays |
      | Rock      | Salt     | false       | Roasted Beef    | 5000       | 20              | 25               |
    When I send the request to create booking API
    Then API Response should have HTTP Status Code 200
    And create booking API response has valid booking ID

    When Booking id has been saved in shared context
    And I prepare a request for update booking api
      | firstName | lastName | depositPaid | additionalNeeds  | totalPrice | checkInPlusDays | checkoutPlusDays |
      | Stave     | Austin   | true        | Roasted Red Meat | 10000      | 10              | 20               |
    And I send the request to update booking api
    Then API Response should have HTTP Status Code 200

  Scenario: Update a existing booking
    Given I prepare a simple HTTP GET request
    When I send the request to API
    Then API Response should have HTTP Status Code 200
    And Extract the booking id from the response of get all bookings

    When I prepare a request for update booking api
      | firstName | lastName | depositPaid | additionalNeeds  | totalPrice | checkInPlusDays | checkoutPlusDays |
      | Jack      | Dworson  | true        | Roasted Red Meat | 9999      | 10              | 20               |
    And I send the request to update booking api
    Then API Response should have HTTP Status Code 200