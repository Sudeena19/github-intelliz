package com.example.user.config;

import com.example.user.job.ActivateInactiveUsersJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail userActivationJobDetail() {
        return JobBuilder.newJob(ActivateInactiveUsersJob.class)
                .withIdentity("activateInactiveUsersJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger userActivationTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(userActivationJobDetail())
                .withIdentity("userActivationTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever())
                .startNow()
                .build();
    }
}
