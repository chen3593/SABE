package edu.umn.csci5801.sabe.model;

import java.math.BigDecimal;

public class Adjustment {

	private String name;
	private BigDecimal amountPerSemester;
	private AdjustmentKind category;
	
	public Adjustment() {
		
	}

	public Adjustment(String name, BigDecimal amountPerSemester,
			AdjustmentKind category) {
		this.name = name;
		this.amountPerSemester = amountPerSemester;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmountPerSemester() {
		return amountPerSemester;
	}

	public void setAmountPerSemester(BigDecimal amountPerSemester) {
		this.amountPerSemester = amountPerSemester;
	}

	public AdjustmentKind getCategory() {
		return category;
	}

	public void setCategory(AdjustmentKind category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((amountPerSemester == null) ? 0 : amountPerSemester
						.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adjustment other = (Adjustment) obj;
		if (amountPerSemester == null) {
			if (other.amountPerSemester != null)
				return false;
		} else if (!amountPerSemester.equals(other.amountPerSemester))
			return false;
		if (category != other.category)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
