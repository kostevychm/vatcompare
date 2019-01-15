package me.kostevych.vatcompare;

import org.junit.Test;
import java.io.IOException;
/**
 * Unit test for json.
 */
public class LoadFromSourceTest{
	@Test(expected = IOException.class)
    public void unknowUrl() throws Exception {
		LoadFromSource source = new LoadFromSource("NotExistingUrl");
		source.storeCountryRates();
    }
	
	@Test(expected = IOException.class)
    public void urlWithoutJson() throws Exception {
		LoadFromSource source = new LoadFromSource("http://kostevych.me");
		source.storeCountryRates();
    }
}
