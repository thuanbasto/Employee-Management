package com.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeHeartDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer employee_id;

	private String name;

	private String address;

	private String description;

	private String phone;
	
	private String imageUrl;

	private String placeOfBirth;

	private Byte status;
	
	private Boolean deletedFlag;

	private Boolean married;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+07:00")
	private Date birthday;

	private String departmentName;

	private Long numberOfHearts;
	
	public EmployeeHeartDTO() {
	}
	
	@Override
	public String toString() {
		return "EmployeeHeartDTO [employee_id=" + employee_id + ", name=" + name + ", address=" + address
				+ ", description=" + description + ", phone=" + phone + ", imageUrl=" + imageUrl + ", placeOfBirth="
				+ placeOfBirth + ", status=" + status + ", deletedFlag=" + deletedFlag + ", married=" + married
				+ ", birthday=" + birthday + ", departmentName=" + departmentName + ", numberOfHearts=" + numberOfHearts
				+ "]";
	}



	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Boolean getMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getNumberOfHearts() {
		return numberOfHearts;
	}

	public void setNumberOfHearts(Long numberOfHearts) {
		this.numberOfHearts = numberOfHearts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}