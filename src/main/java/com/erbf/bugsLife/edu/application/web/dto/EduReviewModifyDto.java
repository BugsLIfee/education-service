package com.erbf.bugsLife.edu.application.web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.erbf.bugsLife.edu.domain.EduReview;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EduReviewModifyDto {
	private Long id;
	private String title;
	private String recommend;
	private String unrecommend;
	private float eduRate;
	private float empRate;
	private float facRate;
	private float lecRate;

	public EduReview toEntity(EduReview newReview) {
		return EduReview.builder()
				.id(this.id)
				.writerId(newReview.getWriterId())
				.eduId(newReview.getEduId())
				.academyId(newReview.getAcademyId())
		 		.title(this.title)
				.recommend(this.recommend)
				.unrecommend(this.unrecommend)
				.registDate(newReview.getRegistDate())
				.updateDate(EduReviewModifyDto.getDate())
				.eduTitle(newReview.getEduTitle())
				.eduRate(this.eduRate)
				.lecRate(this.lecRate)
				.facRate(this.facRate)
				.empRate(this.empRate)
				.reviewsRate(
						(this.eduRate
						+this.lecRate
						+this.facRate
						+this.empRate)/4
						)
				.adReport(newReview.getAdReport())
				.isBlind(newReview.isBlind())
				.build();
	}
	
	private static String getDate() {
        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        return transFormat.format(date);
    }
}