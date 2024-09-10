package com.example.python.python.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class KnowledgeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Pbl_no;
    private String IssueDescription;
    private String WorkedBy;
    private String CreatedBy;
    private String Status;
    private String Complexity;

}
