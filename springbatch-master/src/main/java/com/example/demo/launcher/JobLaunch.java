package com.example.demo.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

public class JobLaunch implements JobLauncher {
    @Override
    public JobExecution run(Job job, JobParameters jobParameters) {
        return null;
    }
}
