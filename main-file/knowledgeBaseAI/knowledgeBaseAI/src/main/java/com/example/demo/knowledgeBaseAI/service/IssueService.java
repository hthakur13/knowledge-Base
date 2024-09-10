package com.example.demo.knowledgeBaseAI.service;

import com.example.demo.knowledgeBaseAI.entity.Issue;
import com.example.demo.knowledgeBaseAI.repository.IssueRepository;
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
