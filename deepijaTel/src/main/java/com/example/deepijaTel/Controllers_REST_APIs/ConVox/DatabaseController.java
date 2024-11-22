package com.example.deepijaTel.Controllers_REST_APIs.ConVox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/databases")
public class DatabaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getTables")
    public List<String> getTableNames(@RequestParam String databaseName) {
        String sql = "SHOW TABLES FROM " + databaseName;
        return jdbcTemplate.queryForList(sql, String.class);
    }

    // Existing /getAll endpoint
    @Value("${spring.datasource.url}")
    private String primaryDataSourceUrl;

    @Value("${spring.datasource.convox.url}")
    private String secondaryDataSourceUrl;

    @GetMapping("/getAll")
    public List<String> getDatabases() {
        // Parse the database names from the URLs
        String primaryDatabase = primaryDataSourceUrl.split("/")[3].split("\\?")[0];
        String secondaryDatabase = secondaryDataSourceUrl.split("/")[3].split("\\?")[0];

        return Arrays.asList(primaryDatabase, secondaryDatabase);
    }

    @PostMapping("/database_status/check")
    public List<Map<String, Object>> checkTable(@RequestBody DatabaseRequest request) {
        String sql = "CHECK TABLE " + request.getDbName() + "." + request.getTableName();
        return jdbcTemplate.queryForList(sql);
    }

    @PostMapping("/database_status/repair")
    public List<Map<String, Object>> repairTable(@RequestBody DatabaseRequest request) {
        String sql = "REPAIR TABLE " + request.getDbName() + "." + request.getTableName();
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/database_status/mysqlErrorLog")
    public List<String> getMysqlErrorLog() {
        String filePath = "C:/Users/AshU/VSCodeProjects/DeepijaTelecomReactFrontend/error.log";
        List<String> logLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                logLines.add(line);
            }
        }
        catch (IOException e) {
            logLines.add("tail: cannot open '" + filePath + "' for reading: " + e.getMessage());
        }
        return logLines;
    } @GetMapping("/database_status/applicationErrorLog")
    public List<Map<String, Object>> getApplicationErrorLog() {
        String sql = "SELECT message, subject FROM application_errors"; // Adjust the query as needed
        return jdbcTemplate.queryForList(sql);
    }
}