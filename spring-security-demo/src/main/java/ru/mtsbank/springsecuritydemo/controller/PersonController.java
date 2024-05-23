package ru.mtsbank.springsecuritydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mtsbank.springsecuritydemo.dto.JwtResponse;
import ru.mtsbank.springsecuritydemo.dto.Signin;
import ru.mtsbank.springsecuritydemo.dto.Signup;
import ru.mtsbank.springsecuritydemo.entity.Person;
import ru.mtsbank.springsecuritydemo.security.jwt.JWTUtilty;
import ru.mtsbank.springsecuritydemo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api")
public class PersonController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtilty jwtUtilty;

    @GetMapping("one")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getPerson(){
        return ResponseEntity.ok("Authorized");
    }

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody Signup signup){
        //register
        Person person = userService.signup(signup);
        return ResponseEntity.ok(String.valueOf(person.getId()));
    }

    @PostMapping("signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody Signin signin){
        Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getUsername(), signin.getPassword()));
        //
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        String jwtToken = jwtUtilty.generateJwtToken(authenticated);

        Person userDetails = (Person) authenticated.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwtToken,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }
}
