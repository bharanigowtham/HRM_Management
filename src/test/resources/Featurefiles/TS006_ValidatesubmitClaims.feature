@Regression @TS006
Feature: Submit new claim

Background: As an admin I have to login into the system
Given I launch url & have to login to system with valid credentials for user "Admin1"

@TS006_TC001
Scenario Outline: Validate user able to submit the claim
Given I navigate to "Claim" module 
When I click on "Submit Claim"
And I create claim request from "<Sheetname>" & <RowNo>
And Add expense details from "<Sheetname>" & <RowNo>
Then validate the expense details & consolidate expense amount from "<Sheetname>" & <RowNo>
And click on submit

Examples:
|Sheetname   | RowNo |
|SubmitClaims|   0    |




