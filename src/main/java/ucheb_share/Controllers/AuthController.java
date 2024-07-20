package ucheb_share.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import ucheb_share.Entities.User;
import ucheb_share.Repositories.FlowRepository;
import ucheb_share.Repositories.UserRepository;

@Controller

@RequestMapping("/auth")
@SessionAttributes("user")
public class AuthController {
	
	FlowRepository flowRepo;
	UserRepository userRepo;
	
	
	AuthController(FlowRepository flowRepo, UserRepository userRepo) {
		this.flowRepo = flowRepo;
		this.userRepo = userRepo;
	}
	
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model, @ModelAttribute User user) {
		model.addAttribute("flows", flowRepo.findAll());
		return "registration";
	}
	
	
	@GetMapping("/login")
	public String showLoginForm(Model model, @ModelAttribute User tempUser) {
		return "login";
	}
	
	
	@PostMapping("/registration")
	public String registrateUser(Model model, @Valid @ModelAttribute User user, Errors errors) {
		if (errors.hasErrors())
			return "registration";
		user.setAdmissionYear(2024);
		user.setCourseNum(1);
		model.addAttribute(user);
		userRepo.save(user);
		return "redirect:/userinfo";
	}
	
	
	@PostMapping("/login")
	public String loginUser(Model model, @Valid @ModelAttribute User tempUser) {
		User user = userRepo.findByName(tempUser.getName());
		if (user != null && user.getPassword().equals(user.getPassword())) {
			model.addAttribute(user);
		    return "redirect:/userinfo";
		}
		return "redirect:/auth/login";
	}
	
	
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("user", null);
		return "redirect:/home";
	}
	
	@ModelAttribute("user")
	private User user() {
		return new User();
	}
}
