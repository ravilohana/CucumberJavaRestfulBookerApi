Feature: Create new booking using Data Tables

  Scenario: Create a new booking using Data Tables as list of Maps
    Given I have a valid request for creating booking with following params
      | firstName | lastName | depositPaid | additionalNeeds | totalPrice | checkInPlusDays | checkoutPlusDays |
      | Sam       | Alton    | false       | Cola            | 500        | 10              | 14               |
    When I send the request to create booking API
    Then API Response should have HTTP Status Code 200
    And create booking API response has valid booking ID

  Scenario: Create a new booking using Data Tables as a Maps
    Given I have a valid request for creating booking with following params as a Map and total price 999
      | firstName        | Sam   |
      | lastName         | Alton |
      | additionalNeeds  | Cola  |
      | totalPrice       | 500   |
      | checkInPlusDays  | 10    |
      | checkoutPlusDays | 14    |
    When I send the request to create booking API
    Then API Response should have HTTP Status Code 200
    And create booking API response has valid booking ID

  Scenario Outline: Create a new booking using Data Tables as list of Maps
    Given I have a valid request for creating booking with following params
      | firstName | lastName | depositPaid | additionalNeeds | totalPrice   | checkInPlusDays | checkoutPlusDays |
      | Sam       | Alton    | false       | Cola            | <totalPrice> | 10              | 14               |
    When I send the request to create booking API
    Then API Response should have HTTP Status Code 200
    And create booking API response has valid booking ID

    Examples:
      | totalPrice |
      | 555        |
      | 666        |
      | 777        |


