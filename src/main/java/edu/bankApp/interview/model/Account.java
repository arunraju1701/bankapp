package edu.bankApp.interview.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import edu.bankApp.interview.forms.AccountType;

@Entity(name = "Account")
@Table(name = "accounts")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "acc_no", nullable = false, length = 500)
	private Long accNo;

	@Column(name = "acc_type")
	private AccountType accType;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(targetEntity = Bank.class)
	@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private Bank bank;

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

	public Long getAccNo() {
		return accNo;
	}

	public void setAccNo(Long accNo) {
		this.accNo = accNo;
	}

	public AccountType getAccType() {
		return accType;
	}

	public void setAccType(AccountType accType) {
		this.accType = accType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
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

	@PrePersist
	void onPrePersist() {
		this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	}

	@PreUpdate
	void onPreUpdate() {
		this.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accNo=" + accNo + ", accType=" + accType + ", user=" + user + ", bank=" + bank
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
