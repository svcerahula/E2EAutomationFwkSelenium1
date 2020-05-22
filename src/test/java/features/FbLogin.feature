Feature: Login into Facebook App

  Scenario Outline: Positive scenario of logging into the FB page
    Given Initialize browser with chrome
    And Navigate to "https://www.facebook.com" login page
    When User enters <username> and <password> and clicks on LogIn button
    Then verify the user is successfully logged in

    Examples:
    |username               |password   |
    |svce.rahula@gmail.com  |svcerahula |

  Scenario Outline: Negative scenario of logging into the FB page
    Given Initialize browser with chrome
    And Navigate to "https://www.facebook.com" login page
    When User enters <username> and <password> and clicks on LogIn button
    Then verify the login is not successful

    Examples:
    |username            |password   |
    |sm_rahula@gmail.com |fgdsfsjlflk|