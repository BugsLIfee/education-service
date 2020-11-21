package com.erbf.bugsLife.edu.application.web.dto;

import lombok.*;

import java.util.List;

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
	
//	public EduInfo toEntity() {
//		return EduInfo.builder()
//				.id(this.id)
//				.academyId(this.academyId)
//				.title(this.title)
//				.academyTitle(this.academyTitle)
//				.simpleAddr(this.simpleAddr)
//				.telNo(this.telNo)
//				.startDate(this.startDate)
//				.endDate(this.endDate)
//				.eduCode(this.eduCode)
//				.eduDegr(this.eduDegr)
//				.build();
//	}
	
	
}
