package com.cwiztech.contituency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cwiztech.contituency.model.ElectionContituencyPollingStation;
import com.cwiztech.contituency.model.ElectionContituencyPollingStationDetail;


public interface electionContituencyPollingStationRepository extends JpaRepository<ElectionContituencyPollingStation, Long> {

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATION where ISACTIVE='Y'", nativeQuery = true)
	public List<ElectionContituencyPollingStation> findActive();
	
}
