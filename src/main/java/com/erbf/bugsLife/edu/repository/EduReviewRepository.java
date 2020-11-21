package com.erbf.bugsLife.edu.repository;

import com.erbf.bugsLife.edu.domain.EduReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EduReviewRepository extends JpaRepository<EduReview, Long>{

	List<EduReview> findByEduId(String eduInfoId);
	List<EduReview> findByAcademyId(String eduAcademyId);
	
}
