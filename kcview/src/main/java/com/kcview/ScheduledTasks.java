package com.kcview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    
    public void scheduleTaskWithFixedRate() {}

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleTaskWithCronExpression() {
    	System.out.println("1 er jour du mois");
    }
    

    @Scheduled(cron = "0 0 15 * * ?")
    public void scheduleTaskWithCronExpression2() {
    	System.out.println("15 eme jour du mois");
    }
}