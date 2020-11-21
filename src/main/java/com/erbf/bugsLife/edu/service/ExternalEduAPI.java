package com.erbf.bugsLife.edu.service;

public interface ExternalEduAPI {
	
	//XML API 호출 기능 메소드 정의
	public abstract int eduInfoParsingAdd();
	public abstract void eduAcademyParsingAdd(String academyId, String academyTitle, String telNo, String eduCode, String eduDegr);
}
