
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {
		
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		System.out.println("develop branch example");
		RestAssured.baseURI= "petclinicapi.e46708b92c054086909b.eastus.aksapp.io/petclinic";
		String response=given().log().all().header("Content-Type","application/json")
		.body(payload.AddVet()).when().post("api/vets")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response); //for parsing Json
		String vetId=js.getString("vet_id");
		
		System.out.println(vetId);
		
		
		String name = "psychology";
		//Update Vet
		
		given().log().all().header("Content-Type","application/json")
		.body("\"specialties\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\":\""+vetId+"\",\r\n" + 
				"      \"name\":\""+name+"\",\r\n" + 
				"    }\r\n" + 
				"  ]").
		when().put("api/vets")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		
		//Get Vet
		
	
		
	}

}
