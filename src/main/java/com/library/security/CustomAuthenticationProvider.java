package com.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.library.service.MyUserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
    MyUserDetailsService myuserDetailsService;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        if (username.equals("NONE_PROVIDED")) {
            throw new BadCredentialsException("invalid login details");
        }
        // get user details using Spring security user details service
        UserDetails user = null;
        try {
            user = myuserDetailsService.loadUserByUsername(username);
            
            if(!encoder.matches((CharSequence) authentication.getCredentials(), user.getPassword())) {
            	throw new UsernameNotFoundException("");
            }

        } catch (UsernameNotFoundException exception) {
            throw new BadCredentialsException("invalid login details");
        }
        return createSuccessfulAuthentication(authentication, user);
	}

	private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
	}

	@Override
    public boolean supports(Class < ? > authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
