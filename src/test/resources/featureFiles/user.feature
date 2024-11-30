Feature: Automating User page Functionality

  @Regression @SmokeTest
  Scenario Outline: Verify login functionality with valid credentials
    Given verify user navigates the URL "<appURL>"
    And verify user enters valid credentials "<userName>" and "<password>"
    And verify user logout from the application
    Examples:
      | appURL                    | userName |  | password |
      | http://localhost:81/login.do | admin    |  | manager  |


  @Regression @SmokeTest
  Scenario Outline: Verify Create and delete user functionality
    Given verify user navigates the URL "<appURL>"
    And verify user enters valid credentials "<userName>" and "<password>"
    Then verify create user functionality with "<userFirstName>", "<userLastName>", "<userEmail>", "<userUserName>", "<userPassword>", "<retypePassword>" details
    Then verify delete user functionality
    And verify user logout from the application
    Examples:
      | appURL                    | userName | password | userFirstName | userLastName | userEmail         | userUserName | userPassword | retypePassword |
      | http://localhost/login.do | admin    | manager  | sg            | tester1      | sg.tester1@sg.com | sgtester1    | Mercury@1    | Mercury@1      |
