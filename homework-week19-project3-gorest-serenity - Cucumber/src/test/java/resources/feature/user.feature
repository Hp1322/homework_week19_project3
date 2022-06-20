Feature: Testing different request on the user

  Scenario: I get all users from application
    Given I am on homepage of application of user
    When I send Get request to list endpoint of user
    Then I must get back a valid status code 200 of user

  Scenario: I create user from application
    Given I am on homepage of application of user
    When I send Post request to list endpoint of user
    Then I must get back a valid status code 201 of user

  Scenario: I update user from application
    Given I am on homepage of application of user
    When I send Put request to list endpoint of user
    Then I must get back a valid status code 200 of user

  Scenario: I delete user from application
    Given I am on homepage of application of user
    When I send Delete request to list endpoint of user
    Then I must get back a valid status code 204 of user
    And I validate if user is deleted


    Scenario: I verify following data response
      Given I am on homepage of application of user
      When I send Get request to list endpoint of user
      Then I validate total records 20
      And I validate name "Rev. Nimit Gowda" of Id 2357
      And I validate email "guha_aagam@kreiger.net" of Id 2352
      And I validate status of all Id
      And I validate gender "male" of Id 2351


