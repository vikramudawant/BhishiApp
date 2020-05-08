package com.bhishiapp.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bhishiapp.entities.Member;
import com.bhishiapp.services.BhishiService;
import com.bhishiapp.services.MemberService;
import com.bhishiapp.services.UserService;

@Controller
public class MemberController {

	@Autowired
	private MemberService mService;
	@Autowired
	private BhishiService bService;
	@Autowired
	private UserService uService;

	@GetMapping("/addMember")
	public String memberForm(Member member, Model model) {
		model.addAttribute("bhishi", bService.getAllBhishi());
		return "views/member/memberForm";
	}

	@PostMapping("/addMember")
	public String memberForm(@Valid Member member, BindingResult bindingResult, Model model,
			@RequestParam(defaultValue = "0") long bhishiId) {

		model.addAttribute("bhishi", bService.getAllBhishi());

		if (bindingResult.hasErrors() || bhishiId == 0) {
			return "views/member/memberForm";
		}

		if(member.getId() == 0) {
			if (bService.checkLimit(bhishiId)) {
				model.addAttribute("exist", "hello");
				return "views/member/memberForm";
			}
			member.setBhishi(bService.getBhishi(bhishiId));
			
			mService.save(member);
			uService.createUser(member);
		} else {
			member.setBhishi(bService.getBhishi(bhishiId));
			mService.save(member);
		}
		

		return "redirect:/memberList";
	}

	@RequestMapping("/memberList")
	public String allMemberList(Model model, @RequestParam(defaultValue = "0") long bhishiId) {
		List<Member> members = mService.getAllMembersByBhishi(bhishiId);
		model.addAttribute("members", members);
		return "views/member/memberList";
	}

	@RequestMapping("/assignRank")
	public String assignRank(Model model, @RequestParam(defaultValue = "0") long bhishiId) {
		if (bhishiId != 0) {
			List<Member> members = mService.getAllMembersByBhishi(bhishiId);
			model.addAttribute("members", members);
		}
		return "views/member/assignRank";
	}

	@GetMapping("/updateMember")
	public String updateForm(@RequestParam long id, Model model) {
		Member member = mService.getMember(id);
		model.addAttribute("member", member);

		return "views/member/updateForm";
	}

	@GetMapping("/deleteMember")
	public String deleteMember(@RequestParam long id) {
		Member member = mService.getMember(id);
		mService.delete(member);

		return "redirect:/memberList";
	}

	@GetMapping("/getAllMembers")
	@ResponseBody
	public List<Member> getAllMember(HttpServletRequest req) {
		long bhishiId = Long.parseLong(req.getParameter("bhishiId"));
		List<Member> members = mService.getMembersByBhishi(bhishiId);

		return members;
	}

	@GetMapping("/assignRankToMembers")
	public String assignRankToMembers(@RequestParam long id) {
		List<Member> members = mService.getMembersByBhishi(id);
		List<Integer> randomNo = new ArrayList<>();
		for (int i = 1; i <= members.size(); i++) {
			randomNo.add(i);
		}
		Collections.shuffle(randomNo);

		for (int i = 0; i < members.size(); i++) {
			members.get(i).setRank(randomNo.get(i));
		}
		for (int i = 0; i < members.size(); i++) {
			mService.save(members.get(i));
		}

		return "redirect:/memberList";
	}

	@RequestMapping("/paybleList")
	public String paybleList(Model model, @RequestParam(defaultValue = "0") long bhishiId) {
		if (bhishiId != 0) {
			List<Member> members = mService.getAllMembersByBhishi(bhishiId);
			members = calculatePaymentDate(members);
			model.addAttribute("members", members);
		}
		return "views/member/paybleList";
	}

	private List<Member> calculatePaymentDate(List<Member> members) {
		for (int i = 0; i < members.size(); i++) {
			Date mySqlDate = members.get(i).getBhishi().getStartDate();
			LocalDate ld = mySqlDate.toLocalDate();
			LocalDate newDate = ld.plusMonths(members.get(i).getRank());
			Date paybleDate = Date.valueOf(newDate);
			members.get(i).setPaybleDate(paybleDate);
		}
		return members;
	}

}
