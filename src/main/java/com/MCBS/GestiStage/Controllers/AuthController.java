package com.MCBS.GestiStage.Controllers;
import com.MCBS.GestiStage.config.TokenService;
import com.MCBS.GestiStage.dtos.request.AppUserDto;
import com.MCBS.GestiStage.dtos.request.LoginRequest;
import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.AuthResponseDTO;
import com.MCBS.GestiStage.dtos.response.RegisterResponseDTO;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.service.AccountService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;



@Controller
@Api(tags = "Authentication end points", description = "Operations for the Registration and Log in functionality")
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger LOGGER= LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtDecoder jwtDecoder;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, AccountService accountService, JwtDecoder jwtDecoder) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.jwtDecoder = jwtDecoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> requestForToken( @RequestBody LoginRequest loginRequest){
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
                    return ResponseEntity.ok(response);
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
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity(Map.of("error",String.format("grantType <<%s>> not supported ",loginRequest.grantType())),HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody AppUserDto studentDto) {
        if (accountService.getUserByEmail(studentDto.getEmail())!=null)
        {
            return new ResponseEntity(Map.of("message", "Email address is taken!"), HttpStatus.UNAUTHORIZED);
        }
        if (!(studentDto.getPassword().equals(studentDto.getConfirmPassword())))
        {
            return new ResponseEntity(Map.of("message", "Your passwords do no match"), HttpStatus.UNAUTHORIZED);
        }
        accountService.createNewUser(studentDto);
        return new ResponseEntity(Map.of("message","User registered success!"), HttpStatus.CREATED);
    }



}
