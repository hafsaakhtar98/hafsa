package com.cwiztech.contituency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cwiztech.contituency.model.ElectionContituency;





public interface electionContituencyRepository extends JpaRepository<ElectionContituency,Long>{
	 
	@Query(value="select * from TBLELECTIONCONTITUENCY where ISACTIVE='Y'", nativeQuery = true)
	List<ElectionContituency> findActive();
	

}

