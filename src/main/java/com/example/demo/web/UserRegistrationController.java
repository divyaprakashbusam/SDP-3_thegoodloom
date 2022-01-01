package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.global.GlobalData;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserRegistrationDto;

@Controller
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@RequestMapping(value="/registration",method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "registration";
	}
	
	
	@RequestMapping(value="/registration",method = RequestMethod.POST)
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
	
	
	@GetMapping("/")
	public String Home() {
		return "home";
	}
	
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String showIndexForm(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	}
	

}
