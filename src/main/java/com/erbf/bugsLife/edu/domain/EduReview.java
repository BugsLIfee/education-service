package com.erbf.bugsLife.edu.domain;

import com.erbf.bugsLife.edu.application.web.dto.EduReviewDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "REVIEW_SEQ_GEN",
		sequenceName = "review_seq",
		initialValue = 1,
		allocationSize = 2
	)
@Entity
public class EduReview {
	
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "REVIEW_SEQ_GEN"
	)
	private Long id;
	
	private String writerId;
	private String eduId;
	private String academyId;
	private String title;
	private String recommend;
	private String unrecommend;
	private String registDate;
    private String updateDate;
    
    @Column(columnDefinition = "float default 0")
	private float eduRate;
    @Column(columnDefinition = "float default 0")
	private float lecRate;
    @Column(columnDefinition = "float default 0")
	private float facRate;
    @Column(columnDefinition = "float default 0")
	private float empRate;
    @Column(columnDefinition = "float default 0")
	private double reviewsRate;
	
	@Column(columnDefinition = "integer default 0")
	private int adReport;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isBlind;
	
	@Builder
	public EduReview(Long id, String writerId, String eduId, String academyId, String title, String recommend, String unrecommend,
			String registDate, String updateDate, float eduRate, float lecRate, float facRate, float empRate, double reviewsRate, int adReport,
			boolean isBlind) {
		super();
		this.id = id;
		this.writerId = writerId;
		this.eduId = eduId;
		this.academyId = academyId;
		this.title = title;
		this.recommend = recommend;
		this.unrecommend = unrecommend;
		this.registDate = registDate;
		this.updateDate = updateDate;
		this.eduRate = eduRate;
		this.lecRate = lecRate;
		this.facRate = facRate;
		this.empRate = empRate;
		this.reviewsRate = reviewsRate;
		this.adReport = adReport;
		this.isBlind = isBlind;
	}
	
	public EduReviewDto toDto() {
		EduReviewDto eduInfoDto = EduReviewDto.builder()
				.id(this.id)
				.writerId(this.writerId)
				.eduId(this.eduId)
				.academyId(this.academyId)
				.title(this.title)
				.recommend(this.recommend)
				.unrecommend(this.unrecommend)
				.registDate(this.registDate)
				.updateDate(this.updateDate)
				.eduRate(this.eduRate)
				.lecRate(this.lecRate)
				.facRate(this.facRate)
				.empRate(this.empRate)
				.reivewsRate(this.reviewsRate)
				.adReport(this.adReport)
				.isBlind(this.isBlind)
				.build();
		
		return eduInfoDto;
	}
	
	
}
