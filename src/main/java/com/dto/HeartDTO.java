package com.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.entities.HeartId;
import com.fasterxml.jackson.annotation.JsonFormat;

public class HeartDTO {
	@NotNull
	private HeartId heartId;

	@NotNull
	private Boolean status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+07:00")
	private Date createdDate;

	public HeartDTO() {
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

}
