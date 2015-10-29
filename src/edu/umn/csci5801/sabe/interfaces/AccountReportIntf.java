package edu.umn.csci5801.sabe.interfaces;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface AccountReportIntf {

	/**
	 * Get data for the account report. Each key in the map will be "displayed"
	 * as a category, with a number of report items contained within that 
	 * category. 
	 * @return data for an account report. 
	 */
	public Map<String,Set<ReportItemIntf>> getItemsByCategory();
	
	/**
	 * @return The final balance of the student's account. A positive value
	 * means that the student owes money. 
	 */
	public BigDecimal getAccountBalance();
}
