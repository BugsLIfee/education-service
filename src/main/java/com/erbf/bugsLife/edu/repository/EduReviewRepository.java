package com.erbf.bugsLife.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erbf.bugsLife.edu.domain.EduReview;

public interface EduReviewRepository extends JpaRepository<EduReview, Long>{

	List<EduReview> findByEduId(String eduInfoId);
	List<EduReview> findByAcademyId(String eduAcademyId);
	Long countByWriterIdAndEduId(Long wid, String eid);
}
