package com.example.bulkupload.service;

import com.example.bulkupload.entity.Issue;
import com.example.bulkupload.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> saveAllIssues(List<Issue> issues) {
        return issueRepository.saveAll(issues);
    }
}
