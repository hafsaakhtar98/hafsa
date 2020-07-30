package com.cwiztech.contituency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cwiztech.contituency.model.ElectionContituencyPollingStationDetail;


public interface electionContituencyPollingStationDetailRepository extends JpaRepository<ElectionContituencyPollingStationDetail, Long> {

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONDETAIL where ISACTIVE='Y'", nativeQuery = true)
	public List<ElectionContituencyPollingStationDetail> findActive();

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONDETAIL as a"
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATION_ID=b.POLLINGSTATION_ID "
			+ "where AREATYPE_ID like ?1 or ELECTORALAREA like ?1 or BLOCKCODE like ?1 or GANDER like ?1 or ASSIGNEDVOTERS like ?1 or TOTALPOLLINGBOOTHS like ?1 ) and a.ISACTIVE='Y'", nativeQuery = true)
	public List<ElectionContituencyPollingStationDetail> findBySearch(String search);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONDETAIL as a "
			+  "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATION_ID=b.POLLINGSTATION_ID "
			+ "where AREATYPE_ID like ?1 or ELECTORALAREA like ?1 or BLOCKCODE like ?1 or GANDER like ?1 or ASSIGNEDVOTERS like ?1 or TOTALPOLLINGBOOTHS like ?1" + "", nativeQuery = true) 
	public List<ElectionContituencyPollingStationDetail> findAllBySearch(String search);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONDETAIL as a "
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATION_ID=b.POLLINGSTATION_ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as c on a.AREATYPE_ID=c.ID " 
			+ "where POLLINGSATTIONDETAIL_ID LIKE CASE WHEN ?1 = 0 THEN POLLINGSATTIONDETAIL_ID ELSE ?1 END "
			+ "and a.POLLINGSTATION_ID LIKE CASE WHEN ?2 = 0 THEN a.POLLINGSTATION_ID ELSE ?2 END "
			+ "and a.AREATYPE_ID LIKE CASE WHEN ?3 = 0 THEN a.AREATYPE_ID ELSE ?3 END "
			+ "and a.ISACTIVE='Y'", nativeQuery = true)
	List<ElectionContituencyPollingStationDetail> findByAdvancedSearch(Long psdid, Long psid,Long atid);

	@Query(value = "select * from TBLELECTIONCONTITUENCYPOLLINGSTATIONDETAIL as a "
			+ "inner join TBLELECTIONCONTITUENCYPOLLINGSTATION as b on a.POLLINGSTATION_ID=b.POLLINGSTATION_ID " 
			+ "inner join TBLSYSTEMSETTINGLOOKUP as c on a.AREATYPE_ID=c.ID " 
			+ "where POLLINGSATTIONDETAIL_ID LIKE CASE WHEN ?1 = 0 THEN POLLINGSATTIONDETAIL_ID ELSE ?1 END "
			+ "and a.POLLINGSTATION_ID LIKE CASE WHEN ?2 = 0 THEN a.POLLINGSTATION_ID ELSE ?2 END "
			+ "and a.AREATYPE_ID LIKE CASE WHEN ?3 = 0 THEN a.AREATYPE_ID ELSE ?3 END "
			, nativeQuery = true)
	List<ElectionContituencyPollingStationDetail> findAllByAdvancedSearch( Long psdid, Long psid,Long atid);

}
