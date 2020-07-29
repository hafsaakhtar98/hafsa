package com.cwiztech.contituency.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " TBLELECTIONCONTITUENCY")
public class ElectionContituency {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long CONTITUENCY_ID;
	
	@Column(name=" ELECTION_ID")
	private long  ELECTION_ID;
	
	@Column(name="ASSEMBLY_ID")
	private long ASSEMBLY_ID;
	
	@Column(name="DISTRICT_ID")
	private long DISTRICT_ID;

	@Column(name = "CONTITUENCY_CODE")
	private String  CONTITUENCY_CODE;
	
	@Column(name = "CONTITUENCY_DESC  ")
	private String CONTITUENCY_DESC ;
	
	@Column(name = "ISACTIVE ")
	private String ISACTIVE ;
	
	@Column(name = "MODIFIED_BY")
	private long MODIFIED_BY;
	
	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;
	
	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;
	

	public long getCONTITUENCY_ID() {
		return CONTITUENCY_ID;
	}

	public void setCONTITUENCY_ID(long cONTITUENCY_ID) {
		CONTITUENCY_ID = cONTITUENCY_ID;
	}

	public long getELECTION_ID() {
		return ELECTION_ID;
	}

	public void setELECTION_ID(long eLECTION_ID) {
		ELECTION_ID = eLECTION_ID;
	}

	public long getASSEMBLY_ID() {
		return ASSEMBLY_ID;
	}

	public void setASSEMBLY_ID(long aSSEMBLY_ID) {
		ASSEMBLY_ID = aSSEMBLY_ID;
	}

	public long getDISTRICT_ID() {
		return DISTRICT_ID;
	}

	public void setDISTRICT_ID(long dISTRICT_ID) {
		DISTRICT_ID = dISTRICT_ID;
	}

	public String getCONTITUENCY_CODE() {
		return CONTITUENCY_CODE;
	}

	public void setCONTITUENCY_CODE(String cONTITUENCY_CODE) {
		CONTITUENCY_CODE = cONTITUENCY_CODE;
	}

	public String getCONTITUENCY_DESC() {
		return CONTITUENCY_DESC;
	}

	public void setCONTITUENCY_DESC(String cONTITUENCY_DESC) {
		CONTITUENCY_DESC = cONTITUENCY_DESC;
	}

	
	public String getISACTIVE() {
		return ISACTIVE;
	}

	public void setISACTIVE(String iSACTIVE) {
		ISACTIVE = iSACTIVE;
	}

	public long getMODIFIED_BY() {
		return MODIFIED_BY;
	}


	public void setMODIFIED_BY(long mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}


	public String getMODIFIED_WHEN() {
		return MODIFIED_WHEN;
	}


	public void setMODIFIED_WHEN(String mODIFIED_WHEN) {
		MODIFIED_WHEN = mODIFIED_WHEN;
	}


	public String getMODIFIED_WORKSTATION() {
		return MODIFIED_WORKSTATION;
	}


	public void setMODIFIED_WORKSTATION(String mODIFIED_WORKSTATION) {
		MODIFIED_WORKSTATION = mODIFIED_WORKSTATION;
	}

	public static long getDatabaseTableID() {
		return (long) 3;
	}

	
}