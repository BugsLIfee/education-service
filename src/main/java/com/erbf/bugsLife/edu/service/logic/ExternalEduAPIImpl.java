package com.erbf.bugsLife.edu.service.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.erbf.bugsLife.edu.domain.EduAcademy;
import com.erbf.bugsLife.edu.domain.EduInfo;
import com.erbf.bugsLife.edu.repository.EduAcademyRepository;
import com.erbf.bugsLife.edu.repository.EduInfoRepository;
import com.erbf.bugsLife.edu.service.ExternalEduAPI;

@Service
public class ExternalEduAPIImpl implements ExternalEduAPI {

	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	@Autowired
	EduInfoRepository eduInfoRepo;

	@Autowired
	EduAcademyRepository eduAcademyRepo;

	@Override
	public int eduInfoParsingAdd() {
		String key = "AK3gE07GzZVEwkfKh3g9H2KCnMI8n6Dz";

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat date = new SimpleDateFormat("yyyyMMdd");
		cal.add(Calendar.DATE, -7);
		String startDate = date.format(cal.getTime());
		System.out.println(startDate);
		cal.add(Calendar.DATE, 21);
		String endDate = date.format(cal.getTime());
		System.out.println(endDate);

		int page = 1;

		try {
			while (true) {
				// parsing
				String url = "http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_1.jsp?"
						+ "returnType=XML&authKey=" + key + "&pageNum=" + page + "&pageSize=100&srchTraStDt="
						+ startDate + "&srchTraEndDt=" + endDate
						+ "&outType=1&sort=ASC&sortCol=TR_STT_DT&srchKeco1=20&srchKeco2=2001";

				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				// root tag
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				NodeList HRDNode = doc.getElementsByTagName("HRDNet");
				Node hNode = HRDNode.item(0);
				Element scn_cnt = (Element) hNode;
				System.out.println("total count :" + getTagValue("scn_cnt", scn_cnt));
				int total_cnt = Integer.parseInt(getTagValue("scn_cnt", scn_cnt));

				NodeList eduList = doc.getElementsByTagName("scn_list");
				for (int temp = 0; temp < eduList.getLength(); temp++) {
					Node eduNode = eduList.item(temp);
					if (eduNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) eduNode;
						eduInfoRepo.save(EduInfo.builder()
								.id(getTagValue("trprId", eElement) + getTagValue("trprDegr", eElement))
								.academyId(getTagValue("instCd", eElement))
								.title(getTagValue("title", eElement))
								.academyTitle(getTagValue("subTitle", eElement))
								.simpleAddr(getTagValue("address", eElement))
								.telNo(getTagValue("telNo", eElement))
								.startDate(getTagValue("traStartDate", eElement))
								.endDate(getTagValue("traEndDate", eElement))
								.eduCode(getTagValue("trprId", eElement))
								.eduDegr(getTagValue("trprDegr", eElement))
								.build());
						eduAcademyParsingAdd(getTagValue("instCd", eElement), getTagValue("subTitle", eElement),
								getTagValue("telNo", eElement), getTagValue("trprId", eElement),
								getTagValue("trprDegr", eElement));
					} // for end
				} // if end
				
				System.out.println("page number : " + page);
				if (page > (total_cnt / 100)) {
					return 1;
				}
				page += 1;
			} // while end
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} // try~catch end

	}

	@Override
	public void eduAcademyParsingAdd(String academyId, String academyTitle, String telNo, String eduCode,
			String eduDegr) {
		String key = "AK3gE07GzZVEwkfKh3g9H2KCnMI8n6Dz";

		try {
			// parsing
			String url = "http://www.hrd.go.kr/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_2.jsp?" + "returnType=XML&authKey="
					+ key + "&srchTrprId=" + eduCode + "&srchTrprDegr=" + eduDegr + "&outType=2&srchTorgId=default";
			
			System.out.println(url);

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// root tag
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			// �Ľ��� tag
			NodeList baseInfo = doc.getElementsByTagName("inst_base_info");
			Node bNode = baseInfo.item(0);
			System.out.println(academyId);
			if (bNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) bNode;
				eduAcademyRepo.save(EduAcademy.builder()
					.id(academyId)
					.address(getTagValue("addr1", eElement))
					.telephone(telNo)
					.title(academyTitle)
					.homePage(getTagValue("hpAddr", eElement))
					.build()
				);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} // try~catch end

	}

}
