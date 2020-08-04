package com.cwiztech.contituency.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cwiztech.login.model.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = " TBLELECTIONCONTITUENCYPOLLINGSTATION")
public class ElectionContituencyPollingStation  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long POLLINGSTATION_ID;
	
	@Column(name=" CONTITUENCY_ID")
	private long  CONTITUENCY_ID;
	
	@Column(name="POLLINGSTATION_CODE")
	private String POLLINGSTATION_CODE;
	
	@Column(name="DESCRIPTION")
	private String DESCRIPTION;

	@Column(name = "LANGITUDE")
	private String  LANGITUDE;
	
	@Column(name = "LATITUDE")
	private String LATITUDE;
	
	@Column(name = "VALID_VOTES")
	private long VALID_VOTES;
	
	@Column(name = "REJECTED_VOTES")
	private long REJECTED_VOTES;
	
	@Column(name = "ISACTIVE ")
	private String ISACTIVE ;
	
	@JsonIgnore
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "MODIFIED_BY")
	 private LoginUser MODIFIED_BY;
	
	@JsonIgnore
	@Column(name = "MODIFIED_WHEN ")
	private String   MODIFIED_WHEN ;
	
	@JsonIgnore
	@Column(name = "MODIFIED_WORKSTATION  ")
	private String MODIFIED_WORKSTATION    ;
	
	


	public long getPOLLINGSTATION_ID() {
		return POLLINGSTATION_ID;
	}




	public void setPOLLINGSTATION_ID(long pOLLINGSTATION_ID) {
		POLLINGSTATION_ID = pOLLINGSTATION_ID;
	}




	public long getCONTITUENCY_ID() {
		return CONTITUENCY_ID;
	}




	public void setCONTITUENCY_ID(long cONTITUENCY_ID) {
		CONTITUENCY_ID = cONTITUENCY_ID;
	}




	public String getPOLLINGSTATION_CODE() {
		return POLLINGSTATION_CODE;
	}




	public void setPOLLINGSTATION_CODE(String pOLLINGSTATION_CODE) {
		POLLINGSTATION_CODE = pOLLINGSTATION_CODE;
	}


	

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}




	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}




	public String getLANGITUDE() {
		return LANGITUDE;
	}




	public void setLANGITUDE(String lANGITUDE) {
		LANGITUDE = lANGITUDE;
	}




	public String getLATITUDE() {
		return LATITUDE;
	}




	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}




	public long getVALID_VOTES() {
		return VALID_VOTES;
	}




	public void setVALID_VOTES(long vALID_VOTES) {
		VALID_VOTES = vALID_VOTES;
	}




	public long getREJECTED_VOTES() {
		return REJECTED_VOTES;
	}




	public void setREJECTED_VOTES(long rEJECTED_VOTES) {
		REJECTED_VOTES = rEJECTED_VOTES;
	}




	public String getISACTIVE() {
		return ISACTIVE;
	}




	public void setISACTIVE(String iSACTIVE) {
		ISACTIVE = iSACTIVE;
	}




	public LoginUser getMODIFIED_BY() {
		return MODIFIED_BY;
	}




	public void setMODIFIED_BY(LoginUser mODIFIED_BY) {
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