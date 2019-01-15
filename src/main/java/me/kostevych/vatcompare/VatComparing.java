package me.kostevych.vatcompare;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import me.kostevych.datastructure.Rates;

/** Printing out three EU countries with the lowest 
 *  and three EU countries with the highest 
 *  standard VAT rate as of today within the EU 
 * @author Maxym Kostevych
*/
public class VatComparing 
{
	public static final String SOURCE_URL = "http://jsonvat.com";
	public static final int LIMIT = 3;
	public static final String RATE_NAME = "standard";
	
	private static void printRates(List<Rates> rates, int limit) {
		int i = limit;
		Iterator<Rates> iterator = rates.iterator();
		while(iterator.hasNext() && i > 0) {
			Rates r = iterator.next();
			System.out.println(String.format(" %-12s%.1f%%",  r.getName(), r.getCurrentRates().get(RATE_NAME)));
			i--;
		}
	}
	
	private static void printRates(List<Rates> rates) {
		printRates(rates, rates.size());
	}
	
    public static void main( String[] args )
    {
    	//Specify a source
    	LoadFromSource source = new LoadFromSource(SOURCE_URL);
    	
    	//Store data from json
    	try {
			source.storeCountryRates();
		} catch (JsonProcessingException e) {
			System.out.println("Source couldn't be parsed");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.out.println("Source couldn't be found");
			e.printStackTrace();
			return;
		}
    	
    	System.out.println("EU countries with the lowest VAT");
    	printRates(source.getSortedCountryRates(RATE_NAME, true), LIMIT);
    	
    	System.out.println("\nEU countries with the highest  VAT");
    	printRates(source.getSortedCountryRates(RATE_NAME, false), LIMIT);
        
    }
}
