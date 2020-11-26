package com.erbf.bugsLife.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erbf.bugsLife.edu.domain.EduInfo;

public interface EduInfoRepository extends JpaRepository<EduInfo, String>{
	
	List<EduInfo> findByAcademyId(String academyId);
	List<EduInfo> findAllByOrderByStartDateDesc();
	
}
