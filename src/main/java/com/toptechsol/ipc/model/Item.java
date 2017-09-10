package com.toptechsol.ipc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

//Type	Size	Length ft

@Entity
@Table(name = "item")
public class Item {

	@Id
	@Column(name = "serial_number", unique = true, nullable = false, length = 50)
	private String serialNumber;

	@Column(name = "name", nullable = false, length = 50)
	@NotNull
	private String name;

	@Column(name = "description", nullable = true, length = 100)
	private String description;

	@Column(name = "note", nullable = true, length = 100)
	private String note;

	@Column(name = "manufacture_name", nullable = false, length = 50)
	@NotNull
	private String manufactureName;

	@Column(name = "manufacture_serial_number", nullable = false, length = 50)
	@NotNull
	private String manufactureSerialNumber;

	@Column(name = "certification_date", nullable = false)
	@NotNull
	private Date certificationDate;

	@Column(name = "certification_expired_date", nullable = false)
	@NotNull
	private Date certificationExpiredDate;

	@Column(name = "certificat")
	@Lob
	private byte[] certificat;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the certificationDate
	 */
	public Date getCertificationDate() {
		return certificationDate;
	}

	/**
	 * @param certificationDate
	 *            the certificationDate to set
	 */
	public void setCertificationDate(Date certificationDate) {
		this.certificationDate = certificationDate;
	}

	/**
	 * @return the category
	 */
	public Category getNode() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setNode(Category category) {
		this.category = category;
	}

	/**
	 * @return the certificationExpiredDate
	 */
	public Date getCertificationExpiredDate() {
		return certificationExpiredDate;
	}

	/**
	 * @param certificationExpiredDate
	 *            the certificationExpiredDate to set
	 */
	public void setCertificationExpiredDate(Date certificationExpiredDate) {
		this.certificationExpiredDate = certificationExpiredDate;
	}

	/**
	 * @return the manufactureName
	 */
	public String getManufactureName() {
		return manufactureName;
	}

	/**
	 * @param manufactureName
	 *            the manufactureName to set
	 */
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	/**
	 * @return the manufactureSerialNumber
	 */
	public String getManufactureSerialNumber() {
		return manufactureSerialNumber;
	}

	/**
	 * @param manufactureSerialNumber
	 *            the manufactureSerialNumber to set
	 */
	public void setManufactureSerialNumber(String manufactureSerialNumber) {
		this.manufactureSerialNumber = manufactureSerialNumber;
	}

	/**
	 * @return the certificat
	 */
	public byte[] getCertificat() {
		return certificat;
	}

	/**
	 * @param certificat the certificat to set
	 */
}