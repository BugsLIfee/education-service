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
public class AcademyDetailDto {
	
	private EduAcademyDto eduAcademys;
	
	private List<EduInfoDto> eduInfos;
	
}
