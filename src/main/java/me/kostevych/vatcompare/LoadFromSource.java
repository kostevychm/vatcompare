package me.kostevych.vatcompare;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import me.kostevych.datastructure.Rates;

public class LoadFromSource {
	private JsonNode rootNode;
    private ObjectMapper objectMapper;
	private final String ROOT_NODE = "rates";
	private String sourceUrl;
	private Set<Rates> countryRates;
    
    public LoadFromSource(String sourceUrl) {
        objectMapper = new ObjectMapper();
        countryRates = new HashSet<>();
        this.sourceUrl = sourceUrl;
    }

    /** 
	 * Store from the source into Pojos
	 */
	public void storeCountryRates() throws JsonProcessingException, IOException
    {
		rootNode = objectMapper.readTree(new URL(sourceUrl));
        JsonNode countryRatesNode = rootNode.get(ROOT_NODE);
		
        for (JsonNode node : countryRatesNode) {
        	countryRates.add(objectMapper.convertValue(node, Rates.class));
		}
    }
	
	/** 
	 * Return unmodified
	 */
	public Collection<Rates> getCountryRates() {
        return Collections.unmodifiableSet(countryRates);
    }
	
	/** Returns sorted value.
	 * @param rateName Specify which rate type use for sorting
	 * @param ascending Specify the order (the lowest = true|the highest = false)
	*/
	public List<Rates> getSortedCountryRates(String rateName, boolean ascending) {
		
		Comparator<Rates> comparator = 
				(Rates o1, Rates o2)->o1.getCurrentRates().get(rateName).compareTo(o2.getCurrentRates().get(rateName));
        
        return countryRates.stream()
                .filter(rate -> rate.getCurrentRates().get(rateName) != null)
                .sorted(ascending ? comparator : comparator.reversed())
                .collect(Collectors.toList());

    }
}
