package me.kostevych.datastructure;

import java.util.*;

/** Represents json structure for a country
 * @author Maxym Kostevych
*/
public class Rates {
	private String name;
    private String code;
    private String country_code;
    //TreeSet is an implementation of SortedSet
    private SortedSet<Periods> periods = new TreeSet<>();
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public SortedSet<Periods> getPeriods() {
		return periods;
	}

	public void setPeriods(SortedSet<Periods> periods) {
		this.periods = periods;
	}

   public Map<String, Double> getCurrentRates() {
        return periods.last().getRates();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rates rate = (Rates) o;
        return code.equalsIgnoreCase(rate.code);
    }
    
    @Override
    public int hashCode() {
    	//Code is uniq, could be used
        return code.hashCode();
    }
}
