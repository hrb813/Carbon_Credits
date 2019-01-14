Feature: Validation via JAXRS 

@RunThisTest
Scenario: Validating the Name CanRelist and Promotions values
Given the API URL is "https://api.tmsandbox.co.nz"
    And the path is "/v1/Categories/6327/Details.json"
    When The parameters are "catalogue" and the value is "true"
    And The response is mapped
    Then The "Name" field should have the value "Carbon credits"
    And The "CanRelist" field should have the value "true"
    And The "Promotions" object that has the field key "Name" with the value "Gallery" and the field "Description" should contain "2x larger image"


#  Acceptance Criteria:
#  Name = "Carbon credits"
#  CanRelist = true
#  The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"

# This test can also be data driven by changing this into a Scenario Outline with Examples.
