package com.example.python.python.service;

import com.example.python.python.model.KnowledgeBase;
import com.example.python.python.repository.KnowledgeBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowlegeBaseService {
    @Autowired
    private KnowledgeBaseRepository knowledgeBaseRepository;

    public KnowledgeBase createKnowledgeBase(KnowledgeBase knowledgeBase){
        return knowledgeBaseRepository.save(knowledgeBase);
    }

    public List<KnowledgeBase> getAllKnowledgeBase() {
        return knowledgeBaseRepository.findAll();
    }

    public Optional<KnowledgeBase> getIssuesById(Long id) {
        return knowledgeBaseRepository.findById(id);
    }

    public KnowledgeBase updateIssue(Long id, KnowledgeBase knowledgeBaseDetails) {
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.findById(id).orElseThrow(() -> new RuntimeException("Issue not found"));
        knowledgeBase.setIssueDescription(knowledgeBaseDetails.getIssueDescription());
        knowledgeBase.setComplexity(knowledgeBaseDetails.getComplexity());
        knowledgeBase.setStatus(knowledgeBaseDetails.getStatus());
        knowledgeBase.setWorkedBy(knowledgeBaseDetails.getWorkedBy());
        knowledgeBase.setCreatedBy(knowledgeBaseDetails.getCreatedBy());
        return knowledgeBaseRepository.save(knowledgeBase);
    }

    public void deleteIssue(Long id) {
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.findById(id).orElseThrow(() -> new RuntimeException("Issue not found"));
        knowledgeBaseRepository.delete(knowledgeBase);
    }

}
