Feature: Alert Api Interactions

  In order to monitor certain products
  As a user of the market-alert api
  I want to be able to upload and view up to five alerts
  Showing the product name, description, type, price, image, type and link.

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts

  Scenario: Invalid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again

  Scenario: Alert Layout
    Given I am a user of marketalertum
    And I am an administrator of the website and I upload 3 alerts
    When I view a list of 3 alerts
    Then each alert should contain an icon
    And each alert should contain a heading
    And each alert should contain a description
    And each alert should contain an image
    And each alert should contain a price
    And each alert should contain a link to the original product website

  Scenario: Alert Limit
    Given I am a user of marketalertum
    Given I am an administrator of the website and I upload 6 alerts
    When I view a list of 6 alerts
    Then I should see 5 alerts

  Scenario Outline: Icon Check
    Given I am a user of marketalertum
    Given I am an administrator of the website and I upload an alert of type <alert-type>
    When I view a list of 1 alerts
    Then I should see 1 alerts
    And the icon displayed should be "<icon-file-name>"
    Examples:
      | alert-type | icon-file-name |
      | 1          | icon-car.png   |
      | 2          | icon-boat.png   |
      | 3          | icon-property-rent.jpg   |
      | 4          | icon-property-sale.jpg   |
      | 5          | icon-toys.png   |
      | 6          | icon-electronics.png   |

