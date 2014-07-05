package org.yonko.mist.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PASSPORT")
public class Passport implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	
	@NotNull(message="Номер паспорта должен содержать 10 символов")
	@Size(min = 10, max = 10, message="Номер паспорта должен содержать 10 символов")
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;
	
	@NotNull
	@Column(name = "ISSUED")
	private String issued;
	
	@NotNull
	@Column(name = "ISSUED_DATE")
	private Date issuedDate;
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getIssued() {
		return issued;
	}

	public void setIssued(String issued) {
		this.issued = issued;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
