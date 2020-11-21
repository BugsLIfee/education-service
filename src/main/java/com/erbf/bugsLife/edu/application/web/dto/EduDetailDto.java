package com.erbf.bugsLife.edu.application.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EduDetailDto {
	
	private EduInfoDto eduInfo;
	
	private List<EduReviewDto> eduReviews;

}
