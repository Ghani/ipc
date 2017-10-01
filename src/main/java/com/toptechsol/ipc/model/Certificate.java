package com.toptechsol.ipc.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "certificate")
public class Certificate {

	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

	@Column(name = "filename", nullable = false, length = 50)
	@NotNull
	private String filename;
	
	
	@Column( name = "content" )
	@Lob 
	@Basic(fetch = FetchType.LAZY)
	@NotNull
	private byte[] content;
	
	
	@Column(name="file_type", length=100, nullable=false)
	private String fileType;
	
	
	@Column(name = "date", nullable = true)
	@NotNull
	private Date date;
	
	@Column(name = "expiry_date", nullable = false)
	private Date expiryDate;
	
	@Column(name = "name", nullable = false, length = 50)
	@NotNull
	private String name;
	
	@Column(name = "period", nullable = true)
	@NotNull
	private Integer period;
	
	
	@Column(name="type", nullable=false)
	private Integer type;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	
	public Certificate(){
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		Calendar myCal = Calendar.getInstance();
		myCal.setTime(date); 
		switch (period) {
		  case 0:
			 myCal.add(Calendar.MONTH, +3);
		     break;
		  case 1: 
			  myCal.add(Calendar.MONTH, +6);
			  break;
		  case 2:
			  myCal.add(Calendar.YEAR, +1);
			  break;
		  case 3:
			  myCal.add(Calendar.YEAR, +3);
			  break;
		  case 4: 
			  myCal.add(Calendar.YEAR, +5);
			  break;
		  default:
			  break;
		}
		this.expiryDate = myCal.getTime();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Transient
	public String getTypeName() {
		switch (type) {
		  case 0:
		        return Constants.DocumentType.CERTIFICATE.getName(); 
		  case 1: 
			    return Constants.DocumentType.MAINTENANCE.getName(); 
		  default:
			  return Constants.DocumentType.Extra.getName();
		}
	}

	
	@Transient
	public String getPeriodName() {
		switch (period) {
		  case 0:
		        return Constants.Period.THREE_MONTHS.getName(); 
		  case 1: 
			    return Constants.Period.SIX_MONTHS.getName(); 
		  case 2:
			    return Constants.Period.ONE_YEAR.getName(); 
		  case 3:        
			    return Constants.Period.THREE_YEAR.getName();
		  case 4: 
			    return Constants.Period.FIVE_YEARS_YEAR.getName();
		  default:
			  return Constants.Period.NOT_DEFINED.getName();
		}
	}

	
	
	
    
	

}