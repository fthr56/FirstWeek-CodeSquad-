package com.example.demo.qna;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class questionController {
	ArrayList<Question> questions = new ArrayList();
	
	@PostMapping("/question")
	public ModelAndView write(Question question) {
		questions.add(question);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/")
	public ModelAndView lsit() {
		ModelAndView mav = new ModelAndView("/index");
		mav.addObject("questions", questions);
		return mav;
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/index");
		mav.addObject("questions", questions);
		return mav;
	}
	
	@GetMapping("/questions/{index}")
	public ModelAndView show(@PathVariable int index) {
		ModelAndView mav = new ModelAndView("qna/show");
		mav.addObject("question", questions.get(index));
		return mav;
	}
}
