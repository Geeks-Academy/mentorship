package com.programmersonly.mentorship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = MentorshipApplication.class)
public class MentorshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(MentorshipApplication.class, args);
    }

}
