package com.project.schedule.common;

import com.project.schedule.service.ScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
    private final ScheduleService scheduleService;

    public Schedule(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void saveFileJob() {
        try {
            scheduleService.saveFileJob();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
