package tashyue.covidapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.ArrayList;

import covidapp.testing.DynamoDBMediatorTester;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello() {
    	
    	ArrayList<String[]> testData = DynamoDBMediatorTester.getItemTest();
    	
    	String display = "There were a total of " + testData.get(0)[1] 
    					+ " deaths and " + testData.get(2)[1] 
    					+ " hospitalizations in California. The data was given a quality grade of: " + testData.get(1)[1];
    	
    	
    			
        return display;
    }
}