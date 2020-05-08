package com.bhishiapp.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhishiapp.entities.Installment;
import com.bhishiapp.services.InstallmentService;
import com.bhishiapp.services.MemberService;

@Controller
public class UserController {

	@Autowired
	private InstallmentService iService; 
	
	@GetMapping("/userProfile")
	public String userProfile(Principal principal, Model model) {
		String name = principal.getName();
		List<Object[]> userDetails = iService.getInstallmentByMemberName(name);
		model.addAttribute("userDetails", userDetails);
		return "views/user/userDetails";
	}
}
