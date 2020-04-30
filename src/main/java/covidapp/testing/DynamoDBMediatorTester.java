package covidapp.testing;

import java.util.ArrayList;
import java.util.Map;

import covidapp.backend.DynamoDBMediator;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class DynamoDBMediatorTester {

	public static ArrayList<String[]> getItemTest() {
		DynamoDbClient client = DynamoDBMediator.getClient(Region.US_EAST_1);
		Map<String, AttributeValue> res = DynamoDBMediator.getDynamoDBItem(client, "covid-data", "state", "CA");
		
		ArrayList<String[]> output = new ArrayList<String[]>();
		for(String k : res.keySet()) {
			//Add key-value pair
			output.add(new String[] {
					k,
					res.get(k).toString()
			});
		}
		return output;
	}
}
