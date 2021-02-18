package com.securemetric.springmvcforms.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.securemetric.springmvcforms.dao.ApplicantDAO;
import com.securemetric.springmvcforms.entity.Applicant;
import com.securemetric.springmvcforms.model.ApplicantInfo;

public class ApplicantDAOImpl implements ApplicantDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Applicant findApplicant(String id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Applicant.class);
		crit.add(Restrictions.eq("id", id));
		return (Applicant) crit.uniqueResult();
	}

	@Override
	public ApplicantInfo findApplicantInfo(String id) {
		Applicant applicant = this.findApplicant(id);
		if (applicant == null) {
			return null;
		}
		return new ApplicantInfo(applicant.getId(), applicant.getName(), applicant.getEmail(), applicant.getGender(), applicant.getPosition(), applicant.getSkills());
	}

	@Override
	public List<ApplicantInfo> listApplicantInfos() {
		String sql = "Select new " + ApplicantInfo.class.getName() + "(a.id, a.name, a.email, a.gender, a.position, a.skills) " + " from " + Applicant.class.getName() + " a ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);

		return query.list();
	}

	@Override
	public void saveApplicant(ApplicantInfo applicantInfo) {
		String id = applicantInfo.getId();
		Applicant applicant = null;
		if (id != null) {
			applicant = this.findApplicant(id);
		}
		boolean isNew = false;
		if (applicant == null) {
			isNew = true;
			applicant = new Applicant();
			applicant.setId(UUID.randomUUID().toString());
		}

		applicant.setEmail(applicantInfo.getEmail());
		applicant.setGender(applicantInfo.getGender());
		applicant.setName(applicantInfo.getName());
		applicant.setPosition(applicantInfo.getPosition());
		String skillsString = applicantInfo.getSkillsString();
		applicant.setSkills(skillsString);

		Session session = this.sessionFactory.getCurrentSession();
		
		//session.persist(applicant);
		
		if(isNew) {		
			session.persist(applicant);		
		} else {
			session.evict(applicant);
			session.update(applicant);
		}
	}

	@Override
	public void deleteApplicant(String id) {
		Applicant applicant = this.findApplicant(id);
		if (applicant != null) {
			this.sessionFactory.getCurrentSession().delete(applicant);
		}
	}
}
