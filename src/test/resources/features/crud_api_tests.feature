Feature: CRUD API for Restful Booker web app API

  Scenario: E2E CRUD Scenario for Restful Booker API

    # Create New Booking --POST call
    Given I have a valid request for creating booking with following params as a Map and total price 999
      | firstName        | Sam   |
      | lastName         | Alton |
      | additionalNeeds  | Cola  |
      | checkInPlusDays  | 10    |
      | checkoutPlusDays | 14    |
    When I send the request to create booking API
    Then API Response should have HTTP Status Code 200
    And create booking API response has valid booking ID


    When Booking id has been saved in shared context

    # Fetch/Retrieve the booking id which newly created - GET Call
    And  I retrieve the booking using booking id
    Then API Response should have HTTP Status Code 200
    And  get booking API response should have fields same as create request

    # Update same booking API  - PUT Call
    And I prepare a request for update booking api
      | firstName | lastName | depositPaid | additionalNeeds  | totalPrice | checkInPlusDays | checkoutPlusDays |
      | Stave     | Austin   | true        | Roasted Red Meat | 10000      | 10              | 20               |
    And I send the request to update booking api
    Then API Response should have HTTP Status Code 200

    # Delete the same booking -- DELETE Call
    When  we send request to delete booking API
    Then API Response should have HTTP Status Code 201


    # Fetch/Retrieve the booking id which Deleted in above step - GET Call -  Assert status code as 404 Not found
    And  I retrieve the booking using booking id
    Then API Response should have HTTP Status Code 404