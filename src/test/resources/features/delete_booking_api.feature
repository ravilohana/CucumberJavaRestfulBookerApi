Feature: Delete API of Restful booker web app

  Scenario: Create a new booking and delete the same booking
    Given I have a valid request for creating booking with following params as a Map and total price 999
      | firstName        | New_Sam   |
      | lastName         | New_Alton |
      | additionalNeeds  | Cola  |
      | totalPrice       | 500   |
      | checkInPlusDays  | 10    |
      | checkoutPlusDays | 14    |
    When I send the request to create booking API
    Then API Response should have HTTP Status Code 200
    And create booking API response has valid booking ID

    When Booking id has been saved in shared context
    And  we send request to delete booking API

    Then API Response should have HTTP Status Code 201


  Scenario: Delete the existing booking
    Given I prepare a simple HTTP GET request
    When I send the request to API
    Then API Response should have HTTP Status Code 200
    And Extract the booking id from the response of get all bookings


    When  we send request to delete booking API

    Then API Response should have HTTP Status Code 201