package com.example.login_sample.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.login_sample.dto.AccountDto;
import com.example.login_sample.entities.Account;
import com.example.login_sample.services.AccountService;

@Controller
public class AuthorizationController {
	
	private AccountService accountService;
	
	public AuthorizationController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	// Essa função verifica se o usuário tem algum 'papel' ligado a ele, caso haja
	// algum usuário autenticado. A depender do papel é possível redirecioná-lo para
	// uma página específica.
	
	@GetMapping("/default")
	public String defaultAfterLogin() {
		Collection<? extends GrantedAuthority> authorities;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		authorities = authentication.getAuthorities();
		String role = authorities.toArray()[0].toString();
		if(role.equals("ROLE_ADMIN")) {
			return "redirect:/accounts";
		}
		return "redirect:/home";
	}
		
	@GetMapping("/accounts")
	public String accounts(Model model) {
		List<AccountDto> accounts = this.accountService.findAllAccounts();
		model.addAttribute("accounts", accounts);
		return "accounts";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registrationForm(Model model) {
		AccountDto accountDto = new AccountDto();
		model.addAttribute("account", accountDto);
		return "register";
	}
	
	@PostMapping("/register/save")
	public String registration(@Validated @ModelAttribute("account") AccountDto accountDto,
			BindingResult result,
			Model model) {
		Account existingAccount = this.accountService.findUserByCpf(accountDto.getCpf());
		
		if(existingAccount != null 
				&& existingAccount.getCpf() != null 
				&& !existingAccount.getCpf().isEmpty()) {
			result.rejectValue("cpf", "Já existe uma conta para este CPF");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("account", accountDto);
			return "/register";
		}
		
		this.accountService.saveAccount(accountDto);
		return "redirect:/register?success";
	}

}
