package com.erbf.bugsLife.edu.application.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erbf.bugsLife.edu.application.web.dto.AcademyDetailDto;
import com.erbf.bugsLife.edu.application.web.dto.EduInfoDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewModifyDto;
import com.erbf.bugsLife.edu.domain.EduReview;
import com.erbf.bugsLife.edu.repository.EduReviewRepository;
import com.erbf.bugsLife.edu.service.EduService;
import com.erbf.bugsLife.edu.service.ExternalEduAPI;

@RestController
@RequestMapping("/api/edu/")
public class EduController {

	@Autowired
	EduService eduService;
	
	@Autowired
	ExternalEduAPI externalEdu;
	
	@Autowired
	EduReviewRepository eduReviewRepo;
	
	@GetMapping
	public List<EduInfoDto> getEduInfoList() {
		List<EduInfoDto> eduInfoList = eduService.eduInfoList();
		return eduInfoList;
	}
	
//	@GetMapping("/info/{eid}")
//	public EduDetailDto eduDetail(@PathVariable String eid) {
//		return eduService.eduDetail(eid);
//	}
	
	@GetMapping("/info/{eid}")
	public EduInfoDto eduInfo(@PathVariable String eid) {
		return eduService.eduInfo(eid);
	}
	
	@PostMapping
	public EduReviewDto eduReviewCreate(@RequestBody EduReviewDto eduReviewDto) {
		eduService.eduReviewCreate(eduReviewDto);
		return eduReviewDto;
	}
	
	
	@GetMapping("/academy/{aid}")
	public AcademyDetailDto academyDetail(@PathVariable String aid) {
		return eduService.academyDetail(aid);
	}
	
	@GetMapping("/parsing/")
	public int eduInfoParsingAdd() {
		return externalEdu.eduInfoParsingAdd();
	}
	
	@PutMapping("/reviewModify/{rid}")
	public void updateReview(@PathVariable Long rid, @RequestBody EduReviewModifyDto modifyDto) {
		eduService.eduReviewUpdate(rid, modifyDto);
	}
	
	@PutMapping("/{rid}/adReport")
	public void adReport(@PathVariable Long rid) {
		EduReview review = eduService.getReview(rid);
		review.addAdReport();
		eduReviewRepo.save(review);
	}
	
	@DeleteMapping("/reviewDelete/{rid}")
	public void deleteReview(@PathVariable Long rid) {
		eduService.eduReviewDelete(rid);
	}
	
	@GetMapping("/checkReview/{wid}/{eid}")
	public boolean checkReview(@PathVariable Long wid, @PathVariable String eid) {
		if(eduService.checkReview(wid,eid)==1) {
			return true;
		}else {
			return false;
		}
	}
	
	
//	@GetMapping
//	public String test() {
//		return "EDU!!";
//	}
	
	
//	@PostMapping("/addEduInfo/")
//	public void eduInfoCreate(@RequestBody EduInfoDto eduInfoDto) {
//		System.out.println(eduInfoDto);
//		eduService.eduInfoCreate(eduInfoDto);
//	}
	
//	@PostMapping("/addEduAcademy/")
//	public void eduAcademyCreate(@RequestBody EduAcademyDto eduAcademyDto) {
//		eduService.eduAcademyCreate(eduAcademyDto);
//	}
}
