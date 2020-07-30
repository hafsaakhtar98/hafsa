package com.cwiztech.contituency.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cwiztech.login.model.LoginUser;
import com.cwiztech.systemsetting.model.Lookup;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "TBLELECTIONCONTITUENCYPOLLINGSTATIONDETAIL")
public class ElectionContituencyPollingStationDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long POLLINGSATTIONDETAIL_ID;
	
	@ManyToOne
	@JoinColumn(name = "POLLINGSTATION_ID")
	private ElectionContituencyPollingStation POLLINGSTATION_ID;
	
	@ManyToOne
	@JoinColumn(name = "AREATYPE_ID")
	private Lookup AREATYPE_ID;
	
	@Column(name = "ELECTORALAREA")
	private String ELECTORALAREA;
	
	@Column(name = "BLOCKCODE")
	private String BLOCKCODE;
	
	@Column(name = "GANDER")
	private String GANDER;
	
	@Column(name = "ASSIGNEDVOTERS")
	private String ASSIGNEDVOTERS;
	
	@Column(name = "TOTALPOLLINGBOOTHS")
	private String TOTALPOLLINGBOOTHS;
	
	@Column(name = "ISACTIVE")
	private String ISACTIVE;
	
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "MODIFIED_BY")
	private LoginUser MODIFIED_BY;
	
	@JsonIgnore
	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;
	
	@JsonIgnore
	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getPOLLINGSATTIONDETAIL_ID() {
		return POLLINGSATTIONDETAIL_ID;
	}

	public void setPOLLINGSATTIONDETAIL_ID(long pOLLINGSATTIONDETAIL_ID) {
		POLLINGSATTIONDETAIL_ID = pOLLINGSATTIONDETAIL_ID;
	}

	public ElectionContituencyPollingStation getPOLLINGSTATION_ID() {
		return POLLINGSTATION_ID;
	}

	public void setPOLLINGSTATION_ID(ElectionContituencyPollingStation pOLLINGSTATION_ID) {
		POLLINGSTATION_ID = pOLLINGSTATION_ID;
	}

	public Lookup getAREATYPE_ID() {
		return AREATYPE_ID;
	}

	public void setAREATYPE_ID(Lookup aREATYPE_ID) {
		AREATYPE_ID = aREATYPE_ID;
	}

	public String getELECTORALAREA() {
		return ELECTORALAREA;
	}

	public void setELECTORALAREA(String eLECTORALAREA) {
		ELECTORALAREA = eLECTORALAREA;
	}

	public String getBLOCKCODE() {
		return BLOCKCODE;
	}

	public void setBLOCKCODE(String bLOCKCODE) {
		BLOCKCODE = bLOCKCODE;
	}

	public String getGANDER() {
		return GANDER;
	}

	public void setGANDER(String gANDER) {
		GANDER = gANDER;
	}

	public String getASSIGNEDVOTERS() {
		return ASSIGNEDVOTERS;
	}

	public void setASSIGNEDVOTERS(String aSSIGNEDVOTERS) {
		ASSIGNEDVOTERS = aSSIGNEDVOTERS;
	}

	public String getTOTALPOLLINGBOOTHS() {
		return TOTALPOLLINGBOOTHS;
	}

	public void setTOTALPOLLINGBOOTHS(String tOTALPOLLINGBOOTHS) {
		TOTALPOLLINGBOOTHS = tOTALPOLLINGBOOTHS;
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
