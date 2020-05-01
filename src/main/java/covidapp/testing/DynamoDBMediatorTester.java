package covidapp.testing;

import java.util.ArrayList;
import java.util.Map;

import covidapp.backend.DynamoDBMediator;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;


public class DynamoDBMediatorTester {

	public static ArrayList<ArrayList<String[]>> getItemTest() {
		DynamoDbClient client = DynamoDBMediator.getClient(Region.US_EAST_1);
		ArrayList<String[]> stateData = new ArrayList<String[]>();
		ArrayList<ArrayList<String[]>> allStates = new ArrayList<ArrayList<String[]>>();
		covidapp.backend.State states[] = covidapp.backend.State.values();
		System.out.println(states[0].getName());
		for(int i = 0; i < covidapp.backend.State.values().length; i++) {
			Map<String, AttributeValue> res = DynamoDBMediator.getDynamoDBItem(client, "covid-data", "state", states[i].toString());
			res = DynamoDBMediator.filterResponse(res, "death", "hospitalizedCurrently", "dataQualityGrade");
			stateData = new ArrayList<String[]>();
			stateData.add(new String[]{states[i].name});
			for (String k : res.keySet()) {
				// Add key-value pair
				String numericalValue = res.get(k).n();
				stateData.add(new String[] {k, numericalValue != null ? numericalValue : res.get(k).s() });
			}
			allStates.add(stateData);
		}
		
		return allStates;
	}

	
}
