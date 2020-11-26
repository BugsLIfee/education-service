package com.erbf.bugsLife.edu.application.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EduAcademyDto {
	
	private String id;
	private String address;
	private String telephone;
	private String title;
	private String homePage;
	private String imgUrl;
	private double academyRate;
	
}
