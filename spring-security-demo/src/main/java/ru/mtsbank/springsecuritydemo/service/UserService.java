package ru.mtsbank.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mtsbank.springsecuritydemo.dto.Signup;
import ru.mtsbank.springsecuritydemo.entity.ERole;
import ru.mtsbank.springsecuritydemo.entity.Person;
import ru.mtsbank.springsecuritydemo.entity.Role;
import ru.mtsbank.springsecuritydemo.repository.PersonRepository;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(@Autowired PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Person signup(Signup signup){
        Person person = new Person();
        person.setUsername(signup.getUsername());
        person.setPassword(passwordEncoder.encode(signup.getPassword()));
        person.setRoles(Set.of(new Role(ERole.ADMIN)));
        return personRepository.save(person);
    }
}
