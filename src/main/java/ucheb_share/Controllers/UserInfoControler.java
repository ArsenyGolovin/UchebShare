package ucheb_share.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ucheb_share.Entities.User;

@Controller
@SessionAttributes("user")
@RequestMapping("/userinfo")
public class UserInfoControler {
	
	@GetMapping
	public String showUserInfo(Model model, @ModelAttribute User user) {
		return "userinfo";
	}
}
