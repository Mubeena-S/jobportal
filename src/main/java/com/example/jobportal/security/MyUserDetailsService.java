package com.example.jobportal.security;

import com.example.jobportal.model.User;
import com.example.jobportal.model.Recruiter;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RecruiterRepository recruiterRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name()) 
                    .build();
        }

        Optional<Recruiter> recruiterOpt = recruiterRepository.findByEmail(email);
        if (recruiterOpt.isPresent()) {
            Recruiter recruiter = recruiterOpt.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(recruiter.getEmail())
                    .password(recruiter.getPassword())
                    .roles("RECRUITER")
                    .build();
        }

        throw new UsernameNotFoundException("User or Recruiter not found with email: " + email);
    }
}
