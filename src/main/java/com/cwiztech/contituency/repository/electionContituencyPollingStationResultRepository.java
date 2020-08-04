package com.cwiztech.contituency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cwiztech.contituency.model.ElectionContituencyPollingStationResult;

public interface electionContituencyPollingStationResultRepository extends JpaRepository<ElectionContituencyPollingStationResult, Long> {

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONRESULT where ISACTIVE='Y'", nativeQuery = true)
	public List<ElectionContituencyPollingStationResult> findActive();

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONRESULT as a"
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATIONRESULT_ID=b.POLLINGSTATIONRESULT_ID "
			+ "where POLLINGSTATION_ID like ?1 or CANDIDATE_ID like ?1 or RESULTFROM_ID like ?1 or OBTAINED_VOTES like ?1 ) and a.ISACTIVE='Y'", nativeQuery = true)
	public List<ElectionContituencyPollingStationResult> findBySearch(String search);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONRESULT as a "
			+  "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATIONRESULT_ID=b.POLLINGSTATIONREULT_ID "
			+ "where POLLINGSTATION_ID like ?1 or CANDIDATE_ID like ?1 or RESULTFROM_ID like ?1 or OBTAINED_VOTES like ?1" + "", nativeQuery = true) 
	public List<ElectionContituencyPollingStationResult> findAllBySearch(String search);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONRESULT as a "
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATIONRESULT_ID=b.POLLINGSTATIONRESULT_ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as c on a.CANDIDATE_ID=c.ID " 
			+ "where POLLINGSATTIONRESULT_ID LIKE CASE WHEN ?1 = 0 THEN POLLINGSATTIONRESULT_ID ELSE ?1 END "
			+ "and a.POLLINGSTATION_ID LIKE CASE WHEN ?2 = 0 THEN a.POLLINGSTATION_ID ELSE ?2 END "
			+ "and a.CANDIDATE_ID LIKE CASE WHEN ?3 = 0 THEN a.CANDIDATE_ID ELSE ?3 END "
			+ "and a.RESULTFROM_ID LIKE CASE WHEN ?4 = 0 THEN a.RESULTFROM_ID ELSE ?4 END "
			+ "and a.ISACTIVE='Y'", nativeQuery = true)
	List<ElectionContituencyPollingStationResult> findByAdvancedSearch(Long psrid, Long psid,Long cid,Long rfid);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONRESULT as a "
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATIONRESULT_ID=b.POLLINGSTATIONRESULT_ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as c on a.CANDIDATE_ID=c.ID " 
			+ "where POLLINGSATTIONRESULT_ID LIKE CASE WHEN ?1 = 0 THEN POLLINGSATTIONRESULT_ID ELSE ?1 END "
			+ "and a.POLLINGSTATION_ID LIKE CASE WHEN ?2 = 0 THEN a.POLLINGSTATION_ID ELSE ?2 END "
			+ "and a.CANDIDATE_ID LIKE CASE WHEN ?3 = 0 THEN a.CANDIDATE_ID ELSE ?3 END "
			+ "and a.RESULTFROM_ID LIKE CASE WHEN ?4 = 0 THEN a.RESULTFROM_ID ELSE ?4 END "
			, nativeQuery = true)
	List<ElectionContituencyPollingStationResult> findAllByAdvancedSearch( Long psrid, Long psid,Long cid,Long rfid);

}
