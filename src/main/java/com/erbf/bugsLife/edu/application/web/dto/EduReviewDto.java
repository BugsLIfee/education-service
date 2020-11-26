package com.erbf.bugsLife.edu.application.web.dto;

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
public class EduReviewDto {
	
	private Long id;
	private Long writerId;
	private String writerName;
	private int writerLevel;
	private String eduId;
	private String academyId;
	private String title;
	private String recommend;
	private String unrecommend;
	private String registDate;
	private String updateDate;
	private String eduTitle;
	private float eduRate;
	private float lecRate;
	private float facRate;
	private float empRate;
	private double reivewsRate;
	private int adReport;
	private boolean isBlind;
	
}
