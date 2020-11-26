package com.erbf.bugsLife.edu.application.web.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EduInfoDto {

	private String id;
	private String academyId;
	private String title;
	private String academyTitle;
	private String simpleAddr;
	private String telNo;
	private String startDate;
	private String endDate;
	private String eduCode;
	private String eduDegr;
	private double eduRate;
	
	private int reviewCnt;
	private List<EduReviewDto> reviews;
	
}
