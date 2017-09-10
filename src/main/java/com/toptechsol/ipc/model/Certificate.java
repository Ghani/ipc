package com.toptechsol.ipc.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "certificate")
public class Certificate {

	@Id
	@Column(name = "id", unique = true, nullable = false, updatable=false)
	private Long id;

	@Column(name = "filename", nullable = false, length = 50)
	@NotNull
	private String filename;
	
	
	@Column( name = "certificate" )
	@Lob 
	@Basic(fetch = FetchType.LAZY)
	@NotNull
	private byte[] content;
	
	@Column(name="type", length=100, nullable=false)
	private String type;
	
	
	public Certificate(){
		
	}
	
	public Certificate(Long id, String filename, byte[] content, String type){
		this.id=id;
		this.filename=filename;
		this.content=content;
		this.type=type;
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

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
	
	

}