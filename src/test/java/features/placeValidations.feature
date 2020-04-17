Feature: Validating Place APIs

Scenario: Verify if Place is being Successfully Added using AddplaceAPI
		Given Add place Payload
		When User calls "AddPlaceAPI" with "POST" Http Request
		Then The Api call gets success with status code 200
		And "status" is response body is "OK"
		And verify "place_Id" created maps using "GetPlaceAPI"
		
Scenario: Verify if Delete Place is working or not
		Given Delete Payload
		When User calls "DeletePlaceAPI" with "DELETE" Http Request 
		Then The Api call gets success with status code 200
		And "status" is response body is "OK"
			