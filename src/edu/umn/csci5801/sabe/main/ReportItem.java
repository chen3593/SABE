package edu.umn.csci5801.sabe.main;

import java.math.BigDecimal;

import edu.umn.csci5801.sabe.interfaces.ReportItemIntf;

public class ReportItem implements ReportItemIntf{
	private String name;
	private String category;
	private BigDecimal amount;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * Constructor of ReportItem
	 * @param name name of fee
	 * @param category category of fee
	 * @param amount total amount of the fee
	 */
	public ReportItem(String name, String category, BigDecimal amount) {
		this.name = name;
		this.category = category;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "name:" + name + " category:" + category + " amout" + amount.floatValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		ReportItem item = (ReportItem) obj;
		Float thisAmountFloat = amount.floatValue();
		boolean isAmountEqual = thisAmountFloat.equals(item.getAmount().floatValue());
		return name.equals(item.getName()) && category.equals(item.getCategory()) && isAmountEqual;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}
}
