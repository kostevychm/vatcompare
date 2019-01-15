package me.kostevych.datastructure;

import java.util.Date;
import java.util.Map;

/** Represents json structure for a period.
 * @author Maxym Kostevych
*/
public class Periods implements Comparable<Periods>{
	
	Date effective_from;
    Map<String, Double> rates;
    
	public Date getEffective_from() {
		return effective_from;
	}

	public void setEffective_from(Date effective_from) {
		this.effective_from = effective_from;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

    public int compareTo(Periods o) {
        return effective_from.compareTo(o.effective_from);
    }
}
