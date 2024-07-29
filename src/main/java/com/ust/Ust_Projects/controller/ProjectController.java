package com.ust.Ust_Projects.controller;


import com.ust.Ust_Projects.model.Project;
import com.ust.Ust_Projects.model.ProjectStatus;
import com.ust.Ust_Projects.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/create")
    public String createPost(@RequestBody Project project, Principal principal){
        project.setProjectStatus(ProjectStatus.INPROGRESS);
        project.setUsername(principal.getName());
        project.setPsnumber(project.getPsnumber());
        postRepository.save(project);
        return principal.getName()+" your post is published successfully.";
    }

    @GetMapping("/approveAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String approveAll(){
        postRepository.findAll().stream().filter(post-> post.getProjectStatus().equals(ProjectStatus.INPROGRESS))
                .forEach(post->{
                    post.setProjectStatus(ProjectStatus.COMPLETED);
                    postRepository.save(post);
                });
        return "Approved all posts!";
    }

    @GetMapping("/removeProject/{projectId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String removeProject(@PathVariable int projectId){
        Project project= postRepository.findById(projectId).get();
        project.setProjectStatus(ProjectStatus.REJECTED);
        postRepository.save(project);
        return "Project rejected!";
    }

    @GetMapping("/updateProject/{projectId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String updateProject(@PathVariable int projectId){
        Project project= postRepository.findById(projectId).get();
        project.setProjectStatus(ProjectStatus.COMPLETED);
        postRepository.save(project);
        return "Project updated!";
    }

    @GetMapping("/viewAll")
    public List<Project> viewAll(){
        return postRepository.findAll().stream()
                .filter(post-> post.getProjectStatus().equals(ProjectStatus.INPROGRESS))
                .collect(Collectors.toList());
    }
}
