package edu.umn.csci5801.sabe.main;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.umn.csci5801.sabe.interfaces.AccountReportIntf;
import edu.umn.csci5801.sabe.interfaces.ReportItemIntf;

public class AccountReport implements AccountReportIntf {
	Map<String, Set<ReportItemIntf>> items;
	BigDecimal amount;

	@Override
	public Map<String, Set<ReportItemIntf>> getItemsByCategory() {
		return items;
	}

	@Override
	public BigDecimal getAccountBalance() {
		return amount;
	}

	/**
	 * Constructor of AccountReport, passed in the result returned by OverallCalculator.
	 * It will transfer the the format<name, ReportItem> to <categoryName, SetOfReportItem> (one designed fault)
	 * @param calculatedItems items returned by the OverallCalculator
	 * @param amount total amount of entries, result from OverallCalculator;
	 */
	public AccountReport(Map<String, ReportItem> calculatedItems,
			BigDecimal amount) {
		this.amount = amount;
		items = new HashMap<String, Set<ReportItemIntf>>();
		Iterator<Entry<String, ReportItem>> iterator = calculatedItems
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry pair = (Map.Entry) iterator.next();
			ReportItem item = (ReportItem) pair.getValue();
			if (items.containsKey(item.getCategory())) {
				items.get(item.getCategory()).add(item);
			} else {
				Set<ReportItemIntf> itemSet = new HashSet<ReportItemIntf>();
				itemSet.add(item);
				items.put(item.getCategory(), itemSet);
			}
		}
	}
}
