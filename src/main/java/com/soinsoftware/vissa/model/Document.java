package com.soinsoftware.vissa.model;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Document extends CommonData {
	/*
	 * 
	 */
	private static final long serialVersionUID = -3912660464232727268L; 
	
	private String number;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private DocumentType documentType;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;	
	@JoinColumn(name = "document_date")
	private Date documentDate;
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;
	@ManyToOne
	@JoinColumn(name = "payment_method_id")
	private PaymentType paymentMethod;
	@JoinColumn(name = "payment_term")
	private String paymentTerm;
	@JoinColumn(name = "expiration_date")
	private Date expirationDate;
	@ManyToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	@JoinColumn(name = "total_value_no_tax")
	private Currency totalValueNoTax;
	@JoinColumn(name = "total_value")
	private Currency totalValue;
	
	public Document() {
		super();
	}

	
	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getNumber() {
		return number;
	}


	public DocumentType getDocumentType() {
		return documentType;
	}


	public Person getPerson() {
		return person;
	}


	public Date getDocumentDate() {
		return documentDate;
	}


	public PaymentType getPaymentType() {
		return paymentType;
	}


	public PaymentType getPaymentMethod() {
		return paymentMethod;
	}


	public String getPaymentTerm() {
		return paymentTerm;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public Currency getCurrency() {
		return currency;
	}


	public Currency getTotalValueNoTax() {
		return totalValueNoTax;
	}


	public Currency getTotalValue() {
		return totalValue;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}


	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}


	public void setPaymentMethod(PaymentType paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	public void setTotalValueNoTax(Currency totalValueNoTax) {
		this.totalValueNoTax = totalValueNoTax;
	}


	public void setTotalValue(Currency totalValue) {	
		this.totalValue = totalValue;
	}
	
	

}
