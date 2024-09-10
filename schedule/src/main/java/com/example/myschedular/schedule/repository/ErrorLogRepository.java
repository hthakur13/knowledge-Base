package com.example.myschedular.schedule.repository;

import com.example.myschedular.schedule.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
    List<ErrorLog> findByTimestampAfter(LocalDateTime timestamp);

}
