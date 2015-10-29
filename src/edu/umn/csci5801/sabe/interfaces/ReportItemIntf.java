package edu.umn.csci5801.sabe.interfaces;

import java.math.BigDecimal;

public interface ReportItemIntf {
	
	/**
	 * @return The name of this item on the report.
	 */
	public String getName();
	
	/**
	 * @return The category that this item belongs to.
	 */
	public String getCategory();
	
	/**
	 * @return The charge or credit for this item. A positive charge represents
	 * something that the student must pay for, a negative charge represents
	 * a payment or credit for the student. 
	 */
	public BigDecimal getAmount();
	
	@Override
	public boolean equals(Object o);
}