package com.leonardozw.securitydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}
}

@RestController
class HelloController {

	@GetMapping("/cookie")
	public String privateCookie(@AuthenticationPrincipal OidcUser principal) {
		return String.format("""
			<h1>Hello Private World!</h1>
			<h3>Logged in as: %s</h3>
			<h3>Email: %s</h3>
			<h3>Authorities: %s</h3>
			<h3>Token: %s</h3>
			""", principal, principal.getEmail(), principal.getAuthorities(), principal.getIdToken().getTokenValue()
		);
	}

	@GetMapping("/jwt")
	public String jwt(@AuthenticationPrincipal Jwt jwt){
		return String.format("""
			<h1>Hello JWT World!</h1>
			<h3>Logged in as: %s</h3>
			<h3>Email: %s</h3>
			<h3>Token: %s</h3>
				""", jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());
	}
}
