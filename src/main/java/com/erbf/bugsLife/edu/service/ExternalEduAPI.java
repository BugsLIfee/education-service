package com.erbf.bugsLife.edu.service;

public interface ExternalEduAPI {
	public abstract int eduInfoParsingAdd();
	public abstract void eduAcademyParsingAdd(String academyId, String academyTitle, String telNo, String eduCode, String eduDegr);
}
