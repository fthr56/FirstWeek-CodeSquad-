package com.example.demo.user;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	ArrayList<User> users = new ArrayList();

	@GetMapping("user/{userId}/form")
	public ModelAndView modify(@PathVariable String userId) {
		System.out.println("modify");
		ModelAndView mav = new ModelAndView("/user/updateForm");

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserId().equals(userId)) {
				mav.addObject("user", users.get(i));
				break;
			}
		}
		return mav;
	}

	@PostMapping("/update")
	public ModelAndView update(User user) {
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getUserId().equals(user.getUserId())) {
				users.set(i, user);
			}
		}
		return new ModelAndView("redirect:/users");
	}

	@GetMapping("/users/{index}")
	public ModelAndView show(@PathVariable int index) {
		System.out.println("index = " + index);
		ModelAndView mav = new ModelAndView("/user/profile");
		mav.addObject("user", users.get(index));
		return mav;
	}

	@PostMapping("/users")
	public ModelAndView create(User user) {
		users.add(user);
		return new ModelAndView("redirect:/users");
	}

	@GetMapping("/users")
	public ModelAndView list() {

		ModelAndView mav = new ModelAndView("/user/list");
		mav.addObject("users", users);
		return mav;
	}
}
