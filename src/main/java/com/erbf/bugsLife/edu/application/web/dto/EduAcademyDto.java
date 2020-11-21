package com.erbf.bugsLife.edu.application.web.dto;

import lombok.*;

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
	
	
//	@Builder
//	public EduAcademyDto(String id, String address, String telephone, String title, String homePage, String imgUrl) {
//		super();
//		this.id = id;
//		this.address = address;
//		this.telephone = telephone;
//		this.title = title;
//		this.homePage = homePage;
//		this.imgUrl = imgUrl;
//	}
	
//	public EduAcademy toEntity() {
//		return EduAcademy.builder()
//				.id(this.id)
//				.address(this.address)
//				.telephone(this.telephone)
//				.title(this.title)
//				.homePage(this.homePage)
//				.imgUrl(this.imgUrl)
//				.build();
//	}
	
}
