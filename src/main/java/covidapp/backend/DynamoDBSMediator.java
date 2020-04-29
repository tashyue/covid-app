package covidapp.backend;


import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


/**
 * 
 * @author Kevin Prakash
 *
 * Meant to be the single point of contact between the application's backend and DynamoDB.
 * All calls should be done through this class
 * 
 *  @version 1.0
 *  @since 1.0
 *  
 */
public class DynamoDBSMediator {
	
	/**
	 * Method for requesting an item from DynamoDB
	 * @return a Map of key-value pairs associated with requested key and keyValue
	 * 
	 * @TODO: Figure out why keyValue is needed or if it can be replaced with an empty String
	 * 
	 * Code modified from AWS documentation examples
	 */
	public static Map<String, AttributeValue> getDynamoDBItem(DynamoDbClient client, String tableName, String key, String keyValue) {
		
		//Object wrapping for DynamoDB
		HashMap<String, AttributeValue> keyHashMap = new HashMap<String, AttributeValue>();
		keyHashMap.put(key, AttributeValue.builder().s(keyValue).build());
		
		//Create Request
		GetItemRequest request = GetItemRequest.builder().key(keyHashMap).tableName(tableName).build();
		
		//Here be dragons
		try {
			//Receive Response
			Map<String, AttributeValue> response = client.getItem(request).item();
			
			if(response != null) {
				System.out.format("Successfully found %d items with key: %s\n", response.keySet().size(), key);
				return response;
			}else {
				System.out.format("No item found with key: %s\n", key);
				return null;
			}
		}catch(DynamoDbException e) {
			System.err.println(e.getMessage());
			System.exit(1);
			return null;
		}
	}
}
