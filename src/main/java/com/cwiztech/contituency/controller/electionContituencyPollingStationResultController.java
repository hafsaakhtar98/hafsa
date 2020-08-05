package com.cwiztech.contituency.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
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

import com.cwiztech.contituency.model.ElectionContituencyPollingStationResult;
import com.cwiztech.contituency.repository.electionContituencyPollingStationResultRepository;
import com.cwiztech.contituency.repository.electionContituencyPollingStationRepository;
import com.cwiztech.datalogs.model.APIRequestDataLog;
import com.cwiztech.datalogs.model.DatabaseTables;
import com.cwiztech.datalogs.model.tableDataLogs;
import com.cwiztech.datalogs.repository.apiRequestDataLogRepository;
import com.cwiztech.datalogs.repository.databaseTablesRepository;
import com.cwiztech.datalogs.repository.tableDataLogRepository;

import com.cwiztech.login.model.LoginUser;
import com.cwiztech.login.repository.loginUserRepository;
import com.cwiztech.systemsetting.repository.lookupRepository;
import com.cwiztech.token.AccessToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
@CrossOrigin
@RequestMapping("/electioncontituencypollingstationresult")
public class electionContituencyPollingStationResultController {

	private static final Logger log = LoggerFactory.getLogger(electionContituencyPollingStationResultController.class);
	
	@Autowired
	private electionContituencyPollingStationResultRepository electioncontituencypollingstationresultrepository ;
	
	@Autowired
	private electionContituencyPollingStationRepository electioncontituencypollingstationrepository;
	
	@Autowired
	private lookupRepository lookuprepository;

	@Autowired
	private loginUserRepository loginuserrepository;

	@Autowired
	private apiRequestDataLogRepository apirequestdatalogRepository;

	@Autowired
	private tableDataLogRepository tbldatalogrepository;

	@Autowired
	private databaseTablesRepository databasetablesrepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		LoginUser requestUser;

		log.info("GET: /electioncontituencypollingstationresult");

		List<ElectionContituencyPollingStationResult> electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.findActive();
		String rtn, workstation = null;

