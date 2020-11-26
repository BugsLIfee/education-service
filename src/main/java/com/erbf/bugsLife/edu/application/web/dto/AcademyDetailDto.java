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
public class AcademyDetailDto {
	
	private EduAcademyDto eduAcademys;
	
	private List<EduInfoDto> eduInfos;
	
}
