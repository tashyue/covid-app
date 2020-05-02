package covidapp.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 * 
 * @author Kevin Prakash
 *
 * Meant for calls to collecting data at a state-level
 * 
 * @version 1.0
 * @since 1.0
 */
public class CovidStateDataCollector {
	
	/**
	 * 
	 * Method for getting the data for a specified state
	 * @return Map of key-value pairs for state data
	 * 
	 */
	public static List<Integer> getCovidDataForState(State s) {
		//Assume Northern Virginia region for now
		DynamoDbClient client = DynamoDBMediator.getClient(Region.US_EAST_1);
		
		return reformatData(DynamoDBMediator.getDynamoDBItem(client, "covid-data", "State", s.name()));
	}

	/**
	 * 
	 * Method for unwrapping the data of a specified state from Map to a more user-friendly data structure
	 * @return List of data values
	 * 
	 */
	public static List<Integer> reformatData(Map<String, AttributeValue> raw){
		//@TODO: Figure out what the data columns are then fix implementation
		List<Integer> ret = new ArrayList<Integer>();
		for(String key : raw.keySet()) {
			//Assumes that all values in the table are integers. VERY DANGEROUS CONFIRM ASAP.
			int value = Integer.parseInt(raw.get(key).toString());
			ret.add(value);
		}
		return ret;
	}
}
