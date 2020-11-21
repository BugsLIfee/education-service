package com.erbf.bugsLife.edu.application.web.dto;

import com.erbf.bugsLife.edu.domain.EduReview;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EduReviewDto {
	
	private Long id;
	private String writerId;
	private String writerName;
	private String eduId;
	private String academyId;
	private String title;
	private String recommend;
	private String unrecommend;
	private String registDate;
	private String updateDate;
	private float eduRate;
	private float lecRate;
	private float facRate;
	private float empRate;
	private double reivewsRate;
	private int adReport;
	private boolean isBlind;

	
	public EduReview toEntity() {
		return EduReview.builder()
		 		.title(this.title)
				.recommend(this.recommend)
				.unrecommend(this.unrecommend)
				.eduRate(this.eduRate)
				.lecRate(this.lecRate)
				.facRate(this.facRate)
				.empRate(this.empRate)
				.build();
	}
	
	
}
