package com.erbf.bugsLife.edu.service.logic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erbf.bugsLife.edu.application.web.dto.AcademyDetailDto;
import com.erbf.bugsLife.edu.application.web.dto.EduAcademyDto;
import com.erbf.bugsLife.edu.application.web.dto.EduInfoDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewDto;
import com.erbf.bugsLife.edu.application.web.dto.EduReviewModifyDto;
import com.erbf.bugsLife.edu.domain.EduAcademy;
import com.erbf.bugsLife.edu.domain.EduInfo;
import com.erbf.bugsLife.edu.domain.EduReview;
import com.erbf.bugsLife.edu.repository.EduAcademyRepository;
import com.erbf.bugsLife.edu.repository.EduInfoRepository;
import com.erbf.bugsLife.edu.repository.EduReviewRepository;
import com.erbf.bugsLife.edu.service.EduService;


@Service
public class EduServiceImpl implements EduService{
	
	@Autowired
	EduReviewRepository eduReviewRepo;
	
	@Autowired
	EduInfoRepository eduInfoRepo;
	
	@Autowired
	EduAcademyRepository eduAcademyRepo;
	
//	@Autowired
//	UserRepository userRepo;
	
	@Override
	public List<EduInfoDto> eduInfoList() {
		List<EduInfo> eduInfos = eduInfoRepo.findAllByOrderByStartDateDesc();
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
						eduReviewRepo.findByEduId(eid).stream()
						.map(EduReview::toDto).collect(Collectors.toList())
						.stream().mapToDouble(EduReviewDto::getReivewsRate).sum()
						/eduReviewRepo.findByEduId(eid).size());
		
		List<EduReview> reviews = eduReviewRepo.findByEduId(eid);
		List<EduReviewDto> reviewDtos = reviews.stream().map(EduReview::toDto).collect(Collectors.toList());
		
//		reviewDtos.stream().forEach(
//				reviewDto -> {
//					User writer = userRepo.findById(reviewDto.getWriterId()).get();
//					reviewDto.setWriterName(writer.getName());
//					reviewDto.setWriterLevel(writer.getLevel());
//				}
//		);
		
//		eduInfoDto.setReviews(reviews.stream()
//					.map(EduReview::toDto).collect(Collectors.toList()));
		
		eduInfoDto.setReviews(reviewDtos);
		
		return eduInfoDto;
	}
	

	@Override
	public AcademyDetailDto academyDetail(String aid) {
		Optional<EduAcademy> academy = eduAcademyRepo.findById(aid);
		EduAcademyDto academyDto = academy.map(EduAcademy::toDto).get();
		
		academyDto.setAcademyRate(
				eduReviewRepo.findByAcademyId(aid).stream()
				.map(EduReview::toDto).collect(Collectors.toList())
				.stream().mapToDouble(EduReviewDto::getReivewsRate).sum()
				/eduReviewRepo.findByAcademyId(aid).size()
		);
		
		List<EduInfo> infos = eduInfoRepo.findByAcademyId(aid);
		List<EduInfoDto> infoDtos = infos.stream().map(EduInfo::toDto).collect(Collectors.toList());
		
		infoDtos.stream().forEach(
					infoDto -> infoDto.setReviews(eduReviewRepo.findByEduId(infoDto.getId()).stream()
					.map(EduReview::toDto).collect(Collectors.toList()))
		);
		
//		List<EduReview> reviews = eduReviewRepo.findByEduId(aid);
//		List<EduReviewDto> reviewDtos = reviews.stream().map(EduReview::toDto).collect(Collectors.toList());
		
//		reviewDtos.stream().forEach(
//				reviewDto -> {
//					User writer = userRepo.findById(reviewDto.getWriterId()).get();
//					reviewDto.setWriterName(writer.getName());
//					reviewDto.setWriterLevel(writer.getLevel());
//				}
//		);
		
		AcademyDetailDto academyDetailDto = AcademyDetailDto.builder()
				.eduAcademys(academyDto)
				.eduInfos(infoDtos)
				.build();
		
		return academyDetailDto;
	}

	@Override
	public void eduReviewCreate(EduReviewDto eduReviewDto) {
		eduReviewRepo.save(EduReview.builder()
				.writerId(eduReviewDto.getWriterId())
				.eduId(eduReviewDto.getEduId())
				.academyId(eduReviewDto.getAcademyId())
				.title(eduReviewDto.getTitle())
				.recommend(eduReviewDto.getRecommend())
				.unrecommend(eduReviewDto.getUnrecommend())
				.registDate(EduServiceImpl.getDate())
				.eduTitle(eduReviewDto.getEduTitle())
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

	@Override
	public void eduReviewDelete(Long rid) {
		eduReviewRepo.deleteById(rid);
	}

	@Override
	public void eduReviewUpdate(Long rid, EduReviewModifyDto eduReviewDto) {
		Optional<EduReview> originalReview = eduReviewRepo.findById(rid);
		EduReview newReview = eduReviewDto.toEntity(originalReview.get());
		eduReviewRepo.save(newReview);
	}

	@Override
	public EduReview getReview(Long rid) {
		return eduReviewRepo.findById(rid).get();
	}
	
	@Override
	public Long checkReview(Long wid, String eid) {
		Long reviewCnt = eduReviewRepo.countByWriterIdAndEduId(wid,eid);
		return reviewCnt;
	}

	private static String getDate() {
        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        return transFormat.format(date);
    }

}

