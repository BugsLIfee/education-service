package com.erbf.bugsLife.edu.application.web.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EduDetailDto {
	
	private EduInfoDto eduInfo;
	
	private List<EduReviewDto> eduReviews;

}
