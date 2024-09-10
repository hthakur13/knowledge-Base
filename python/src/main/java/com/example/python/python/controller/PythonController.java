package com.example.python.python.controller;

import com.example.python.python.model.ExpertIssue;
import com.example.python.python.model.KnowledgeBase;
import com.example.python.python.repository.ExpertIssueRepository;
import com.example.python.python.repository.KnowledgeBaseRepository;
import com.example.python.python.service.KnowlegeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PythonController {
    @Autowired
    private ExpertIssueRepository expertIssueRepository;
    @Autowired
    private KnowlegeBaseService knowlegeBaseService;
    @GetMapping("/hi")
    public String callPythonScript() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "scripts/HelloWorld.py");
            pb.redirectErrorStream(true);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }

    @PostMapping("/knowledgeBase")
    public KnowledgeBase createEmployee(@RequestBody KnowledgeBase knowledgeBase) {
        return knowlegeBaseService.createKnowledgeBase(knowledgeBase);
    }

    @GetMapping("/knowledgeBase")
    public List<KnowledgeBase> getAllKnowledgeBase() {
        return knowlegeBaseService.getAllKnowledgeBase();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgeBase> getIssuesById(@PathVariable Long id) {
        KnowledgeBase knowledgeBase = knowlegeBaseService.getIssuesById(id).orElseThrow(() -> new RuntimeException("Issues not found"));
        return ResponseEntity.ok(knowledgeBase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KnowledgeBase> updateIssue(@PathVariable Long id, @RequestBody KnowledgeBase knowledgeBaseDetails) {
        KnowledgeBase updatedKnowledgeBase = knowlegeBaseService.updateIssue(id, knowledgeBaseDetails);
        return ResponseEntity.ok(updatedKnowledgeBase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        knowlegeBaseService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addIssue")
    public ExpertIssue addIssue(@RequestBody ExpertIssue expertIssue) {
        return expertIssueRepository.save(expertIssue);
    }

}
