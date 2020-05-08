package com.bhishiapp.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhishiapp.services.BhishiService;
import com.bhishiapp.services.InstallmentService;
import com.bhishiapp.services.MemberService;

@Controller
public class IndexController {
	
	@Autowired
	private BhishiService service;
	@Autowired
	private MemberService mService;
	@Autowired
	private InstallmentService iService;

	@GetMapping("/") 
	public String home(Model model) {
		
		int bCount = service.getBhishiCount();
		int mCount = mService.getMemberCount();
		int iCount = iService.getInstallmentCount();
		
		model.addAttribute("bhishi", bCount);
		model.addAttribute("members", mCount);
		model.addAttribute("installments", iCount);
		return "index";
	}
	
	@GetMapping("/error") 
	public String errorPage() {
		return "error";
	}
	
	@GetMapping("/about") 
	public String aboutPage() {
		return "about";
	}
	
	@RequestMapping("/login") 
	public String loginPage(Principal principal) {
		return principal == null ? "login" : "redirect:/";
	}
	
	@RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/";
        }
        return "redirect:/userProfile";
    }
}
