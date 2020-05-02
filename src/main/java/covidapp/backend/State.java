package covidapp.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Initials for all 50 states + DC
 * @TODO: Add more information to each state (<STRIKE>full name</STRIKE>, region, etc)
 *
 */
public enum State{
	AL("Alabama"), 
	AK("Alaska"),
	AZ("Arizona"),
	AR("Arkansas"),
	CA("California"),
	CO("Colorado"),
	CT("Connecticut"),
	DE("Delaware"),
	DC("District of Columbia"),
	FL("Florida"),
	GA("Georgia"),
	HI("Hawaii"),
	ID("Idaho"),
	IL("Illinois"),
	IN("Indiana"),
	IA("Iowa"),
	KS("Kansas"),
	KY("Kentucky"),
	LA("Loisiana"),
	ME("Maine"),
	MD("Maryland"),
	MA("Massachusetts"),
	MI("Michigan"),
	MN("Minnesota"),
	MS("Mississippi"),
	MO("Missouri"),
	MT("Montana"),
	NE("Nebraska"),
	NV("Nevada"),
	NH("New Hampshire"),
	NJ("New Jersey"),
	NM("New Mexico"),
	NY("New York"),
	NC("North Carolina"),
	ND("North Dakota"),
	OH("Ohio"),
	OK("Oklahoma"),
	OR("Oregon"),
	PA("Pennsylvania"),
	RI("Rhode Island"),
	SC("South Carolina"),
	SD("South Dakota"),
	TN("Tennessee"),
	TX("Texas"),
	UT("Utah"),
	VT("Vermont"),
	VA("Virginia"),
	WA("Washington"),
	WV("West Virginia"),
	WI("Wisconsin"),
	WY("Wyoming");
	
	//For searching by values
	private static final Map<String, State> BY_NAME = new HashMap<String, State>();
	
	static {
		for(State s : values()) {
			BY_NAME.put(s.name, s);
		}
	}
	
	public static State getByName(String name) {
		return BY_NAME.get(name);
	}
	//
	
	public final String name;
	
	private State(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
