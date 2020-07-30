package com.cwiztech.contituency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwiztech.contituency.repository.electionContituencyPollingStationRepository;
import com.cwiztech.datalogs.repository.apiRequestDataLogRepository;
import com.cwiztech.datalogs.repository.databaseTablesRepository;
import com.cwiztech.datalogs.repository.tableDataLogRepository;
import com.cwiztech.login.repository.loginUserRepository;
import com.cwiztech.systemsetting.repository.lookupRepository;

@RestController
@CrossOrigin
@RequestMapping("/electioncontituencypollingstation")
public class electionContituencyPollingStationController {

	private static final Logger log = LoggerFactory.getLogger(electionContituencyPollingStationController.class);
	
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
	
}
