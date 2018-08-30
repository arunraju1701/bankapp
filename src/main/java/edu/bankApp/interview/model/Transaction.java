package edu.bankApp.interview.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.bankApp.interview.forms.TransactionType;

@Entity(name = "Transaction")
@Table(name = "transactions")
@JsonIgnoreProperties({"user","account"})

public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "transaction_id", length = 15, unique = true, nullable = false)
	private String transactionId;

	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	@Column(name = "transaction_amount")
	private BigDecimal amount;

	@Column(name = "type")
	private TransactionType type;

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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
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
		return "Transaction [id=" + id + ", transactionId=" + transactionId + ", account=" + account + ", amount="
				+ amount + ", type=" + type + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
