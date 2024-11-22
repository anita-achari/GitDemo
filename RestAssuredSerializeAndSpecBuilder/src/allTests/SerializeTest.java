package allTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest {

	public static void main(String[] args) {
		AddPlace addPlace = new AddPlace();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		addPlace.setLocation(location);
		addPlace.setAccuracy(50);
		addPlace.setName("Frontline house");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress("29, side layout, cohen 09");
		
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		
		addPlace.setTypes(types);
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage("French-IN");
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		//Add Place
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().log().all().spec(reqSpec).body(addPlace);
		
		String response=res.when().post("/maps/api/place/add/json")
				.then().log().all().spec(resSpec).extract().asString();
		System.out.println("Add Place response= " + response);
		
		/*String response = given().queryParam("key", "qaclick123").header("Content-Type", "Application/json")
		.body(addPlace)	
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		System.out.println("Add Place response= " + response);

		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println("Place Id: " + placeId);*/

	}

}
