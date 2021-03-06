package tashyue.covidapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

import covidapp.backend.DynamoDBMediator;
import covidapp.backend.State;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RestController
public class HelloController {
    @GetMapping("/api/array")
    public ArrayList<ArrayList<String[]>> hello() {
        
        // Gson gson = new Gson();
        // ArrayList<String[]> json = gson.toJson(testData);

    	DynamoDbClient client = DynamoDBMediator.getClient(Region.US_EAST_1);
		ArrayList<String[]> stateData = new ArrayList<String[]>();
		ArrayList<ArrayList<String[]>> allStates = new ArrayList<ArrayList<String[]>>();
		for(State state : State.values()) {
			Map<String, AttributeValue> res = DynamoDBMediator.getDynamoDBItem(client, "covid-data", "state", state.toString());
			res = DynamoDBMediator.filterResponse(res, "death", "hospitalizedCurrently", "dataQualityGrade");
			stateData = new ArrayList<String[]>();
			stateData.add(new String[]{state.name});
			for (String k : res.keySet()) {
				// Add key-value pair
				String numericalValue = res.get(k).n();
				stateData.add(new String[] {k, numericalValue != null ? numericalValue : res.get(k).s() });
			}
			

			if(stateData.get(2)[1].equals("B")) {
				System.out.println("B grade");
				allStates.add(stateData);
			}
			if(stateData.get(2)[1].equals("A")) {
				System.out.println("A grade");
				allStates.add(stateData);
			}
			if(stateData.get(2)[1].equals("A+")) {
				System.out.println("A+ grade");
				allStates.add(stateData);
			}

		}
        return allStates;
        
    }
    @GetMapping("/api")
    public ArrayList<String> helloJSON() {
    	DynamoDbClient client = DynamoDBMediator.getClient(Region.US_EAST_1);
    	ArrayList<String> allStates = new ArrayList<String>();
    	
    	for(State state: State.values()) {
    		Map<String, AttributeValue> res = DynamoDBMediator.getDynamoDBItem(client, "covid-data", "state", state.toString());
    		res = DynamoDBMediator.filterResponse(res, "death", "hospitalizedCurrently", "dataQualityGrade");
    		
			String stateJSON = DynamoDBMediator.mapTOJSON(res);
    		allStates.add(stateJSON);
		}
		
    	System.out.println(allStates);
    	return allStates;
    }
}