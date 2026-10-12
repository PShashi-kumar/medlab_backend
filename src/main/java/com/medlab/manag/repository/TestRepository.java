package com.medlab.manag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medlab.manag.entity.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {
}