package com.securemetric.springmvcforms.dao;

import java.util.List;

import com.securemetric.springmvcforms.entity.Applicant;
import com.securemetric.springmvcforms.model.ApplicantInfo;

public interface ApplicantDAO {
	
	public Applicant findApplicant(String id);
	
	public List<ApplicantInfo> listApplicantInfos();
	
	public void saveApplicant(ApplicantInfo applicantInfo);
	
	public ApplicantInfo findApplicantInfo(String id);
	
	public void deleteApplicant(String id);
	
}
