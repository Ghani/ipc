package com.toptechsol.ipc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "certificate")
public class Certificate {

	@Id
	@Column(name = "id", unique = true, nullable = false, updatable=false)
	private Long id;

	@Column(name = "filename", nullable = false, length = 50)
	@NotNull
	private String filename;
	
	
	
	public Certificate(){
		
	}
	
	public Certificate(Long id, String filename){
		this.id=id;
		this.filename=filename;
	}
	

	/**
	 * @return the serialNumber
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}