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
@Table(name = " TBLELECTIONCONTITUENCYPOLLINGSTATIONRESULT")
public class  ElectionContituencyPollingStationResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long POLLINGSTATIONRESULT_ID;
	
	@Column(name=" POLLINGSTATION_ID")
	private long  POLLINGSTATION_ID;
	
	@Column(name="CANDIDATE_ID")
	private long CANDIDATE_ID;
	
	@Column(name = "RESULTFROM_ID")
	private long  RESULTFROM_ID;
	
	@Column(name = "OBTAINED_VOTES")
	private long  OBTAINED_VOTES;
	
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
	
	
	public long getPOLLINGSTATIONRESULT_ID() {
		return POLLINGSTATIONRESULT_ID;
	}


	public void setPOLLINGSTATIONRESULT_ID(long pOLLINGSTATIONRESULT_ID) {
		POLLINGSTATIONRESULT_ID = pOLLINGSTATIONRESULT_ID;
	}


	public long getPOLLINGSTATION_ID() {
		return POLLINGSTATION_ID;
	}


	public void setPOLLINGSTATION_ID(long pOLLINGSTATION_ID) {
		POLLINGSTATION_ID = pOLLINGSTATION_ID;
	}


	public long getCANDIDATE_ID() {
		return CANDIDATE_ID;
	}


	public void setCANDIDATE_ID(long cANDIDATE_ID) {
		CANDIDATE_ID = cANDIDATE_ID;
	}


	public long getRESULTFROM_ID() {
		return RESULTFROM_ID;
	}


	public void setRESULTFROM_ID(long rESULTFROM_ID) {
		RESULTFROM_ID = rESULTFROM_ID;
	}


	public long getOBTAINED_VOTES() {
		return OBTAINED_VOTES;
	}


	public void setOBTAINED_VOTES(long oBTAINED_VOTES) {
		OBTAINED_VOTES = oBTAINED_VOTES;
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