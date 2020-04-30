package covidapp.backend;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.qos.logback.classic.db.names.TableName;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * 
 * @author Kevin Prakash
 *
 *         Meant to be the single point of contact between the application's
 *         backend and DynamoDB. All calls should be done through this class
 * 
 * @version 1.0
 * @since 1.0
 * 
 */
public class DynamoDBMediator {

	/**
	 * Method for requesting an item from DynamoDB
	 * 
	 * @return a Map of key-value pairs associated with requested key (column name)
	 *         and keyValue (column value)
	 * 
	 *         Code modified from AWS documentation examples
	 */
	public static Map<String, AttributeValue> getDynamoDBItem(DynamoDbClient client, String tableName, String key,
			String keyValue) {

		// Object wrapping for DynamoDB
		HashMap<String, AttributeValue> keyHashMap = new HashMap<String, AttributeValue>();
		keyHashMap.put(key, AttributeValue.builder().s(keyValue).build());

		// Create Request
		GetItemRequest request = GetItemRequest.builder().key(keyHashMap).tableName(tableName).build();

		// Here be dragons
		try {
			// Receive Response
			Map<String, AttributeValue> response = client.getItem(request).item();

			if (response != null) {
				System.out.format("Successfully found %d items with key: %s\n", response.keySet().size(), key);
				return response;
			} else {
				System.out.format("No item found with key: %s\n", key);
				return null;
			}
		} catch (DynamoDbException e) {
			System.err.println(e.getMessage());
			System.exit(1);
			return null;
		}
	}

	/**
	 * Method for filtering out unneeded attributes from a DynamoDB response
	 * 
	 * @param response         The response to filter
	 * @param attributesToKeep Which attributes from the response to keep
	 * @return a Map<String, AttributeValue> with unneeded attributes removed
	 */
	public static Map<String, AttributeValue> filterResponse(Map<String, AttributeValue> response,
			String... attributesToKeep) {
		Map<String, AttributeValue> filteredResponse = new HashMap<String, AttributeValue>();
		for (String s : attributesToKeep) {
			if (response.containsKey(s)) {
				filteredResponse.put(s, response.get(s));
			}
		}
		return filteredResponse;
	}

	/**
	 * Method for getting a client object for DynamoDB
	 * 
	 * @return DynamoDbClient for specified region
	 */
	public static DynamoDbClient getClient(Region r) {
		return DynamoDbClient.builder().region(r).build();
	}

	
}
