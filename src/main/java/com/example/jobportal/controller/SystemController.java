package com.example.jobportal.controller;

import com.example.jobportal.dto.SystemStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
public class SystemController {

    private final DataSource dataSource;

    // Check system status
    @GetMapping("/status")
    public SystemStatusDTO getSystemStatus() {
        String dbStatus = "DISCONNECTED";
        try (Connection conn = dataSource.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                dbStatus = "CONNECTED";
            }
        } catch (SQLException e) {
            dbStatus = "DISCONNECTED";
        }

        return SystemStatusDTO.builder()
                .applicationName("Job Portal System")
                .version("v1.0.0")
                .status("UP")
                .serverTime(LocalDateTime.now())
                .databaseStatus(dbStatus)
                .build();
    }
}
