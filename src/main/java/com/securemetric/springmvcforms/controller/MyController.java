package com.securemetric.springmvcforms.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.securemetric.springmvcforms.dao.ApplicantDAO;
import com.securemetric.springmvcforms.model.ApplicantInfo;
import com.securemetric.springmvcforms.validator.ApplicantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// Hibernate Transaction
@Transactional
// RedirectAttributes
@EnableWebMvc
public class MyController {

	@Autowired
	private ApplicantDAO applicantDAO;
	
	@Autowired
	private ApplicantValidator applicantValidator;
	
	@RequestMapping("/")
	public String homePage(Model model) {
		return applicantList(model);
	}
	
	@RequestMapping("/applicantList")
	public String applicantList(Model model) {
		List<ApplicantInfo> list = applicantDAO.listApplicantInfos();
		model.addAttribute("applicantInfos", list);
		return "applicantList";
	}
	
	private Map<String, String> dataForPositions() {
		Map<String, String> positionMap = new LinkedHashMap<String, String>();
		positionMap.put("Developer", "Developer");
		positionMap.put("Leader", "Leader");
		positionMap.put("Tester", "Tester");
		return positionMap;
	}
	
	private List<String> dataForSkills(){
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("C/C++");
		list.add("C#");
		return list;
	}
	
	private String formApplicant(Model model, ApplicantInfo applicantInfo) {
		model.addAttribute("applicantForm", applicantInfo);
		Map<String, String> positionMap = this.dataForPositions();
		model.addAttribute("positionMap", positionMap);
		List<String> list = dataForSkills();
		model.addAttribute("skills", list);
		
		if(applicantInfo.getId() == null) {
			model.addAttribute("formTitle", "Create Applicant");
		} else {
			model.addAttribute("formTitle", "Edit Applicant");
		}
		
		return "formApplicant";
	}
	
	@RequestMapping("/createApplicant")
	public String createApplicant(Model model) {
		ApplicantInfo applicantInfo = new ApplicantInfo();
		return this.formApplicant(model, applicantInfo);
	}
	
	@RequestMapping("/editApplicant")
	public String editApplicant(Model model, @RequestParam("id") String id) {
		ApplicantInfo applicantInfo = null;
		if(id != null) {
			applicantInfo = this.applicantDAO.findApplicantInfo(id);
		}
		if(applicantInfo == null) {
			return "redirect:/applicantList";
		}
		return this.formApplicant(model, applicantInfo);
	}
	
	@RequestMapping("/deleteApplicant")
	public String deleteApplicant(Model model, @RequestParam("id") String id) {
		if(id != null) {
			this.applicantDAO.deleteApplicant(id);
		}
		return "redirect:/applicantList";
	}
	
	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form muc tieu
		Object target = dataBinder.getTarget();
		if(target == null) {
			return;
		}
		System.out.println("Target=" + target);
		
		if(target.getClass() == ApplicantInfo.class) {
			dataBinder.setValidator(applicantValidator);
		}
	}	
	
	// Save or update Applicant
	// 1. @ModelAttribute bind form value
	// 2. @Validated form validator
	// 3. RedirectAttributes for flash value
	@RequestMapping(value = "/saveApplicant", method = RequestMethod.POST)
	public String saveApplicant(Model model, @ModelAttribute("applicantForm") @Validated ApplicantInfo applicantInfo, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		// Neu validate loi
		if(result.hasErrors()) {
			return this.formApplicant(model, applicantInfo);
		}
		
		this.applicantDAO.saveApplicant(applicantInfo);
		
		// Quan trong! Can co @EnableWebMvc
		// Them message toi scope flash
		redirectAttributes.addFlashAttribute("message", "Save Applicant Successful");
		
		return "redirect:/applicantList";
	}
}











