package com.example.myschedular.schedule.job;

import com.example.myschedular.schedule.entity.ErrorLog;
import com.example.myschedular.schedule.repository.ErrorLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ErrorLogJob implements Job {
    @Autowired
    private ErrorLogRepository errorLogRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            LocalDateTime lastCheckedTime = (LocalDateTime) context.getJobDetail().getJobDataMap().getOrDefault("lastCheckedTime", LocalDateTime.now().minusMinutes(5));
            List<ErrorLog> newEntries = errorLogRepository.findByTimestampAfter(lastCheckedTime);

            if (!newEntries.isEmpty()) {
                sendToMLSystem(newEntries);
                context.getJobDetail().getJobDataMap().put("lastCheckedTime", LocalDateTime.now());
            } else {
                log.debug("No new entries found in errorDB.");
            }
        } catch (Exception e) {
            log.error("Error occurred while executing job", e);
        }
    }

    private void sendToMLSystem(List<ErrorLog> newEntries) {
        // Simulating sending data to ML system
        for (ErrorLog entry : newEntries) {

            log.info("Sending entry to ML system: ID = {}, Message = {}, Timestamp = {}", entry.getId(), entry.getMessage(), entry.getTimestamp());
        }
    }
}
