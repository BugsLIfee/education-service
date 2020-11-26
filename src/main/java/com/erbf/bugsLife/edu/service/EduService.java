package com.erbf.bugsLife.edu.service;

import java.util.List;

import com.erbf.bugsLife.edu.application.web.dto.AcademyDetailDto;
import com.erbf.bugsLife.edu.application.web.dto.EduInfoDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewModifyDto;
import com.erbf.bugsLife.edu.domain.EduReview;

public interface EduService {
	public abstract void eduReviewCreate(EduReviewDto eduReviewDto);
	public abstract List<EduInfoDto> eduInfoList();
	public abstract EduInfoDto eduInfo(String eid);
	public abstract AcademyDetailDto academyDetail(String aid);
	public abstract void eduReviewDelete(Long rid);
	public abstract void eduReviewUpdate(Long rid, EduReviewModifyDto eduReviewDto);
	public EduReview getReview(Long rid);
	public Long checkReview(Long wid, String eid);
}
