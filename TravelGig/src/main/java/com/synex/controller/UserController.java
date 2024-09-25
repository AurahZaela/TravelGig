package com.synex.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.synex.domain.Role;
import com.synex.domain.User;
import com.synex.service.RoleService;
import com.synex.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@SessionAttributes("users")
public class UserController {
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	RoleService roleService;
	
	
	
//	@GetMapping("/fetchUser")
//	public String fetchUserPage(Principal principal) {
//		return "fetchUser";
//	}

	@GetMapping(value = "/login")
	public String login(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		String message = "";
		if (error != null) {
			message = "Invalid Credentials";
		}
		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
			}
			message = "Logout";
			return "login";
		}
		model.addAttribute("Message", message);
		return "login";

	}

	@GetMapping(value = "/accessDeniedPage")
	public String accessDenied(Principal principal, Model model) {
		String message = principal.getName() + ", Unauthorised access";
		model.addAttribute("Message", message);
		return "accessDeniedPage";

	}

	@GetMapping(value = "/signup")
	public String signup(@RequestParam String userEmail, @RequestParam String userName, @RequestParam String password) {
		return "login";

	}
	
	@GetMapping("/register")
	public String register(User user, Model model) {
		return "signup";
	}
	
	@GetMapping(value = "/user/{username}")
	@ResponseBody
	public String getUserByUsername(@PathVariable String username) {
		return userService.findByUserName(username).getEmail();

	}
	
	@PostMapping("saveUser")
    public String saveUser(@ModelAttribute User user, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job.
		long nextUserId= userService.getNextUserId();
		
		user.setUserId(nextUserId+1);
        if (!br.hasErrors()) {
            Role userRole = roleService.findByRoleId(2);  // USER is role id 2.
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(userRole);
            
            user.setRoles(roleSet);
            userService.save(user);
            model.addAttribute("messageAfterLogInOut", "Thank you for sign up. Please sign in.");
            return "login";
        }
        // else
        model.addAttribute("messageAfterLogInOut", "Error occured during sign up.");
        return "register";  // do not redirect (redirect:register), keep the info user entered and show error messages
    }
	
	@GetMapping("/userProfile")
	public String userProfile(Principal principal) {
		if(principal != null)
			return "userProfile";
		
		return "redirect:NotFound";
	}


//	@Bean
//	public BCryptPasswordEncoder bCryptpeasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
