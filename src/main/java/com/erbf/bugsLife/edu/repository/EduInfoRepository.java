package com.erbf.bugsLife.edu.repository;

import com.erbf.bugsLife.edu.domain.EduInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EduInfoRepository extends JpaRepository<EduInfo, String>{
	
	List<EduInfo> findByAcademyId(String academyId);
	
}
