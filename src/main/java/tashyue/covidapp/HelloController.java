package tashyue.covidapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import covidapp.testing.DynamoDBMediatorTester;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RestController
public class HelloController {
    @GetMapping("/api")
    public ArrayList<ArrayList<String[]>> hello() {
    	
    	ArrayList<ArrayList<String[]>> testData = DynamoDBMediatorTester.getItemTest();
        
        // Gson gson = new Gson();
        // ArrayList<String[]> json = gson.toJson(testData);

       

        
        return testData;
        
    }
}