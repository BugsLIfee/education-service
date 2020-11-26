package com.erbf.bugsLife.edu.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.erbf.bugsLife.edu.application.web.dto.EduAcademyDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class EduAcademy {
	
	@Id
	private String id;
	
	private String address;
	private String telephone;
	private String title;
	private String homePage;
	private String imgUrl;
	
	public EduAcademyDto toDto() {
		EduAcademyDto eduAcademyDto = EduAcademyDto.builder()
				.id(this.id)
				.address(this.address)
				.telephone(this.telephone)
				.title(this.title)
				.homePage(this.homePage)
				.imgUrl(this.imgUrl)
				.build();
		
		return eduAcademyDto;
	}
	
}
