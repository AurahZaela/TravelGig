package com.synex.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.synex.domain.User;
import com.synex.service.UserService;



@Controller
public class GatewayController {
	
	@Autowired 
	UserService userService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Principal principal, Model model) {
		modelData(principal, model);
		return "Home";
	}
	
	
	@RequestMapping(value="mybookings", method=RequestMethod.GET)
	    public String myBookings(Principal principal, Model model) {
	        modelData(principal, model);
	        return "myBookings";
	    }
	
	@RequestMapping(value="myQuestions", method=RequestMethod.GET)
    public String myQA(Principal principal, Model model) {
        modelData(principal, model);
        return "viewQuestions";
    }
	
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
	public String welcome(Principal principle) {
		System.out.println("Welcome Mr..................."+principle.getName());
		return "welcome";
	}
	
	
	private void modelData(Principal principal, Model model) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        } 
        // get this by either request.getAttribute("username") as <% javacodes %> or ${username} as javascript codes or "${username}" as jQuery codes in home.jsp.
        model.addAttribute("username", username);
        
    }
	
}
