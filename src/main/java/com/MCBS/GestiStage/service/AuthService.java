package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.config.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

// rafinnement
@Service
public class AuthService {

    private final TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtDecoder jwtDecoder;

    public AuthService(TokenService tokenService, AuthenticationManager authenticationManager, AccountService accountService, JwtDecoder jwtDecoder) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.jwtDecoder = jwtDecoder;
    }
/*
    public AuthResponseDTO authenticate(LoginRequest loginRequest)
    {
        AuthResponseDTO response;
        if(loginRequest.grantType().equals("password")){
            try
            {
                Authentication authentication=authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.email(),loginRequest.password()
                        )
                );
                {
                    response=tokenService.generateJwtToken(authentication.getName(),authentication.getAuthorities(),loginRequest.withRefreshToken());
                    return response;
                }
            }
            catch (AuthenticationException e)
            {
                return new ResponseEntity(Map.of("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
            }

        }
        else if(loginRequest.grantType().equals("refreshToken")){
            String refreshToken=loginRequest.refreshToken();
            if(refreshToken==null) {
                return new ResponseEntity(Map.of("error","RefreshToken Not Present"), HttpStatus.UNAUTHORIZED);
            }
            Jwt decodedJwt = jwtDecoder.decode(refreshToken);
            String email=decodedJwt.getSubject();
            AppUser appUser=accountService.getUserByEmail(email);
            Collection<GrantedAuthority> authorities=appUser.getAppRoles()
                    .stream()
                    .map(role->new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toList());
            response=tokenService.generateJwtToken(appUser.getEmail(),authorities,loginRequest.withRefreshToken());
            return response;
        }
        return new ResponseEntity(Map.of("error",String.format("grantType <<%s>> not supported ",loginRequest.grantType())),HttpStatus.UNAUTHORIZED);

    }

 */
}
