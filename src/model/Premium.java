package model;

import java.util.Calendar;

public class Premium extends User {

	/**
	 * 
	 * @param name
	 * @param cc
	 * @param vinculationDate
	 */
	public Premium(String name, String cc, Calendar vinculationDate) {
		super(name, cc, vinculationDate);
	}

}