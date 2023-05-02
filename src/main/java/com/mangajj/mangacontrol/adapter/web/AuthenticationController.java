package com.mangajj.mangacontrol.adapter.web;

import com.mangajj.mangacontrol.adapter.web.dto.LoginDTO;
import com.mangajj.mangacontrol.adapter.web.dto.TokenDto;
import com.mangajj.mangacontrol.application.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginDTO form) {
        UsernamePasswordAuthenticationToken dataLogin = form.converter();

        Authentication authentication = authManager.authenticate(dataLogin);
        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    }
}