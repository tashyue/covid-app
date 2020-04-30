package tashyue.covidapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import covidapp.backend.DynamoDBMediator;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello() {
    	
    	//EXAMPLE FOR HOW TO USE DynamoDBMediator
    	
    	//Step 1: Get the client (We're using US_EAST_1 because the database is in the N. Virginia region)
    	DynamoDbClient client = DynamoDBMediator.getClient(Region.US_EAST_1);
    	
    	//Step 2: Request data from DynamoDBMediator (returns a Map<String, AttributeValue)
    		//First param: The client to use (the one we just got)
    		//Second param: The table name (as written in the database)
    		//Third param: The primary key column name (In this case, it's state)
    		//Fourth param: What value of the primary key we're looking for (In this case, we're looking for California)
    	Map<String, AttributeValue> res = DynamoDBMediator.getDynamoDBItem(client, "covid-data", "state", "CA");
    	
    	//Step 3: Filter out everything but the columns we want to use
    		//First param: A Map<String, AttributeValue> that you want to filter (this is from the previous step
    		//The rest of the params: The columns you want to keep
    	res = DynamoDBMediator.filterResponse(res, "death", "hospitalizedCurrently", "dataQualityGrade");
    	
    	
    	//Step 4: Get the values from each column
    		//The Map's key is the column name
    		//The associated AttributeValue is the value of the column
    	
    	String deathCount = res.get("death").n();											//Get the value of the "death" column; .n() gets the numerical value of this attribute as a String
    	String hospitalizationCount = res.get("hospitalizedCurrently").n();					//Get the value of the "hospitalizedCurrently" column; .n() gets the numerical value of this attribute as a String
    	String dataQualityGrade = res.get("dataQualityGrade").s();							//Get the value of the "dataQualityGrade" column; .s() gets the String value of this attribute
    	
    	
    	//Step 5: Use the values you got however you want (here I just put them in a String to display)
    	String display = "There were a total of " + deathCount 
    					+ " deaths and " + hospitalizationCount
    					+ " hospitalizations in California. The data was given a quality grade of: " + dataQualityGrade;
    	
    	
    			
        return display;
        
        //END EXAMPLE
    }
}