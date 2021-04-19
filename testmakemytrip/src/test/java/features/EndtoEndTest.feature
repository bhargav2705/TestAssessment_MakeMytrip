Feature: To test Make my trip application

Scenario Outline: TC 1_Test the End to End flight booking with valid credentials

Given I login on the MakeMyTrip and home page is displayed.
When I select the Source as "Bengaluru" and  Destination as "Delhi" for "roundTrip".
And I select the departure date as today and arrival date as tomorrow.
And I select the no.of passsengers as "<Adults>" and "<Children>" and click on Apply button. 
And I click on Search button and verify the flights list page is displayed.
And I click on non stop flights check box and sort  with lowest price.
And I select the flights with lowest price and click on Book Now button
And I click on Continue in the next page
Then I verify the new tab is displayed with the Title as "Review your booking".

Examples:

|Adults|Children|
|2     |1       |

