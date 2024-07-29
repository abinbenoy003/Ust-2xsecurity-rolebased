package com.ust.Ust_Projects.repository;

import com.ust.Ust_Projects.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Project,Integer> {
}
