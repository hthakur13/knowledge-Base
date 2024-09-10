package com.example.bulkupload.controller;

import com.example.bulkupload.entity.Issue;
import com.example.bulkupload.service.IssueService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {
    @Autowired
    private IssueService issueService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        List<Issue> issues = new ArrayList<>();
        int successCount = 0;

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader("error_description", "resolution").withIgnoreHeaderCase().withTrim())) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String errorDescription = csvRecord.get("error_description");
                String resolution = csvRecord.get("resolution");

                Issue issue = new Issue();
                issue.setErrorDescription(errorDescription);
                issue.setResolution(resolution);
                issues.add(issue);
            }

            List<Issue> savedIssues = issueService.saveAllIssues(issues);
            successCount = savedIssues.size();

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }

        return ResponseEntity.ok("Successfully uploaded " + successCount + " out of " + issues.size() + " entries.");
    }
}
