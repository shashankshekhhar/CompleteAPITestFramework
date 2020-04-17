package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuilder {

	public AddPlace addPlacePayLoad() {

		AddPlace addplace = new AddPlace();
		Location location = new Location();
		location.setLat("-38.383494");
		location.setLng("33.427362");
		addplace.setLocation(location);
		addplace.setAccuracy("50");
		addplace.setName("Frontline house");
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setAddress("29, side layout, cohen 09");
		List<String> types= new ArrayList();
		types.add("SHoe");
		types.add("Shop");
		addplace.setTypes(types);
		addplace.setWebsite("http://google.com");
		addplace.setLanguage("French-IN");

		return addplace;
	}
	
	public String  deletePlacePayload(String Place_Id) {
		
		return "{\r\n" + 
				"    \"place_id\":\""+Place_Id+"\"\r\n" + 
				"}" ;
	}
}
