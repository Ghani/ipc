package com.toptechsol.ipc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
@EnableScheduling
public class ScheduleNotification {
	
	@Autowired
	private EmailHtmlSender emailHtmlSender;
	
    @Autowired
    private ApplicationContext applicationContext;
    
	@Scheduled(fixedDelay = 5000)
	public void fixedDelayTask() {
		System.out.println(new Date() + " This runs in a fixed delay");
	}

	@Scheduled(fixedRate = 6000)
	public void fixedRateTask() {
		System.out.println(new Date() + " This runs in a fixed rate");
	}

	@Scheduled(fixedRate = 7000, initialDelay = 2000)
	public void fixedRateWithInitialDelayTask() {
		//emailHtmlSender.send("lamine2002@hotmail.com", "subject", "email/email", new Context());
		System.out.println(new Date() + " This runs in a fixed delay with a initial delay");
	}

	@Scheduled(cron = "10 * * * * *")
	public void cronTask() {
		System.out.println(new Date() + " This runs in a cron schedule");
	}
	
//* "0 0 * * * *" = the top of every hour of every day.
//* "*/10 * * * * *" = every ten seconds.
//* "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//* "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
//* "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//* "0 0 0 25 12 ?" = every Christmas Day at midnight
}	