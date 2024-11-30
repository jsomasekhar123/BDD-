Feature: Automating Login page Functionality

  @Regression @SmokeTest
  Scenario: Verify login functionality with valid credentials
    Given verify user navigates the URL
      | appURL                    |
      | http://localhost:81/login.do |
    And verify user enters valid credentials
      | userName | password |
      | admin    | manager  |
    And verify user logout from the application


  @Regression @SmokeTest
  Scenario: Verify Create and delete user functionality
    Given verify user navigates the URL
      | appURL |
      | http://localhost:81/login.do       |
    And verify user enters valid credentials
      | userName | password |
      | admin    | manager  |
    Then verify create user functionality
      | firstName | lastName | email           | userName | password  | retypePassword |
      | sg        | user1    | sg.user1@sg.com | sguser1  | Mercury@1 | Mercury@1      |
    Then verify delete user functionality
    And verify user logout from the application
