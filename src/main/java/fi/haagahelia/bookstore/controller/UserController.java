package fi.haagahelia.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.bookstore.domain.SignupForm;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="signup")
	public String addUser(Model model) {
		model.addAttribute("signup", new SignupForm());
		return "signup";
	}

	
	@PostMapping("/saveuser")
	public String save(@Valid @ModelAttribute("signup") SignupForm signupForm, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			
			if(signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String pass = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPass = bc.encode(pass);
				
				User user = new User();
				user.setPasswordHash(hashPass);
				user.setUsername(signupForm.getUsername());
				user.setEmail(signupForm.getEmail());
				user.setRole("USER");
				
				if(userRepository.findByUsername(signupForm.getUsername()) == null && userRepository.findByEmail(signupForm.getEmail()) == null) {
					userRepository.save(user);
				} else if(userRepository.findByUsername(signupForm.getUsername()) != null ) {
					bindingResult.rejectValue("username", "err.username", "Username is already exist");
					return "signup";
				} else if(userRepository.findByUsername(signupForm.getEmail()) != null ) {
					bindingResult.rejectValue("email", "err.email", "Email is already exist");
					return "signup";
				}
				
			} else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
			
		} else {
			return "signup";
		}
		
	return "redirect:/login";
	}
}
