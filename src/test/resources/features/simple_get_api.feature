Feature: Simple HTTP GET Call
  This is a simple example for HTTP GET with cucumber.

  Scenario: Make HTTP GET Call to fetch all booking ids and validate status code is 200
    Given I prepare a simple HTTP GET request
    When I send the request to API
    Then API Response should have HTTP Status Code 200


  Scenario Outline: Make HTTP GET Call to fetch single booking id and validate status code is 200
    Given I prepare a simple HTTP GET request
    When I send the request to API with bookingID <BookingID>
    Then API Response should have HTTP Status Code 200
    Examples:
      | BookingID |
      | 255    |
      | 167       |
      | 4       |


  Scenario Outline: Make HTTP GET Call to fetch single unavailable booking id and validate status code is 404 Not Found (Negative Test Case)
    Given I prepare a simple HTTP GET request
    When I send the request to API with invalid bookingID <BookingID>
    Then API Response should have HTTP Status Code <statusCode>
    Examples:
      | BookingID | statusCode |
      | 10000     | 404        |