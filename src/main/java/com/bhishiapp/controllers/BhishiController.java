package com.bhishiapp.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhishiapp.entities.Bhishi;
import com.bhishiapp.services.BhishiService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Controller
public class BhishiController {

	@Autowired
	private BhishiService service;
		
	@GetMapping("/createBhishi")
	public String bhishiForm(Bhishi bhishi) { 
	
		return "views/bhishi/bhishiForm";
	}
	
	@PostMapping("/createBhishi")
	public String createBhishi(@Valid Bhishi bhishi, 
			BindingResult bindingResult, 
			@RequestParam(defaultValue = "true") boolean isActive) {
		
		if(bindingResult.hasErrors()) {
			return "views/bhishi/bhishiForm";
		}
		
		bhishi.setNoOfInstallments(bhishi.getNoOfMembers());
		bhishi.setActive(isActive);
		
		
		service.save(bhishi);
		
		return "redirect:/bhishiList";
	}
	
	@GetMapping("/updateBhishi")
	public String editBhishi(@RequestParam long id, Model model) { 
		Bhishi bhishi = service.getBhishi(id);
		
		model.addAttribute("bhishi", bhishi);
		return "views/bhishi/bhishiForm";
	}

	@GetMapping("/deleteBhishi")
	public String deleteBhishi(@RequestParam long id) { 
			
		service.delete(id);
		return "redirect:/bhishiList";
	}
	
	
	@GetMapping("/bhishiList")
	public String list(Model model) { 
		List<Bhishi> bhishi = service.getAllBhishi();
		
		model.addAttribute("bhishi", bhishi);
		return "views/bhishi/bhishiList";
	}
	
	@GetMapping("/getAllBhishi")
	@ResponseBody
	public List<Bhishi> getAllBhishi(){
		List<Bhishi> bhishi = service.getAllBhishi();

		return bhishi;
		
	}
	
	@GetMapping("/getMemberCompletedBhishi")
	@ResponseBody
	public List<Bhishi> getMemberCompletedBhishi(){
		List<Bhishi> bhishi = service.getMemberCompletedBhishi();

		return bhishi;
		
	}
	

}
