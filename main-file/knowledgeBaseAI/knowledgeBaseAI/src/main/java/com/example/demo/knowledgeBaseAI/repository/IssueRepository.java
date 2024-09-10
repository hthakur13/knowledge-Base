package com.example.demo.knowledgeBaseAI.repository;

import com.example.demo.knowledgeBaseAI.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}
