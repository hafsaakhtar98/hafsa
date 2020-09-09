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

import com.cwiztech.contituency.model.ElectionContituencyPollingStation;
import com.cwiztech.contituency.repository.electionContituencyPollingStationRepository;
import com.cwiztech.contituency.repository.electionContituencyRepository;
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
@RequestMapping("/electioncontituencypollingstation")
public class electionContituencyPollingStationController {

	private static final Logger log = LoggerFactory.getLogger(electionContituencyPollingStationController.class);
	
	
	@Autowired
	private electionContituencyPollingStationRepository electioncontituencypollingstationrepository;
	
	@Autowired
	private electionContituencyRepository electioncontituencyrepository;
	
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

		log.info("GET: /electioncontituencypollingstation");

		List<ElectionContituencyPollingStation> electioncontituencypollingstation = electioncontituencypollingstationrepository.findActive();
		String rtn, workstation = null;

        String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);

        DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituencypollingstation",
				null, workstation);


		rtn = mapper.writeValueAsString(electioncontituencypollingstation);

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

		log.info("GET: /electioncontituencypollingstation/all");

		List<ElectionContituencyPollingStation> electioncontituencypollingstation = electioncontituencypollingstationrepository.findAll();
		String rtn, workstation = null;
		
		
        String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);

		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituencypollingstation/all",
				null, workstation);

		rtn = mapper.writeValueAsString(electioncontituencypollingstation);

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

		log.info("GET: /electioncontituencypollingstation/" + id);

		ElectionContituencyPollingStation electioncontituencypollingstation = electioncontituencypollingstationrepository.findOne(id);
		String rtn, workstation = null;

		String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);
		
		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser,
				"/electioncontituencypollingstation/" + id, null, workstation);

		rtn = mapper.writeValueAsString(electioncontituencypollingstation);

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

			log.info("POST: /electioncontituencypollingstation");
			log.info("Input: " + data);

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
			Date date = new Date();
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(data);

			LoginUser requestUser;
			String rtn, workstation = null;
			
			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);

	        ElectionContituencyPollingStation electioncontituencypollingstation = new ElectionContituencyPollingStation();

			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
			
			if (jsonObj.has("workstation"))
				workstation = jsonObj.getString("workstation");
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser, "/electioncontituencypollingstation",
					data, workstation);
			
			if (!jsonObj.has("contituency_ID") && jsonObj.isNull("contituency_CODE")){
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Contituency Id is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstation.setCONTITUENCY_ID(electioncontituencyrepository.findOne(jsonObj.getLong("contituency_ID")));
			
			if (!jsonObj.has("description") && jsonObj.isNull("description")) {
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Description is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstation.setDESCRIPTION(jsonObj.getString("description"));
			
			
			if (jsonObj.has("pollingstation_code"))
				electioncontituencypollingstation.setPOLLINGSTATION_CODE(jsonObj.getString("pollingstation_code"));
			
			if (jsonObj.has("langitude"))
				
				electioncontituencypollingstation.setLANGITUDE(jsonObj.getString("langitude"));
			if (jsonObj.has("latitude"))
				electioncontituencypollingstation.setLATITUDE(jsonObj.getString("latitude"));
			
			if (jsonObj.has("valid_votes"))
				electioncontituencypollingstation.setVALID_VOTES(lookuprepository.findOne(jsonObj.getLong("valid_votes")));
			
			if (jsonObj.has("rejected_votes"))
				electioncontituencypollingstation.setREJECTED_VOTES(lookuprepository.findOne(jsonObj.getLong("rejected_votes")));
			
			

		
			electioncontituencypollingstation.setISACTIVE("Y");
			electioncontituencypollingstation.setMODIFIED_BY(requestUser);
			electioncontituencypollingstation.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstation.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstation = electioncontituencypollingstationrepository.saveAndFlush(electioncontituencypollingstation);
			rtn = mapper.writeValueAsString(electioncontituencypollingstation);

			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstation.getPOLLINGSTATION_ID(),
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

			log.info("PUT: /electioncontituencypollingstation/" + id);
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			ElectionContituencyPollingStation electioncontituencypollingstation = electioncontituencypollingstationrepository.findOne(id);
			String rtn, workstation = null;
			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
			
			if (jsonObj.has("workstation"))
				workstation = jsonObj.getString("workstation");
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("PUT", databaseTableID, requestUser, "/electioncontituencypollingstation" + id,
					data, workstation);

			    
			    if (jsonObj.has("contituency_ID") && !jsonObj.isNull("contituency_ID"))
			    	 electioncontituencypollingstation.setCONTITUENCY_ID(electioncontituencyrepository.findOne(jsonObj.getLong("contituency_ID")));
			    
			    if (jsonObj.has("pollingstation_CODE"))
					electioncontituencypollingstation.setPOLLINGSTATION_CODE(jsonObj.getString("pollingstation_CODE"));
				
				if (jsonObj.has("description")&& !jsonObj.isNull("description"))
					electioncontituencypollingstation.setDESCRIPTION(jsonObj.getString("description"));
				
				if (jsonObj.has("langitude"))
					electioncontituencypollingstation.setLANGITUDE(jsonObj.getString("langitude"));
				
				if (jsonObj.has("latitude"))
					electioncontituencypollingstation.setLATITUDE(jsonObj.getString("latitude"));
				
				if (jsonObj.has("valid_VOTES"))
					electioncontituencypollingstation.setVALID_VOTES(lookuprepository.findOne(jsonObj.getLong("valid_VOTES")));
				
				if (jsonObj.has("rejected_VOTES"))
					electioncontituencypollingstation.setREJECTED_VOTES(lookuprepository.findOne(jsonObj.getLong("rejected_VOTES")));
				
			    if (jsonObj.has("isactive"))
					 electioncontituencypollingstation.setISACTIVE(jsonObj.getString("isactive"));
			    
		
			electioncontituencypollingstation.setMODIFIED_BY(requestUser);
			electioncontituencypollingstation.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstation.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstation = electioncontituencypollingstationrepository.saveAndFlush(electioncontituencypollingstation);

			rtn = mapper.writeValueAsString(electioncontituencypollingstation);
			
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstation.getPOLLINGSTATION_ID(),
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

			log.info("PUT: /electioncontituencypollingstation");
			log.info("Input: " + data);

			JSONArray jsonPAV = new JSONArray(data);
			List<ElectionContituencyPollingStation> electioncontituencypollingstations = new ArrayList<ElectionContituencyPollingStation>();
			String rtn, workstation = null;

			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("PUT", databaseTableID, requestUser, "/electioncontituencypollingstation",
					data, workstation);


			for (int i=0; i<jsonPAV.length(); i++) {
				JSONObject jsonObj = jsonPAV.getJSONObject(i);
				ElectionContituencyPollingStation electioncontituencypollingstation = new  ElectionContituencyPollingStation();
				long id=0; 
				
				if (jsonObj.has("pollingstation_ID")) {
					id = jsonObj.getLong("pollingstation_ID");
					if (id!=0)
						electioncontituencypollingstation = electioncontituencypollingstationrepository.findOne(id);
				};
				
	
				 if (id==0 && (!jsonObj.has("contituency_ID") || jsonObj.isNull("contituency_ID"))) {
						apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "contituency_ID is missing");
						apirequestdatalogRepository.saveAndFlush(apiRequest);
						id = -1;
					}
				   
			    if (id==0 && (!jsonObj.has("pollingstation_CODE") || jsonObj.isNull("pollingstation_CODE"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Pollingstation_code is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}

			    if (id==0 && (!jsonObj.has("description") || jsonObj.isNull("description"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Description is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    if (id==0 && (!jsonObj.has("langitude") || jsonObj.isNull("langitude"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Langitude is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    if (id==0 && (!jsonObj.has("latitude") || jsonObj.isNull("latitude"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Latitude is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			   
			    if (id==0 && (!jsonObj.has("valid_VOTES") || jsonObj.isNull("valid_VOTES"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Valid Votes is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    if (id==0 && (!jsonObj.has("rejected_VOTES") || jsonObj.isNull("rejected_VOTES"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStation", "Rejected Votes are missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    
			    
			    
			    
			    
				if (id!=-1) {
					
					 if (jsonObj.has("Contituency_ID") && !jsonObj.isNull("Contituency_ID"))
						 electioncontituencypollingstation.setCONTITUENCY_ID(electioncontituencyrepository.findOne(jsonObj.getLong("Contituency_ID")));
					 
					 
					 if (jsonObj.has("pollingstation_CODE"))
							electioncontituencypollingstation.setPOLLINGSTATION_CODE(jsonObj.getString("pollingstation_CODE"));
						
						if (jsonObj.has("description") && !jsonObj.isNull("description"))
							electioncontituencypollingstation.setDESCRIPTION(jsonObj.getString("description"));
						
						if (jsonObj.has("langitude"))
							electioncontituencypollingstation.setLANGITUDE(jsonObj.getString("langitude"));
						
						if (jsonObj.has("latitude"))
							electioncontituencypollingstation.setLATITUDE(jsonObj.getString("latitude"));
						
						if (jsonObj.has("valid_VOTES"))
							electioncontituencypollingstation.setVALID_VOTES(lookuprepository.findOne(jsonObj.getLong("valid_VOTES")));
						
						if (jsonObj.has("rejected_VOTES"))
							electioncontituencypollingstation.setREJECTED_VOTES(lookuprepository.findOne(jsonObj.getLong("rejected_VOTES")));
			    	
					 if (jsonObj.has("isactive"))
						electioncontituencypollingstation.setISACTIVE(jsonObj.getString("isactive"));
					else
						electioncontituencypollingstation.setISACTIVE("Y");

					electioncontituencypollingstation.setMODIFIED_BY(requestUser);
					electioncontituencypollingstation.setMODIFIED_WORKSTATION(workstation);
					electioncontituencypollingstation.setMODIFIED_WHEN(dateFormat1.format(date));
					electioncontituencypollingstation = electioncontituencypollingstationrepository.saveAndFlush(electioncontituencypollingstation);
					
					rtn = mapper.writeValueAsString(electioncontituencypollingstation);
					tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstation.getPOLLINGSTATION_ID(), databaseTableID, requestUser, rtn));

					electioncontituencypollingstations.add(electioncontituencypollingstation);
				}
				
			}
			
			rtn = mapper.writeValueAsString(electioncontituencypollingstations);

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

			log.info("DELETE: /electioncontituencypollingstation/" + id);

			ElectionContituencyPollingStation electioncontituencypollingstation = electioncontituencypollingstationrepository.findOne(id);
			String rtn, workstation = null;

			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("DELETE", databaseTableID, requestUser, "/electioncontituencypollingstation" + id,
					null, workstation);

			electioncontituencypollingstationrepository.delete(electioncontituencypollingstation);
			rtn = mapper.writeValueAsString(electioncontituencypollingstation);

			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstation.getPOLLINGSTATION_ID(),
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
			
			log.info("GET: /electioncontituencypollingstation/" + id + "/remove");

			ElectionContituencyPollingStation electioncontituencypollingstation = electioncontituencypollingstationrepository.findOne(id);
			String rtn, workstation = null;

			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser,
					"/electioncontituencypollingstation" + id + "/remove", "", workstation);
			electioncontituencypollingstation.setISACTIVE("N");
			electioncontituencypollingstation.setMODIFIED_BY(requestUser);
			electioncontituencypollingstation.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstation.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstation = electioncontituencypollingstationrepository.saveAndFlush(electioncontituencypollingstation);
			rtn = mapper.writeValueAsString(electioncontituencypollingstation);
			tbldatalogrepository
					.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstation.getPOLLINGSTATION_ID(), databaseTableID, requestUser, rtn));

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
			
			log.info("POST: electioncontituencypollingstation/search" + ((active == true) ? "" : "/all"));
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			String rtn = null, workstation = null;

			List<ElectionContituencyPollingStation> electioncontituencypollingstation = ((active == true)
					? electioncontituencypollingstationrepository.findBySearch("%" + jsonObj.getString("search") + "%")
					: electioncontituencypollingstationrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
			
			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);

			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser,
					"/electioncontituencypollingstation/search" + ((active == true) ? "" : "/all"), null, workstation);

			rtn = mapper.writeValueAsString(electioncontituencypollingstation);

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
			
			log.info("POST: electioncontituencypollingstation/advancedsearch" + ((active == true) ? "" : "/all"));
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			long pollingstation_ID = 0; long contituency_ID=0; long valid_VOTES=0; long rejected_VOTES=0;
			if (jsonObj.has("pollingstation_ID"))
				pollingstation_ID = jsonObj.getLong("pollingstation_ID");
			
			if (jsonObj.has("contituency_ID"))
				pollingstation_ID = jsonObj.getLong("contituency_ID");
			
			if (jsonObj.has("valid_VOTES"))
				valid_VOTES = jsonObj.getLong("valid_VOTES");
			
			if (jsonObj.has("rejected_VOTES"))
				rejected_VOTES = jsonObj.getLong("rejected_VOTES");
			
	
			
			
			List<ElectionContituencyPollingStation> electioncontituencypollingstation = ((active == true)
					? electioncontituencypollingstationrepository.findByAdvancedSearch(pollingstation_ID,contituency_ID,valid_VOTES,rejected_VOTES)
					: electioncontituencypollingstationrepository.findAllByAdvancedSearch(pollingstation_ID,contituency_ID,valid_VOTES,rejected_VOTES));
			

			String rtn, workstation=null;		
			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStation.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser,
					"/electioncontituencypollingstation/advancedsearch" + ((active == true) ? "" : "/all"), data, workstation);
		    
			rtn = mapper.writeValueAsString(electioncontituencypollingstation);

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;
		
		}
	};
	
	
	
	
	
	