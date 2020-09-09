package com.cwiztech.contituency.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cwiztech.contituency.model.ElectionContituency;
import com.cwiztech.contituency.repository.electionContituencyRepository;
import com.cwiztech.datalogs.model.APIRequestDataLog;
import com.cwiztech.datalogs.model.DatabaseTables;
import com.cwiztech.datalogs.model.tableDataLogs;
import com.cwiztech.datalogs.repository.apiRequestDataLogRepository;
import com.cwiztech.datalogs.repository.databaseTablesRepository;
import com.cwiztech.datalogs.repository.tableDataLogRepository;
import com.cwiztech.login.model.LoginUser;
import com.cwiztech.login.repository.loginUserRepository;

import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/electioncontituency")
public class electionContituencyController {
	
	private static final Logger log = LoggerFactory.getLogger(electionContituencyController.class);
	@Autowired
	private electionContituencyRepository electioncontituencyrepository;
	
	@Autowired
	private loginUserRepository loginuserrepository;
	
	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;
	
	@Autowired
	private tableDataLogRepository tbldatalogrepository;
	
	@Autowired
	private databaseTablesRepository databasetablesrepository;
	


	@RequestMapping(method = RequestMethod.GET)
	public  String get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		log.info("GET: /electioncontituency");
		
		
		String  rtn,workstation=null ;
		List<ElectionContituency>electioncontituency=  electioncontituencyrepository.findActive();
		
		
		LoginUser requestUser;
		String user_NAME = AccessToken.getTokenDetail(headToken);
		requestUser = loginuserrepository.getUser(user_NAME);

		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituency.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituency", null,
				workstation);
 
		rtn=mapper.writeValueAsString(electioncontituency);
		apiRequest.setREQUEST_OUTPUT(rtn);
		apiRequest.setREQUEST_STATUS("Success");
		apirequestdatalogRepository.saveAndFlush(apiRequest);

		log.info("Output: " + rtn);
		log.info("--------------------------------------------------------");
		return rtn;
	}
	  
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public  String getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		log.info("GET: /electioncontituency");
		
		String  rtn,workstation=null;
	
		List<ElectionContituency> electioncontituency=electioncontituencyrepository.findAll();
		LoginUser requestUser;
		String user_NAME = AccessToken.getTokenDetail(headToken);
		requestUser = loginuserrepository.getUser(user_NAME);

		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituency.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituency", null,workstation);
		
		rtn=mapper.writeValueAsString(electioncontituency);
		apiRequest.setREQUEST_OUTPUT(rtn);
		apiRequest.setREQUEST_STATUS("Success");
		apirequestdatalogRepository.saveAndFlush(apiRequest);

		log.info("Output: " + rtn);
		log.info("--------------------------------------------------------");
		return rtn;
	}
	
	@RequestMapping(value="{id}" ,method = RequestMethod.GET)
	public  String getOne(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		log.info("GET: /electioncontituency" + id);
		String  rtn,workstation=null;
		
		ElectionContituency  electioncontituency = electioncontituencyrepository.findOne(id);
		LoginUser requestUser;
		String user_NAME = AccessToken.getTokenDetail(headToken);
		requestUser = loginuserrepository.getUser(user_NAME);

		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituency.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituency", null,workstation);
		
		
		rtn=mapper.writeValueAsString(electioncontituency); 
		apiRequest.setREQUEST_OUTPUT(rtn);
		apiRequest.setREQUEST_STATUS("Success");
		apirequestdatalogRepository.saveAndFlush(apiRequest);

		log.info("Output: " + rtn);
		log.info("--------------------------------------------------------");
		return rtn;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
	throws JsonProcessingException, JSONException, ParseException {
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
	Date date = new Date();
	ObjectMapper mapper = new ObjectMapper();

	log.info("POST: /electioncontituency");
	log.info("Input: " + data);

	JSONObject jsonObj = new JSONObject(data);
	ElectionContituency electioncontituency = new  ElectionContituency();
	String rtn, workstation = null;

	LoginUser requestUser;
	String user_NAME = AccessToken.getTokenDetail(headToken);
	requestUser = loginuserrepository.getUser(user_NAME);
	DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituency.getDatabaseTableID());
	
	if (jsonObj.has("workstation"))
	workstation = jsonObj.getString("workstation");
	APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser, "/electioncontituency", data,
	workstation);

	if (!jsonObj.has("contituency_ID")&& jsonObj.isNull("contituency_ID")) {
		apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituency", "contituency_ID  is missing");
		apirequestdatalogRepository.saveAndFlush(apiRequest);
		return apiRequest.getREQUEST_OUTPUT();
		}
	electioncontituency.setCONTITUENCY_ID(jsonObj.getLong("contituency_ID"));
	
	if (!jsonObj.has("election_ID")&& jsonObj.isNull("election_ID")) {
		apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituency", "election_ID  is missing");
		apirequestdatalogRepository.saveAndFlush(apiRequest);
		return apiRequest.getREQUEST_OUTPUT();
		}
	electioncontituency.setELECTION_ID(jsonObj.getLong("election_ID")); 
	
	if (!jsonObj.has("assembly_ID")&& jsonObj.isNull("assembly_ID")) {
		apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituency", "assembly_ID  is missing");
		apirequestdatalogRepository.saveAndFlush(apiRequest);
		return apiRequest.getREQUEST_OUTPUT();
		}
	electioncontituency.setASSEMBLY_ID(jsonObj.getLong("assembly_ID"));
	
	if (!jsonObj.has("district_ID")&& jsonObj.isNull("district_ID")) {
		apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituency", "district_ID  is missing");
		apirequestdatalogRepository.saveAndFlush(apiRequest);
		return apiRequest.getREQUEST_OUTPUT();
		}
	electioncontituency.setDISTRICT_ID(jsonObj.getLong("district_ID"));
	
		
	if (!jsonObj.has("contituency_DESC")&& jsonObj.isNull("contituency_DESC"))
	{
	apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituency", "contituency_DESC is missing");
	apirequestdatalogRepository.saveAndFlush(apiRequest);
	return apiRequest.getREQUEST_OUTPUT();
	}
	electioncontituency.setCONTITUENCY_DESC(jsonObj.getString("contituency_DESC"));
	 
	if (!jsonObj.has("contituency_CODE")&& jsonObj.isNull("contituency_CODE")) {
		apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituency", "contituency_CODE  is missing");
		apirequestdatalogRepository.saveAndFlush(apiRequest);
		return apiRequest.getREQUEST_OUTPUT();
		}
		 
	electioncontituency.setCONTITUENCY_CODE(jsonObj.getString("contituency_CODE"));
				
	//electioncontituency.setMODIFIED_BY(requestUser.getUSER_ID());
	electioncontituency.setMODIFIED_BY(requestUser);
	 
	electioncontituency.setISACTIVE("Y");
	electioncontituency.setMODIFIED_BY(requestUser);
	electioncontituency.setMODIFIED_WORKSTATION(workstation);
	electioncontituency.setMODIFIED_WHEN(dateFormat1.format(date));
	electioncontituency = electioncontituencyrepository.saveAndFlush(electioncontituency);
	rtn = mapper.writeValueAsString(electioncontituency);

	tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituency.getCONTITUENCY_ID(),
			databaseTableID, requestUser, rtn));

	apiRequest.setREQUEST_OUTPUT(rtn);
	apiRequest.setREQUEST_STATUS("Success");
	apirequestdatalogRepository.saveAndFlush(apiRequest);

	log.info("Output: " + rtn);
	log.info("--------------------------------------------------------");

	return rtn;

};

	
	@RequestMapping(value= "/{id}",method = RequestMethod.PUT)
	public String update(@PathVariable Long id,@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
	throws JsonProcessingException, JSONException, ParseException {
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
	Date date = new Date();
	ObjectMapper mapper = new ObjectMapper();

	
	log.info("PUT: /electioncontituency");
	log.info("Input: " + data);

	JSONObject jsonObj = new JSONObject(data);
	ElectionContituency  electioncontituency = electioncontituencyrepository.findOne(id);
	String rtn, workstation = null;
	LoginUser requestUser;
	String user_NAME = AccessToken.getTokenDetail(headToken);
	requestUser = loginuserrepository.getUser(user_NAME);

	DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituency.getDatabaseTableID());

	if (jsonObj.has("workstation"))
	workstation = jsonObj.getString("workstation");
	APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("PUT", databaseTableID, requestUser, "/electioncontituency/"+id, data,
	workstation);


	if (jsonObj.has("CONTITUENCY_ID") && !jsonObj.isNull("contituency_ID"))
		electioncontituency.setCONTITUENCY_ID(jsonObj.getLong("contituency_ID"));
	if (jsonObj.has("ASSEMBLY_ID"))
		electioncontituency.setASSEMBLY_ID(jsonObj.getLong("assembly_ID"));
	if (jsonObj.has("ELECTION_ID"))  
		electioncontituency.setELECTION_ID(jsonObj.getLong("election_ID"));
	if (jsonObj.has("DISTRICT_ID"))  
		electioncontituency.setDISTRICT_ID(jsonObj.getLong("district_ID"));
	if (jsonObj.has("CONTITUENCY_DESC"))  
		electioncontituency.setCONTITUENCY_DESC(jsonObj.getString("contituency_DESC"));
	if (jsonObj.has("CONTITUENCY_CODE"))
		electioncontituency.setCONTITUENCY_CODE(jsonObj.getString("contituency_CODE"));

	if (jsonObj.has("isactive"))
		electioncontituency.setISACTIVE(jsonObj.getString("isactive"));

	electioncontituency.setMODIFIED_BY(requestUser);
	electioncontituency.setMODIFIED_WORKSTATION(workstation);
	electioncontituency.setMODIFIED_WHEN(dateFormat1.format(date));
	electioncontituency= electioncontituencyrepository.saveAndFlush(electioncontituency);
	rtn = mapper.writeValueAsString(electioncontituency);

	tbldatalogrepository
	.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituency.getCONTITUENCY_ID(), databaseTableID, requestUser.getUSER_ID(), rtn));

	
	apiRequest.setREQUEST_OUTPUT(rtn);
	apiRequest.setREQUEST_STATUS("Success");
	apirequestdatalogRepository.saveAndFlush(apiRequest);

	log.info("Output: " + rtn);
	log.info("--------------------------------------------------------");

	return rtn;

	}
	
	
	@RequestMapping(value= "/{id}",method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken)
	throws JsonProcessingException, JSONException, ParseException {
	ObjectMapper mapper = new ObjectMapper();


	log.info("DELETE: / electioncontituency");
	ElectionContituency  electioncontituency = electioncontituencyrepository.findOne(id);
	String rtn, workstation = null;
	LoginUser requestUser;
	String user_NAME = AccessToken.getTokenDetail(headToken);
	requestUser = loginuserrepository.getUser(user_NAME);

	DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituency.getDatabaseTableID());

	 
	APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("DELETE", databaseTableID, requestUser, "/electioncontituency/"+id, null,
	workstation);

	electioncontituencyrepository.delete(electioncontituency);
	rtn = mapper.writeValueAsString(electioncontituency);

	tbldatalogrepository
	.saveAndFlush(tableDataLogs.TableDeleteDataLog(electioncontituency.getCONTITUENCY_ID(), databaseTableID, requestUser.getUSER_ID(), rtn));

	apiRequest.setREQUEST_OUTPUT(rtn);
	apiRequest.setREQUEST_STATUS("Success");
	apirequestdatalogRepository.saveAndFlush(apiRequest);

	log.info("Output: " + rtn);
	log.info("--------------------------------------------------------");

	return rtn;
	}
}
