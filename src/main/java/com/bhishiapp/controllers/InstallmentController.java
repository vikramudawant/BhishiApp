package com.bhishiapp.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.entities.Installment;
import com.bhishiapp.entities.Member;
import com.bhishiapp.services.BhishiService;
import com.bhishiapp.services.InstallmentService;
import com.bhishiapp.services.MemberService;

@Controller
public class InstallmentController {
	
	@Autowired
	private InstallmentService iService;
	@Autowired
	private MemberService mService;
	@Autowired
	private BhishiService bService;
	
	@GetMapping("/addInstallment")
	public String installmentForm(Installment installment) {
		
		return "views/installment/installmentForm";
	}
	
	@PostMapping("/addInstallment")
	public String addInstallment(@Valid Installment installment, 
			BindingResult bindingResult, 
			Model model,
			@RequestParam(defaultValue = "0") long bhishiId, 
			@RequestParam(defaultValue = "0") long memberId) {
		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

		if(bhishiId == 0 || memberId == 0) {
			model.addAttribute("exist", true);
			return "views/installment/installmentForm";
		}
		if(bindingResult.hasErrors()) {
			return "views/installment/installmentForm";
		}
		Bhishi bhishi = bService.getBhishi(bhishiId);
		Member member = mService.getMember(memberId);
		System.out.println(bhishi+" " + member);
		installment.setBhishi(bhishi);
		installment.setMember(member);
		installment.setInstallmentDate(sqlDate);
		
		installment.setInstallmentCount(iService.getNextInstallmentCount(memberId));
		
		iService.addInstallment(installment);
		return "redirect:/installmentList";
	}
	
	@RequestMapping("/installmentList")
	public String installmentList(Model model, @RequestParam(defaultValue = "0") long bhishiId) {
		
		model.addAttribute("installments", iService.getInstallments(bhishiId));
		return "views/installment/installmentList";
	}
	
	@GetMapping("/memberInstallments")
	public String installmentByMember(@RequestParam long id, Model model) {
		List<Installment> installments = iService.getInstallmentsByMember(id);
		model.addAttribute("installments", installments);
		return "views/member/memberInstallments";
	}
	
	
	
}
