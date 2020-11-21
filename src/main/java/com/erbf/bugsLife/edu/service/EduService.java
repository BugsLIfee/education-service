package com.erbf.bugsLife.edu.service;

import com.erbf.bugsLife.edu.application.web.dto.AcademyDetailDto;
import com.erbf.bugsLife.edu.application.web.dto.EduInfoDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewDto;

import java.util.List;

public interface EduService {
	public abstract void eduReviewCreate(EduReviewDto eduReviewDto);
	public abstract List<EduInfoDto> eduInfoList();
	public abstract EduInfoDto eduInfo(String eid);
	public abstract AcademyDetailDto academyDetail(String aid);
}
