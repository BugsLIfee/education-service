package com.erbf.bugsLife.edu.service;

public interface ExternalEduAPI {
	
	//XML API ȣ�� ��� �޼ҵ� ����
	public abstract int eduInfoParsingAdd();
	public abstract void eduAcademyParsingAdd(String academyId, String academyTitle, String telNo, String eduCode, String eduDegr);
}
