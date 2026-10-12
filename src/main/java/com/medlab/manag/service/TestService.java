package com.medlab.manag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medlab.manag.entity.Test;
import com.medlab.manag.repository.TestRepository;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAll() {
        return testRepository.findAll();
    }

    public Test save(Test test) {
        return testRepository.save(test);
    }

    public Test update(Integer id, Test updated) {
        Test test = testRepository.findById(id).orElse(null);
        if (test == null) return null;
        test.setTestName(updated.getTestName());
        test.setDescription(updated.getDescription());
        test.setPrice(updated.getPrice());
        return testRepository.save(test);
    }

    public void delete(Integer id) {
        testRepository.deleteById(id);
    }
}