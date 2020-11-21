package com.erbf.bugsLife.edu.domain;

import com.erbf.bugsLife.edu.application.web.dto.EduInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class EduInfo {
	
	@Id
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
	
	@Builder
	public EduInfo(String id, String academyId, String title, String academyTitle, String simpleAddr, String telNo, String startDate, String endDate, String eduCode,
			String eduDegr) {
		super();
		this.id = id;
		this.academyId = academyId;
		this.title = title;
		this.academyTitle = academyTitle;
		this.simpleAddr = simpleAddr;
		this.telNo = telNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eduCode = eduCode;
		this.eduDegr = eduDegr;
	}
	
	public EduInfoDto toDto() {
		EduInfoDto eduInfoDto = EduInfoDto.builder()
				.id(this.id)
				.academyId(this.academyId)
				.title(this.title)
				.academyTitle(this.academyTitle)
				.simpleAddr(this.simpleAddr)
				.telNo(this.telNo)
				.startDate(this.startDate)
				.endDate(this.endDate)
				.eduCode(this.eduCode)
				.eduDegr(this.eduDegr)
				.build();
		
		return eduInfoDto;
	}

}
