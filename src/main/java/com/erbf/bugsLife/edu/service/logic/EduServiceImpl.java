package com.erbf.bugsLife.edu.service.logic;

import com.erbf.bugsLife.edu.application.web.dto.AcademyDetailDto;
import com.erbf.bugsLife.edu.application.web.dto.EduAcademyDto;
import com.erbf.bugsLife.edu.application.web.dto.EduInfoDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewDto;
import com.erbf.bugsLife.edu.domain.EduAcademy;
import com.erbf.bugsLife.edu.domain.EduInfo;
import com.erbf.bugsLife.edu.domain.EduReview;
import com.erbf.bugsLife.edu.repository.EduAcademyRepository;
import com.erbf.bugsLife.edu.repository.EduInfoRepository;
import com.erbf.bugsLife.edu.repository.EduReviewRepository;
import com.erbf.bugsLife.edu.service.EduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EduServiceImpl implements EduService{
	
	@Autowired
	EduReviewRepository eduReviewRepo;
	
	@Autowired
	EduInfoRepository eduInfoRepo;
	
	@Autowired
	EduAcademyRepository eduAcademyRepo;
	
	@Override
	public List<EduInfoDto> eduInfoList() {
		List<EduInfo> eduInfos = eduInfoRepo.findAll();
		List<EduInfoDto> eduInfoDtos = eduInfos.stream().map(EduInfo::toDto).collect(Collectors.toList());
		eduInfoDtos.stream().forEach(
				eduInfoDto -> eduInfoDto.setReviewCnt(
						eduReviewRepo.findByEduId(eduInfoDto.getId()).size()
				)
		);
		
//		int result = 0;
		eduInfoDtos.stream().forEach(
				eduInfoDto -> eduInfoDto.setEduRate(eduReviewRepo.findByEduId(eduInfoDto.getId()).isEmpty() ? 0 :
						eduReviewRepo.findByEduId(eduInfoDto.getId()).stream()
						.map(EduReview::toDto).collect(Collectors.toList())
						.stream().mapToDouble(EduReviewDto::getReivewsRate).sum()
						/eduReviewRepo.findByEduId(eduInfoDto.getId()).size()
				)
		);
//		(
//				eduReviewRepo.findByEduId(eduInfoDto.getId()).stream().map(EduReview::toDto)
//				.reduce((r,o) -> (r.reviewRating + o.reviewRating))
//		)
		
		return eduInfoDtos;
	}
	

	@Override
	public EduInfoDto eduInfo(String eid) {
		Optional<EduInfo> eduInfo = eduInfoRepo.findById(eid);
		EduInfoDto eduInfoDto = eduInfo.map(EduInfo::toDto).get();
		
		eduInfoDto.setEduRate(
//				eduReviewRepo.findByEduId(eduInfoDto.getId()).isEmpty() ? 0 :
						eduReviewRepo.findByEduId(eduInfoDto.getId()).stream()
						.map(EduReview::toDto).collect(Collectors.toList())
						.stream().mapToDouble(EduReviewDto::getReivewsRate).sum()
						/eduReviewRepo.findByEduId(eduInfoDto.getId()).size());
		
//		List<EduReview> eduReviews = eduReviewRepo.findByEduId(eid);
//		List<EduReviewDto> eduReviewDtos = eduReviews.stream().map(EduReview::toDto).collect(Collectors.toList());
//		eduInfoDto.setReviews(eduReviews.stream().map(EduReview::toDto).collect(Collectors.toList()));
		
//		EduDetailDto eduDetaiDto = EduDetailDto.builder()
//				.eduInfo(eduInfoDto)
//				.eduReviews(eduReviewDtos)
//				.build();
				
//		return eduDetaiDto;
		
		eduInfoDto.setReviews(eduReviewRepo.findByEduId(eduInfoDto.getId()).stream()
					.map(EduReview::toDto).collect(Collectors.toList()));
		
		return eduInfoDto;
	}
	

	@Override
	public AcademyDetailDto academyDetail(String aid) {
		Optional<EduAcademy> academy = eduAcademyRepo.findById(aid);
		EduAcademyDto academyDto = academy.map(EduAcademy::toDto).get();
		
		List<EduInfo> infos = eduInfoRepo.findByAcademyId(aid);
		List<EduInfoDto> infoDtos = infos.stream().map(EduInfo::toDto).collect(Collectors.toList());
		
//		List<EduReview> reviews = eduReviewRepo.findByAcademyId(aid);
//		List<EduReviewDto> reviewDtos = reviews.stream().map(EduReview::toDto).collect(Collectors.toList());
		
		infoDtos.stream().forEach(
					infoDto -> infoDto.setReviews(eduReviewRepo.findByEduId(infoDto.getId()).stream()
					.map(EduReview::toDto).collect(Collectors.toList())));
		
		AcademyDetailDto academyDetailDto = AcademyDetailDto.builder()
				.eduAcademys(academyDto)
				.eduInfos(infoDtos)
				.build();
		
		return academyDetailDto;
	}

	@Override
	public void eduReviewCreate(EduReviewDto eduReviewDto) {
		eduReviewRepo.save(EduReview.builder()
				.id(eduReviewDto.getId())
				.writerId(eduReviewDto.getWriterId())
				.eduId(eduReviewDto.getEduId())
				.academyId(eduReviewDto.getAcademyId())
				.title(eduReviewDto.getTitle())
				.recommend(eduReviewDto.getRecommend())
				.unrecommend(eduReviewDto.getUnrecommend())
				.registDate(EduServiceImpl.getDate())
				//.updateDate(EduServiceImpl.getDate())
				.eduRate(eduReviewDto.getEduRate())
				.lecRate(eduReviewDto.getLecRate())
				.facRate(eduReviewDto.getFacRate())
				.empRate(eduReviewDto.getEmpRate())
				.reviewsRate(
						(eduReviewDto.getEduRate()
						+eduReviewDto.getLecRate()
						+eduReviewDto.getFacRate()
						+eduReviewDto.getEmpRate())/4
						)
				.build());
	}
	
	private static String getDate() {
        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        return transFormat.format(date);
    }

}

