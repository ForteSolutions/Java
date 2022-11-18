package com.codingdojo.loginandregister.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.loginandregister.models.LoginUser;
import com.codingdojo.loginandregister.models.User;
import com.codingdojo.loginandregister.services.UserService;

@Controller
public class HomeController {
	private final UserService userService;
    
    public HomeController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public String registerForm(Model model) {
    	model.addAttribute("user", new User());
    	model.addAttribute("loginUser", new LoginUser());
        return "index.jsp";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
    	User u = userService.registerUser(user, result);
		if(result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "index.jsp";
		}
    	session.setAttribute("userId", u.getId());
    	return "redirect:/welcome";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
    	System.out.println(loginUser.getEmail());
		boolean isAuthenticated = userService.authenticateUser(loginUser.getEmail(), loginUser.getPassword());
		if(isAuthenticated) {
			User u = userService.findByEmail(loginUser.getEmail());
			session.setAttribute("userId", u.getId());
			return "redirect:/welcome";
		} 
		
		else {
			model.addAttribute("error", "Please use valid login credentials");
			model.addAttribute("user", new User());
			return "index.jsp";
		}
    }
    
    @RequestMapping("/welcome")
    public String home(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		return "welcome.jsp";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
    }
}