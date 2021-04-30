package com.ssafy.study.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.study.model.StudyDto;
import com.ssafy.study.model.MemberDto;
import com.ssafy.study.model.service.StudyService;
import com.ssafy.util.PageNavigation;

@Controller
@RequestMapping("/article")
public class StudyController {

	@Autowired
	private StudyService studyService;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "study/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(StudyDto studyDto, Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if(memberDto != null) {
			
			
		
			try {
				System.out.println(studyDto.getName()+" "+studyDto.getCntId());
				studyService.writeArticle(studyDto);
				return "study/writesuccess";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "문제가 발생했습니다.");
				return "error/error";
			}
		} else {
			model.addAttribute("msg", "로그인 후 사용 가능한 페이지입니다.");
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam Map<String, String> map, Model model) {
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");//sizePerPage
		try {
			List<StudyDto> list = studyService.listArticle(map);
			PageNavigation pageNavigation = studyService.makePageNavigation(map);
			model.addAttribute("articles", list);
			model.addAttribute("navigation", pageNavigation);
			return "study/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "스터디목록을 얻어오는 중 문제가 발생했습니다.");
			return "error/error";
		}
	}
	
	@GetMapping("/join")
	public String joinStudy(@RequestParam Map<String, String> map, Model model) {
		try {
			studyService.joinStudy(map);
			model.addAttribute("isSuccess", "succ");
			
			return "forward:/article/list?pg=1&key=&word=";
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			model.addAttribute("isSuccess", "fail");
			
			return "forward:/article/list?pg=1&key=&word=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "글수정 처리 중 문제가 발생했습니다.");
			return "error/error";
			
		}
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("articleno") int articleno, Model model) {
		try {
			StudyDto studyDto = studyService.getArticle(articleno);
			model.addAttribute("article", studyDto);
			return "study/modify";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "글수정 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(StudyDto studyDto, Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
	

			try {
				studyService.modifyArticle(studyDto);
				return "study/writesuccess";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "글수정중 문제가 발생했습니다.");
				return "error/error";
			}
		} else {
			model.addAttribute("msg", "로그인 후 사용 가능한 페이지입니다.");
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("articleno") int articleno, Model model) {
		try {
			studyService.deleteArticle(articleno);
			return "redirect:list?pg=1&key=&word=";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "글삭제 처리 중 문제가 발생했습니다.");
			return "error/error";
		}
	}
	
}
