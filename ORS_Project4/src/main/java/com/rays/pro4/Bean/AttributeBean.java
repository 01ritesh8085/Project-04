package com.rays.pro4.Bean;

public class AttributeBean extends BaseBean {
	private String displayeName;
	private String dataType;
	private String IsActive;
	private String description;

	public String getdisplayeName() {
		return displayeName;
	}

	public void setdisplayeName(String displayeName) {
		this.displayeName = displayeName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String isActive) {
		IsActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
