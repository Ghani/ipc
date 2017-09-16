package com.toptechsol.ipc.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//Type	Size	Length ft

@Entity
@Table(name = "item")
public class Item {
	@Id
	@Column(name = "id", unique = true, nullable = false, updatable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "serial_number", unique = true, nullable = false, updatable=true,length = 50)
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
	
	
	@Column(name = "inspector_company_serial_number", nullable = false, length = 50)
	@NotNull
	private String inspectorCompanySerialNumber;

	@Column(name = "certification_date", nullable = false)
	@NotNull
	private Date certificationDate;

	@Column(name = "certification_expired_date", nullable = false)
	@NotNull
	private Date certificationExpiredDate;

	 
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private Certificate certificate;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
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
	 * @return the certificate
	 */
	public Certificate getCertificate() {
		return certificate;
	}

	/**
	 * @param certificate the certificate to set
	 */
	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public String getInspectorCompanySerialNumber() {
		return inspectorCompanySerialNumber;
	}

	public void setInspectorCompanySerialNumber(String inspectorCompanySerialNumber) {
		this.inspectorCompanySerialNumber = inspectorCompanySerialNumber;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", serialNumber=" + serialNumber + ", name=" + name + ", description=" + description
				+ ", note=" + note + ", manufactureName=" + manufactureName + ", manufactureSerialNumber="
				+ manufactureSerialNumber + ", inspectorCompanySerialNumber=" + inspectorCompanySerialNumber
				+ ", certificationDate=" + certificationDate + ", certificationExpiredDate=" + certificationExpiredDate
				+ ", category=" + category + ", certificate=" + certificate + "]";
	}
	
	
	

}