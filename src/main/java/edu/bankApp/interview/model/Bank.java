package edu.bankApp.interview.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity(name = "Bank")
@Table(name = "banks")
public class Bank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 120)
	private String name;

	@Column(name = "corpId", nullable = true)
	private Long corp_id;

	@Column(name = "user_name_required")
	private boolean isUserNameRequired;

	@Column(name = "password_required")
	private boolean isPasswordRequired;

	@Column(name = "address")
	private String address;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(Long corp_id) {
		this.corp_id = corp_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isUserNameRequired() {
		return isUserNameRequired;
	}

	public void setUserNameRequired(boolean isUserNameRequired) {
		this.isUserNameRequired = isUserNameRequired;
	}

	public boolean isPasswordRequired() {
		return isPasswordRequired;
	}

	public void setPasswordRequired(boolean isPasswordRequired) {
		this.isPasswordRequired = isPasswordRequired;
	}

	@PrePersist
	void onPrePersist() {
		this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	}

	@PreUpdate
	void onPreUpdate() {
		this.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

	}
}
