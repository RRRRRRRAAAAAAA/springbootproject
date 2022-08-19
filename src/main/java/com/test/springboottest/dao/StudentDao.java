package com.test.springboottest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.springboottest.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

}
