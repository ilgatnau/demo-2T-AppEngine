package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoTableRepository extends JpaRepository<DemoTable, Long> {
}
