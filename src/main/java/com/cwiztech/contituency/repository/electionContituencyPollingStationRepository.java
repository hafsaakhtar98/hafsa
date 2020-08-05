package com.cwiztech.contituency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cwiztech.contituency.model.ElectionContituencyPollingStation;



public interface electionContituencyPollingStationRepository extends JpaRepository<ElectionContituencyPollingStation, Long> {
	

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATION where ISACTIVE='Y'", nativeQuery = true)
	public List<ElectionContituencyPollingStation> findActive();

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATION as a"
			+ "inner join TBLELECTIONCONTITUENCY as b on a.CONTITUENCY_ID=b.CONTITUENCY_ID "
			+ "where POLLINGSTATION_CODE like ?1 or DESCRIPTION like ?1 or LANGITUDE like ?1 or LATITUDE like ?1 or VALID_VOTES like ?1 or REJECTED_VOTES like ?1 ) and a.ISACTIVE='Y'", nativeQuery = true)
	public List<ElectionContituencyPollingStation> findBySearch(String search);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATION as a "
			+ "inner join TBLELECTIONCONTITUENCY as b on a.CONTITUENCY_ID=b.CONTITUENCY_ID "
			+ "where POLLINGSTATION_CODE like ?1 or DESCRIPTION like ?1 or LANGITUDE like ?1 or LATITUDE like ?1 or VALID_VOTES like ?1 or REJECTED_VOTES like ?1" + "", nativeQuery = true) 
	public List<ElectionContituencyPollingStation> findAllBySearch(String search);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATION as a "
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATION_ID=b.POLLINGSTATION_ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as c on a.VALID_VOTES=c.ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as d on a.REJECTED_VOTES=d.ID " 
			+ "where POLLINGSATTION_ID LIKE CASE WHEN ?1 = 0 THEN POLLINGSATTION_ID ELSE ?1 END "
			+ "and a.CONTITUENCY_ID LIKE CASE WHEN ?2 = 0 THEN a.CONTITUENCY_ID ELSE ?2 END "
			+ "and a.VALID_VOTES LIKE CASE WHEN ?3 = 0 THEN a.VALID_VOTES ELSE ?3 END "
			+ "and a.REJECTED_VOTES LIKE CASE WHEN ?4 = 0 THEN a.REJECTED_VOTES ELSE ?4 END "
			+ "and a.ISACTIVE='Y'", nativeQuery = true)
	List<ElectionContituencyPollingStation> findByAdvancedSearch(Long psid, Long cid,Long vv, Long rv);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATION as a "
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATION_ID=b.POLLINGSTATION_ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as c on a.VALID_VOTES=c.ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as d on a.REJECTED_VOTES=d.ID " 
			+ "where POLLINGSATTION_ID LIKE CASE WHEN ?1 = 0 THEN POLLINGSATTION_ID ELSE ?1 END "
			+ "and a.CONTITUENCY_ID LIKE CASE WHEN ?2 = 0 THEN a.CONTITUENCY_ID ELSE ?2 END "
			+ "and a.VALID_VOTES LIKE CASE WHEN ?3 = 0 THEN a.VALID_VOTES ELSE ?3 END "
			+ "and a.REJECTED_VOTES LIKE CASE WHEN ?4 = 0 THEN a.REJECTED_VOTES ELSE ?4 END "
			, nativeQuery = true)
	List<ElectionContituencyPollingStation> findAllByAdvancedSearch(Long psid, Long cid,Long vv, Long rv);

	
}
