package com.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer employee_id;

//	@Pattern(regexp = "^[a-zA-Z]{2,}(?: [a-zA-Z]+)*$", message = "Name should be 3 - 100 characters")
	@NotBlank
	@Length(min = 3, max = 100, message = "Name should be 2 - 100 characters")
	private String name;

	@Length(min = 3, max = 255, message = "Address should be 3 - 255 characters")
	@NotBlank
	private String address;

	@Length(max = 2000, message = "Description should be maximum 2000 characters")
	private String description;

	@Pattern(regexp = "^[0-9]{9,10}$", message = "Phone should be 9 - 10 numbers")
	private String phone;
	
	private String imageUrl;

	@Length(min = 3, max = 255, message = "Place of birth should be 3 - 255 characters")
	@NotBlank
	private String placeOfBirth;

	private Byte status;
	
	private Boolean deletedFlag;

	private Boolean married;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+07:00")
	@Past(message = "Birthday should be less than current date")
	@NotNull
	private Date birthday;
	
	private MultipartFile file;

	@NotNull
	private DepartmentDTO department;

	public EmployeeDTO() {
	}
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

}