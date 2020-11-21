package com.erbf.bugsLife.edu.application.web;

import com.erbf.bugsLife.edu.application.web.dto.AcademyDetailDto;
import com.erbf.bugsLife.edu.application.web.dto.EduInfoDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewDto;
import com.erbf.bugsLife.edu.service.EduService;
import com.erbf.bugsLife.edu.service.ExternalEduAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edu/")
public class EduController {

	@Autowired
	EduService eduService;
	
	@Autowired
	ExternalEduAPI externalEdu;
	
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
	
//	@GetMapping("/parsingAcademy")
//	public void eduAcademyParsingAdd() {
//		externalEdu.eduAcademyParsingAdd("200700084", "�λ���������������б�", "051-802-1650", "AIG20150000094549", "15");
//	}
	
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
