Feature: AccountHash Functionality

  @validPost
  Scenario: Employee Should Provide Valid AccountNumber
    Given Post AccountHash API
    When Provide Valid AccountNumber
    Then Status_code equals 200
    And  response contains AccountHashNumber equals "IsPosted"

  @invalidPost
  Scenario: Employee Accidentally Provides Invalid Path
    Given Post AccountHash API
    When Provide Invalid Path
    Then Status_code equals 404
    And  response contains AccountHashNumber equals "Not_Found"
