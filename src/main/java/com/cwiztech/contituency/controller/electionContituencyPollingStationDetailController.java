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

import com.cwiztech.contituency.model.ElectionContituencyPollingStationDetail;
import com.cwiztech.contituency.repository.electionContituencyPollingStationDetailRepository;
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
@RequestMapping("/electioncontituencypollingstationdetail")
public class electionContituencyPollingStationDetailController {

	private static final Logger log = LoggerFactory.getLogger(electionContituencyPollingStationDetailController.class);
	
	@Autowired
	private electionContituencyPollingStationDetailRepository electioncontituencypollingstationdetailrepository ;
	
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

		log.info("GET: /electioncontituencypollingstationdetail");

		List<ElectionContituencyPollingStationDetail> electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.findActive();
		String rtn, workstation = null;

        String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);

        DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituencypollingstationdetail",
				null, workstation);


		rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);

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

		log.info("GET: /electioncontituencypollingstationdetail/all");

		List<ElectionContituencyPollingStationDetail> electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.findAll();
		String rtn, workstation = null;
		
		
        String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);

		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser, "/electioncontituencypollingstationdetail/all",
				null, workstation);

		rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);

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

		log.info("GET: /electioncontituencypollingstationdetail/" + id);

		ElectionContituencyPollingStationDetail electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.findOne(id);
		String rtn, workstation = null;

		String user_NAME = AccessToken.getTokenDetail(headToken);
        requestUser = loginuserrepository.getUser(user_NAME);
		
		DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
		APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser,
				"/electioncontituencypollingstationdetail/" + id, null, workstation);

		rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);

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

			log.info("POST: /electioncontituencypollingstationdetail");
			log.info("Input: " + data);

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
			Date date = new Date();
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(data);

			LoginUser requestUser;
			String rtn, workstation = null;
			
			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);

	        ElectionContituencyPollingStationDetail electioncontituencypollingstationdetail = new ElectionContituencyPollingStationDetail();

			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
			
			if (jsonObj.has("workstation"))
				workstation = jsonObj.getString("workstation");
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser, "/electioncontituencypollingstationdetail",
					data, workstation);

			
			
			if (!jsonObj.has("pollingstation_ID")) {
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "PollingStation Id is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstationdetail.setPOLLINGSTATION_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("pollingstation_ID")));
			
			
			if (!jsonObj.has("areatype_ID")) {
				apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "AreaType Id is missing");
				apirequestdatalogRepository.saveAndFlush(apiRequest);
				return apiRequest.getREQUEST_OUTPUT();
			}
			electioncontituencypollingstationdetail.setAREATYPE_ID(lookuprepository.findOne(jsonObj.getLong("areatype_ID")));
			
			
			if (jsonObj.has("electoralarea"))
				electioncontituencypollingstationdetail.setELECTORALAREA(jsonObj.getString("electoralarea"));
			
			if (jsonObj.has("blockcode"))
				electioncontituencypollingstationdetail.setBLOCKCODE(jsonObj.getString("blockcode"));
			
			if (jsonObj.has("gender"))
				electioncontituencypollingstationdetail.setGANDER(jsonObj.getString("gender"));
			
			if (jsonObj.has("assignedvoters"))
				electioncontituencypollingstationdetail.setASSIGNEDVOTERS(jsonObj.getString("assignedvoters"));
			
			if (jsonObj.has("totalpollingbooths"))
				electioncontituencypollingstationdetail.setTOTALPOLLINGBOOTHS(jsonObj.getString("totalpollingbooths"));
			
			

		
			electioncontituencypollingstationdetail.setISACTIVE("Y");
			electioncontituencypollingstationdetail.setMODIFIED_BY(requestUser);
			electioncontituencypollingstationdetail.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstationdetail.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.saveAndFlush(electioncontituencypollingstationdetail);
			rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);

			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationdetail.getPOLLINGSTATIONDETAIL_ID(),
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

			log.info("PUT: /electioncontituencypollingstationdetail/" + id);
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			ElectionContituencyPollingStationDetail electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.findOne(id);
			String rtn, workstation = null;
			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
			
			if (jsonObj.has("workstation"))
				workstation = jsonObj.getString("workstation");
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("PUT", databaseTableID, requestUser, "/electioncontituencypollingstationdetail" + id,
					data, workstation);

			
			
			if (jsonObj.has("pollingstation_ID") && !jsonObj.isNull("pollingstation_ID"))
			    electioncontituencypollingstationdetail.setPOLLINGSTATION_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("pollingstation_ID")));
					    
			    if (jsonObj.has("areatype_ID") && !jsonObj.isNull("areatype_ID"))
			    	 electioncontituencypollingstationdetail.setAREATYPE_ID(lookuprepository.findOne(jsonObj.getLong("areatype_ID")));
							    
			    if (jsonObj.has("blockcode"))
					 electioncontituencypollingstationdetail.setBLOCKCODE(jsonObj.getString("blockcode"));
			    
			    if (jsonObj.has("gender"))
					 electioncontituencypollingstationdetail.setGANDER(jsonObj.getString("gender"));
			    	
			    if (jsonObj.has("assignedvoters"))
					 electioncontituencypollingstationdetail.setASSIGNEDVOTERS(jsonObj.getString("assignedvoters"));
			    	
			    if (jsonObj.has("totalpollingbooths"))
					 electioncontituencypollingstationdetail.setTOTALPOLLINGBOOTHS(jsonObj.getString("totalpollingbooths"));
			    	
			    if (jsonObj.has("isactive"))
					 electioncontituencypollingstationdetail.setISACTIVE(jsonObj.getString("isactive"));
			    
		
			electioncontituencypollingstationdetail.setMODIFIED_BY(requestUser);
			electioncontituencypollingstationdetail.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstationdetail.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.saveAndFlush(electioncontituencypollingstationdetail);

			rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);
			
			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationdetail.getPOLLINGSTATIONDETAIL_ID(),
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

			log.info("PUT: /electioncontituencypollingstationdetail");
			log.info("Input: " + data);

			JSONArray jsonPAV = new JSONArray(data);
			List<ElectionContituencyPollingStationDetail> electioncontituencypollingstationdetails = new ArrayList<ElectionContituencyPollingStationDetail>();
			String rtn, workstation = null;

			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("PUT", databaseTableID, requestUser, "/election contituency polling station detail",
					data, workstation);


			for (int i=0; i<jsonPAV.length(); i++) {
				JSONObject jsonObj = jsonPAV.getJSONObject(i);
				ElectionContituencyPollingStationDetail electioncontituencypollingstationdetail = new  ElectionContituencyPollingStationDetail();
				long id=0; 
				
				if (jsonObj.has("pollingstationdetail_ID")) {
					id = jsonObj.getLong("pollingstationdetail_ID");
					if (id!=0)
						electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.findOne(id);
				};
				
				if (id==0 && (!jsonObj.has("pollingstation_ID") || jsonObj.isNull("pollingstation_ID"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "Polling Station ID is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
					
				 if (id==0 && (!jsonObj.has("areatype_ID") || jsonObj.isNull("areatype_ID"))) {
						apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "Area Type ID is missing");
						apirequestdatalogRepository.saveAndFlush(apiRequest);
						id = -1;
					}
				   
			    if (id==0 && (!jsonObj.has("blockcode") || jsonObj.isNull("blockcode"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "Block Code is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}

			    if (id==0 && (!jsonObj.has("gender") || jsonObj.isNull("gender"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "Gender is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    if (id==0 && (!jsonObj.has("assignedvoters") || jsonObj.isNull("assignedvoters"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "Assigned Voters is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			   
			    if (id==0 && (!jsonObj.has("electoralarea") || jsonObj.isNull("electoralarea"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "Electoral Area is missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    if (id==0 && (!jsonObj.has("totalpoolingbooths") || jsonObj.isNull("totalpoolingbooths"))) {
					apiRequest = tableDataLogs.errorDataLog(apiRequest, "ElectionContituencyPollingStationDetail", "Total Pooling Booths are missing");
					apirequestdatalogRepository.saveAndFlush(apiRequest);
					id = -1;
				}
			    
			    
			    
			    
			    
			    
				if (id!=-1) {
					if (jsonObj.has("pollingstation_ID") && !jsonObj.isNull("pollingstation_ID"))
						electioncontituencypollingstationdetail.setPOLLINGSTATION_ID(electioncontituencypollingstationrepository.getOne(jsonObj.getLong("pollingstation_ID")));
					
					
					 if (jsonObj.has("areatype_ID") && !jsonObj.isNull("areatype_ID"))
						 electioncontituencypollingstationdetail.setAREATYPE_ID(lookuprepository.findOne(jsonObj.getLong("areatype_ID")));
					 
					 
					 if (jsonObj.has("electoralarea")) 
						 electioncontituencypollingstationdetail.setELECTORALAREA(jsonObj.getString("electoralarea"));
					 
					 
					 if (jsonObj.has("blockcode"))
						 electioncontituencypollingstationdetail.setBLOCKCODE(jsonObj.getString("blockcode"));
					 
					 if (jsonObj.has("gender"))
						 electioncontituencypollingstationdetail.setGANDER(jsonObj.getString("gender"));
			    	
					 if (jsonObj.has("assignedvoters"))
						 electioncontituencypollingstationdetail.setASSIGNEDVOTERS(jsonObj.getString("assignedvoters"));
			    	
					 if (jsonObj.has("totalpoolingbooths"))
						 electioncontituencypollingstationdetail.setTOTALPOLLINGBOOTHS(jsonObj.getString("totalpoolingbooths"));
			    	
					 if (jsonObj.has("isactive"))
						electioncontituencypollingstationdetail.setISACTIVE(jsonObj.getString("isactive"));
					else
						electioncontituencypollingstationdetail.setISACTIVE("Y");

					electioncontituencypollingstationdetail.setMODIFIED_BY(requestUser);
					electioncontituencypollingstationdetail.setMODIFIED_WORKSTATION(workstation);
					electioncontituencypollingstationdetail.setMODIFIED_WHEN(dateFormat1.format(date));
					electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.saveAndFlush(electioncontituencypollingstationdetail);
					
					rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);
					tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationdetail.getPOLLINGSTATIONDETAIL_ID(), databaseTableID, requestUser, rtn));

					electioncontituencypollingstationdetails.add(electioncontituencypollingstationdetail);
				}
				
			}
			
			rtn = mapper.writeValueAsString(electioncontituencypollingstationdetails);

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

			log.info("DELETE: /electioncontituencypollingstationdetail/" + id);

			ElectionContituencyPollingStationDetail electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.findOne(id);
			String rtn, workstation = null;

			String user_NAME = AccessToken.getTokenDetail(headToken);
	        requestUser = loginuserrepository.getUser(user_NAME);
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("DELETE", databaseTableID, requestUser, "/electioncontituencypollingstationdetail" + id,
					null, workstation);

			electioncontituencypollingstationdetailrepository.delete(electioncontituencypollingstationdetail);
			rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);

			tbldatalogrepository.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationdetail.getPOLLINGSTATIONDETAIL_ID(),
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
			
			log.info("GET: /electioncontituencypollingstationdetail/" + id + "/remove");

			ElectionContituencyPollingStationDetail electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.findOne(id);
			String rtn, workstation = null;

			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("GET", databaseTableID, requestUser,
					"/electioncontituencypollingstationdetail" + id + "/remove", "", workstation);
			electioncontituencypollingstationdetail.setISACTIVE("N");
			electioncontituencypollingstationdetail.setMODIFIED_BY(requestUser);
			electioncontituencypollingstationdetail.setMODIFIED_WORKSTATION(workstation);
			electioncontituencypollingstationdetail.setMODIFIED_WHEN(dateFormat1.format(date));
			electioncontituencypollingstationdetail = electioncontituencypollingstationdetailrepository.saveAndFlush(electioncontituencypollingstationdetail);
			rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);
			tbldatalogrepository
					.saveAndFlush(tableDataLogs.TableSaveDataLog(electioncontituencypollingstationdetail.getPOLLINGSTATIONDETAIL_ID(), databaseTableID, requestUser, rtn));

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
			
			log.info("POST: electioncontituencypollingstationdetail/search" + ((active == true) ? "" : "/all"));
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			String rtn = null, workstation = null;

			List<ElectionContituencyPollingStationDetail> electioncontituencypollingstationdetail = ((active == true)
					? electioncontituencypollingstationdetailrepository.findBySearch("%" + jsonObj.getString("search") + "%")
					: electioncontituencypollingstationdetailrepository.findAllBySearch("%" + jsonObj.getString("search") + "%"));
			
			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);

			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser,
					"/electioncontituencypollingstationdetail/search" + ((active == true) ? "" : "/all"), null, workstation);

			rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);

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
			
			log.info("POST: electioncontituencypollingstationdetail/advancedsearch" + ((active == true) ? "" : "/all"));
			log.info("Input: " + data);

			JSONObject jsonObj = new JSONObject(data);
			long pollingstationdetail_ID = 0; long pollingstation_ID=0,areatype_ID=0;
			if (jsonObj.has("pollingstationdetail_ID"))
				pollingstationdetail_ID = jsonObj.getLong("pollingstationdetail_ID");
			
			if (jsonObj.has("pollingstation_ID"))
				pollingstation_ID = jsonObj.getLong("pollingstation_ID");
			
			if (jsonObj.has("areatype_ID"))
				areatype_ID = jsonObj.getLong("areatype_ID");
			
			
			List<ElectionContituencyPollingStationDetail> electioncontituencypollingstationdetail = ((active == true)
					? electioncontituencypollingstationdetailrepository.findByAdvancedSearch(pollingstationdetail_ID,pollingstation_ID,areatype_ID)
					: electioncontituencypollingstationdetailrepository.findAllByAdvancedSearch(pollingstationdetail_ID,pollingstation_ID,areatype_ID));
			

			String rtn, workstation=null;		
			LoginUser requestUser;
			String user_NAME = AccessToken.getTokenDetail(headToken);
			requestUser = loginuserrepository.getUser(user_NAME);
			
			DatabaseTables databaseTableID = databasetablesrepository.findOne(ElectionContituencyPollingStationDetail.getDatabaseTableID());
			requestUser = loginuserrepository.findOne((long) 0);
			APIRequestDataLog apiRequest = tableDataLogs.apiRequestDataLog("POST", databaseTableID, requestUser,
					"/electioncontituencypollingstationdetail/advancedsearch" + ((active == true) ? "" : "/all"), data, workstation);
		    
			rtn = mapper.writeValueAsString(electioncontituencypollingstationdetail);

			apiRequest.setREQUEST_OUTPUT(rtn);
			apiRequest.setREQUEST_STATUS("Success");
			apirequestdatalogRepository.saveAndFlush(apiRequest);

			log.info("Output: " + rtn);
			log.info("--------------------------------------------------------");

			return rtn;
		
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


