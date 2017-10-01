package com.toptechsol.ipc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "item")
public class Item {
	@Id
	@Column(name = "item_id", unique = true, nullable = false, updatable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "serial_number", unique = true, nullable = false, updatable=true,length = 50)
	private String serialNumber;


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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	
	@OneToMany(targetEntity = Certificate.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name = "item_id")
	private List<Certificate> certificates = new ArrayList<Certificate>() ;
	
	
	
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



	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	public String getInspectorCompanySerialNumber() {
		return inspectorCompanySerialNumber;
	}

	public void setInspectorCompanySerialNumber(String inspectorCompanySerialNumber) {
		this.inspectorCompanySerialNumber = inspectorCompanySerialNumber;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", serialNumber=" + serialNumber  + ", description=" + description
				+ ", note=" + note + ", manufactureName=" + manufactureName + ", manufactureSerialNumber="
				+ manufactureSerialNumber + ", inspectorCompanySerialNumber=" + inspectorCompanySerialNumber
				+ ", category=" + category + "]";
	}
	
	
	

}