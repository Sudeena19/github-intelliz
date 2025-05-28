package com.example.user.job;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ActivateInactiveUsersJob implements Job {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(JobExecutionContext context) {

        List<User> inactiveUsers = userRepository.findByStatus(User.Status.INACTIVE);
        for (User user : inactiveUsers) {
            user.setStatus(User.Status.ACTIVE);
        }
        userRepository.saveAll(inactiveUsers);

        List<User> activeUsers = userRepository.findByStatus(User.Status.ACTIVE);

        System.out.println("✅ Updated " + activeUsers.size() + " users to ACTIVE at " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}