        String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);

        DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituencypollingstationresult",
				null, workstation);


		rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);

		apiRequest.setREQUEST_OUTPUT(rtn);
		apiRequest.setREQUEST_STATUS("Success");
		apirequestdatalogRepository.saveAndFlush(apiRequest);

		log.info("Output: " + rtn);
		log.info("--------------------------------------------------------");

		return rtn;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAll(@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		LoginUser requestUser;

		log.info("GET: /electioncontituencypollingstationresult/all");

		List<ElectionContituencyPollingStationResult> electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.findAll();
		String rtn, workstation = null;
		
		
        String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);

		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituencypollingstationresult/all",
				null, workstation);

		rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);

		apiRequest.setREQUEST_OUTPUT(rtn);
		apiRequest.setREQUEST_STATUS("Success");
		apirequestdatalogRepository.saveAndFlush(apiRequest);

		log.info("Output: " + rtn);
		log.info("--------------------------------------------------------");

		return rtn;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getOne(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		LoginUser requestUser;

		log.info("GET: /electioncontituencypollingstationresult/" + id);

		ElectionContituencyPollingStationResult electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.findOne(id);
		String rtn, workstation = null;

		String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);
		
		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser,
				"/electioncontituencypollingstationresult/" + id, null, workstation);

		rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);

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

			log.info("POST: /electioncontituencypollingstationresult");
			log.info("Input: " + data);

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
			Date date = new Date();
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(data);

			LoginUser requestUser;
			String rtn, workstation = null;
			
			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);

	        ElectionContituencyPollingStationResult electioncontituencypollingstationresult = new ElectionContituencyPollingStationResult();

			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
			
			if (jsonObj.has("workstation"))
				workstation = jsonObj.getString("workstation");
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser, "/electioncontituencypollingstationresult",
					data, workstation);

			
			
			if (!jsonObj.has("pollingstationresult_ID")) {
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "PollingStationResult Id is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstationresult.setPOLLINGSTATIONRESULT_ID(electioncontituencypollingstationresultrepository.getOne(jsonObj.getLong("pollingstationresult_ID")));
			
			
			
			if (!jsonObj.has("pollingstation_ID")) {
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "PollingStation Id is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstationresult.setPOLLINGSTATION_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("pollingstation_ID")));
			
			
			if (!jsonObj.has("candidate_ID")) {
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "Candidate Id is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstationresult.setCANDIDATE_ID(lookuprepository.findOne(jsonObj.getLong("candidate_ID")));
			
			
			if (!jsonObj.has("resultfrom_ID")) {
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "ResultFrom Id is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstationresult.setRESULTFROM_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("resultfrom_ID")));
		
			
			if (jsonObj.has("obtained_votes"))
				electioncontituencypollingstationresult.setOBTAINED_VOTES(jsonObj.getString("obtained_votes"));

		    if (jsonObj.has("isactive"))
				 electioncontituencypollingstationresult.setISACTIVE(jsonObj.getString("isactive"));
			
			
		
			electioncontituencypollingstationresult.setISACTIVE("Y");
			electioncontituencypollingstationresult.setMODIFIED_BY(requestUser);
			electioncontituencypollingstationresult.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstationresult.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.saveAndFlush(electioncontituencypollingstationresult);
			rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);

			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationresult.getPOLLINGSTATIONRESULT_ID(),
					databaseTableID, requestUser, rtn));

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;

		};

		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public String update(@PathVariable Long id, @RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
				throws JsonProcessingException, JSONException, ParseException {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
			Date date = new Date();
			ObjectMapper mapper = new ObjectMapper();
			LoginUser requestUser;

			log.info("PUT: /electioncontituencypollingstationresult/" + id);
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			ElectionContituencyPollingStationResult electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.findOne(id);
			String rtn, workstation = null;
			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
			
			if (jsonObj.has("workstation"))
				workstation = jsonObj.getString("workstation");
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("PUT", databaseTableID, requestUser, "/electioncontituencypollingstationresult" + id,
					data, workstation);

			

			if (jsonObj.has("pollingstationresult_ID") && !jsonObj.isNull("pollingstationresult_ID"))
			    electioncontituencypollingstationresult.setPOLLINGSTATIONRESULT_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("pollingstationresult_ID")));
			
			if (jsonObj.has("pollingstation_ID") && !jsonObj.isNull("pollingstation_ID"))
			    electioncontituencypollingstationresult.setPOLLINGSTATION_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("pollingstation_ID")));
					    
			if (jsonObj.has("candidate_ID") && !jsonObj.isNull("candidate_ID"))
		    	 electioncontituencypollingstationresult.setCANDIDATE_ID(lookuprepository.findOne(jsonObj.getLong("candidate_ID")));
					
			if (jsonObj.has("resultfrom_ID") && !jsonObj.isNull("resultfrom_ID"))
		    	 electioncontituencypollingstationresult.setCANDIDATE_ID(lookuprepository.findOne(jsonObj.getLong("resultfrom_ID")));
					
			    if (jsonObj.has("obtained_votes"))
					 electioncontituencypollingstationresult.setOBTAINED_VOTES(jsonObj.getString("obtained_votes"));
			  
			    if (jsonObj.has("isactive"))
					 electioncontituencypollingstationresult.setISACTIVE(jsonObj.getString("isactive"));
			    
		
			electioncontituencypollingstationresult.setMODIFIED_BY(requestUser);
			electioncontituencypollingstationresult.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstationresult.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.saveAndFlush(electioncontituencypollingstationresult);

			rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);
			
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationresult.getPOLLINGSTATIONRESULT_ID(),
					databaseTableID, requestUser, rtn));

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;
		}

		@RequestMapping( method = RequestMethod.PUT)
		public String updateAll(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken)
				throws JsonProcessingException, JSONException, ParseException {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
			Date date = new Date();
			ObjectMapper mapper = new ObjectMapper();
			LoginUser requestUser;

			log.info("PUT: /electioncontituencypollingstationresult");
			log.info("Input: " + data);

			JSONArray jsonPAV = new JSONArray(data);
			List<ElectionContituencyPollingStationResult> electioncontituencypollingstationresults = new ArrayList<ElectionContituencyPollingStationResult>();
			String rtn, workstation = null;

			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("PUT", databaseTableID, requestUser, "/election contituency polling station detail",
					data, workstation);


			for (int i=0; i<jsonPAV.length(); i++) {
				JSONObject jsonObj = jsonPAV.getJSONObject(i);
				ElectionContituencyPollingStationResult electioncontituencypollingstationresult = new  ElectionContituencyPollingStationResult();
				long id=0; 
				
				if (jsonObj.has("pollingstationresult_ID")) {
					id = jsonObj.getLong("pollingstationresult_ID");
					if (id!=0)
						electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.findOne(id);
				};
				
				if (id==0 && (!jsonObj.has("pollingstation_ID") || jsonObj.isNull("pollingstation_ID"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "Polling Station ID is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
					
				 if (id==0 && (!jsonObj.has("candidate_ID") || jsonObj.isNull("candidate_ID"))) {
						apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "Candidate ID is missing");
						apirequestdatalogRepository.saveAndFlush(apiRequest);
						id = -1;
					}
				   
			    if (id==0 && (!jsonObj.has("resultfrom_ID") || jsonObj.isNull("resultfrom_ID"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "ResultFrom ID is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}

			    if (id==0 && (!jsonObj.has("obtained_Votes") || jsonObj.isNull("obtained_Votes"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationResult", "Obtained Votes is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    
			    
			    
				if (id!=-1) {
					if (jsonObj.has("pollingstationresult_ID") && !jsonObj.isNull("pollingstationresult_ID"))
						electioncontituencypollingstationresult.setPOLLINGSTATIONRESULT_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("pollingstationresult_ID")));
					
					
				if (jsonObj.has("pollingstation_ID") && !jsonObj.isNull("pollingstation_ID"))
						 electioncontituencypollingstationresult.setPOLLINGSTATION_ID(lookuprepository.findOne(jsonObj.getLong("pollingstation_ID")));
					
					if (jsonObj.has("candidate_ID") && !jsonObj.isNull("candidate_ID"))
						 electioncontituencypollingstationresult.setCANDIDATE_ID(lookuprepository.findOne(jsonObj.getLong("candidate_ID")));
					
					if (jsonObj.has("resultfrom_ID") && !jsonObj.isNull("resultfrom_ID"))
						 electioncontituencypollingstationresult.setRESULTFROM_ID(lookuprepository.findOne(jsonObj.getLong("resultfrom_ID")));
					 
					 if (jsonObj.has("obtained_votes")) 
						 electioncontituencypollingstationresult.setOBTAINED_VOTES(jsonObj.getString("obtained_votes"));
				
					 if (jsonObj.has("isactive"))
						electioncontituencypollingstationresult.setISACTIVE(jsonObj.getString("isactive"));
					else
						electioncontituencypollingstationresult.setISACTIVE("Y");

					electioncontituencypollingstationresult.setMODIFIED_BY(requestUser);
					electioncontituencypollingstationresult.setMODIFIED_WORKSTATION(workstation);
					electioncontituencypollingstationresult.setMODIFIED_WHEN(dateFormat1.format(date));
					electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.saveAndFlush(electioncontituencypollingstationresult);
					
					rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);
					tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationresult.getPOLLINGSTATIONRESULT_ID(), databaseTableID, requestUser, rtn));

					electioncontituencypollingstationresults.add(electioncontituencypollingstationresult);
				}
				
			}
			
			rtn = mapper.writeValueAsString(electioncontituencypollingstationresults);

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;
		}

		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public String delete(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken)
				throws JsonProcessingException, JSONException, ParseException {
			ObjectMapper mapper = new ObjectMapper();
			LoginUser requestUser;

			log.info("DELETE: /electioncontituencypollingstationresult/" + id);

			ElectionContituencyPollingStationResult electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.findOne(id);
			String rtn, workstation = null;

			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("DELETE", databaseTableID, requestUser, "/electioncontituencypollingstationresult" + id,
					null, workstation);

			electioncontituencypollingstationresultrepository.delete(electioncontituencypollingstationresult);
			rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);

			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationresult.getPOLLINGSTATIONRESULT_ID(),
					databaseTableID, requestUser, rtn));

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;
		}

		@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
		public String remove(@PathVariable Long id,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException, JSONException, ParseException {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
			Date date = new Date();

			ObjectMapper mapper = new ObjectMapper();
			
			log.info("GET: /electioncontituencypollingstationresult/" + id + "/remove");

			ElectionContituencyPollingStationResult electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.findOne(id);
			String rtn, workstation = null;

			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser,
					"/electioncontituencypollingstationresult" + id + "/remove", "", workstation);
			electioncontituencypollingstationresult.setISACTIVE("N");
			electioncontituencypollingstationresult.setMODIFIED_BY(requestUser);
			electioncontituencypollingstationresult.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstationresult.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstationresult = electioncontituencypollingstationresultrepository.saveAndFlush(electioncontituencypollingstationresult);
			rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);
			tbldatalogrepository
					.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationresult.getPOLLINGSTATIONRESULT_ID(), databaseTableID, requestUser, rtn));

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;
		}

		@RequestMapping(value = "/search", method = RequestMethod.POST)
		public String getBySearch(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
			return BySearch(data, true, headToken);

		}

		@RequestMapping(value = "/search/all", method = RequestMethod.POST)
		public String getAllBySearch(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
			return BySearch(data, false, headToken);

		}

		public String BySearch(String data, boolean active, String headToken) throws JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			
			log.info("POST: electioncontituencypollingstationresult/search" + ((active == true) ? "" : "/all"));
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			String rtn = null, workstation = null;

			List<ElectionContituencyPollingStationResult> electioncontituencypollingstationresult = ((active == true)
					? electioncontituencypollingstationresultrepository.findBySearch("%" + jsonObj.getString("search") + "%")
					: electioncontituencypollingstationresultrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
			
			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);

			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser,
					"/electioncontituencypollingstationresult/search" + ((active == true) ? "" : "/all"), null, workstation);

			rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;

		}

		@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
		public String getByAdvancedSearch(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
			return ByAdvancedSearch(data, true, headToken);
		}

		@RequestMapping(value = "/advancedsearch/all", method = RequestMethod.POST)
		public String getAllByAdvancedSearch(@RequestBody String data,@RequestHeader(value = "Authorization") String headToken) throws JsonProcessingException {
			return ByAdvancedSearch(data, false, headToken);
		}

		public String ByAdvancedSearch(String data, boolean active, String headToken) throws JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			
			log.info("POST: electioncontituencypollingstationresult/advancedsearch" + ((active == true) ? "" : "/all"));
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			long pollingstationresult_ID = 0; long pollingstation_ID=0,candidate_ID=0,resultfrom_ID=0;
			if (jsonObj.has("pollingstationresult_ID"))
				pollingstationresult_ID = jsonObj.getLong("pollingstationresult_ID");
			
			if (jsonObj.has("pollingstation_ID"))
				pollingstation_ID = jsonObj.getLong("pollingstation_ID");
			
			if (jsonObj.has("candidate_ID"))
				candidate_ID = jsonObj.getLong("candidate_ID");
			
			if (jsonObj.has("resultfrom_ID"))
				resultfrom_ID = jsonObj.getLong("resultfrom_ID");
			
			
			List<ElectionContituencyPollingStationResult> electioncontituencypollingstationresult = ((active == true)
					? electioncontituencypollingstationresultrepository.findByAdvancedSearch(pollingstationresult_ID,pollingstation_ID,candidate_ID,resultfrom_ID)
					: electioncontituencypollingstationresultrepository.findAllByAdvancedSearch(pollingstationresult_ID,pollingstation_ID,candidate_ID,resultfrom_ID));
			

			String rtn, workstation=null;		
			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationResult.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser,
					"/electioncontituencypollingstationresult/advancedsearch" + ((active == true) ? "" : "/all"), data, workstation);
		    
			rtn = mapper.writeValueAsString(electioncontituencypollingstationresult);

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;
		
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


