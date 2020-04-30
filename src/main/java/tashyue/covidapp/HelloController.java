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
    	
    	String display = "";
    	
    	for(String[] kv : testData) {
    		display += "Attribute: " + kv[0] + "  |  Value: " + kv[1] + "\n\n";
    	}
    			
        return display;
    }
}