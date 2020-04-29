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
	 * Initials for all 50 states + DC
	 * @TODO: Add more information to each state (full name, region, etc)
	 *
	 */
	public enum State{
		AL, AK, AZ, AR, CA, CO, CT, DE, DC, FL,
		GA, HI, ID, IL, IN, IA, KS, KY, LA, ME,
		MD, MA, MI, MN, MS, MO, MT, NE, NV, NH,
		NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI,
		SC, SD, TN, TX, UT, VT, VA, WA, WV, WI, WY
	}
	
	
	/**
	 * 
	 * Method for getting the data for a specified state
	 * @return Map of key-value pairs for state data
	 * 
	 */
	public static List<Integer> getCovidDataForState(State s) {
		DynamoDbClient client = DynamoDBSMediator.getClient(Region.US_WEST_1);
		
		return reformatData(DynamoDBSMediator.getDynamoDBItem(client, "covid-data", "State", s.name()));
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
