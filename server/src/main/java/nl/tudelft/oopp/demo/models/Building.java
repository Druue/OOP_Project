package nl.tudelft.oopp.demo.models;

import java.time.Period;
import java.util.Map;

/**
 * Building
 */
public class Building {
	public long Id;
	public String Name;
	public Period OpeningHours;
	public Foodcourt Foodcourt;
	Map<Reservable, Iterable<Period>> AvailableReservations;
}