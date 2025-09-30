package com.example.jobportal.util;

import com.example.jobportal.model.Recruiter;
import com.example.jobportal.repository.RecruiterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordEncoderRunner implements CommandLineRunner {

    private final RecruiterRepository recruiterRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PasswordEncoderRunner(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Recruiter> recruiters = recruiterRepository.findAll();

        for (Recruiter recruiter : recruiters) {
            String rawPassword = recruiter.getPassword();
            if (rawPassword != null && !rawPassword.startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                recruiter.setPassword(encodedPassword);
                recruiterRepository.save(recruiter);
                System.out.println("Password encoded for: " + recruiter.getEmail());
            }
        }

        System.out.println("All recruiter passwords updated successfully!");
    }
}
