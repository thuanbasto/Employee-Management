package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Heart {

	@EmbeddedId
	private HeartId heartId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "employee_id")
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "user_id")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(nullable = true)
	private Boolean status;

	@Column(name = "created_date", columnDefinition = "datetime(0)")
	private Date createdDate;

	public Heart() {
	}

	public HeartId getHeartId() {
		return heartId;
	}

	public void setHeartId(HeartId heartId) {
		this.heartId = heartId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
