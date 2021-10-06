package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employee_id;

	@Column(columnDefinition = "nvarchar(100)")
//	@Pattern(regexp = "^[a-zA-Z]{3,}(?: [a-zA-Z]+)*$", message = "Name should be 3 - 100 characters")
//	@Length(min = 3, max = 100, message = "Name should be 3 - 100 characters")
	private String name;

	@Column(columnDefinition = "nvarchar(255)")
	private String address;

	@Column(columnDefinition = "nvarchar(2000)")
	private String description;

	@Column(columnDefinition = "varchar(10)")
//	@Pattern(regexp = "^[0-9]{9,10}$", message = "Phone should be 9 - 10 numbers")
	private String phone;

	@Column(columnDefinition = "varchar(100)")
	private String imageUrl;

	@Column(columnDefinition = "nvarchar(255)")
	private String placeOfBirth;

	@Column(nullable = true, columnDefinition = "tinyint(4) default 0")
	private Byte status;

	@Column(nullable = true)
	private Boolean married;

	@Column(nullable = true, columnDefinition = "bit(1) default 0")
	private Boolean deletedFlag;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+07:00")
	private Date birthday;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	private Set<Heart> hearts;
	
	public Employee() {
	}
	
	public Employee(Integer id) {
		this.employee_id = id;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
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

	public Set<Heart> getHearts() {
		return hearts;
	}

	public void setHearts(Set<Heart> hearts) {
		this.hearts = hearts;
	}

}