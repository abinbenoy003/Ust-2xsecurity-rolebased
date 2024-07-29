package com.ust.Ust_Projects.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="project")
public class Project {
    @Id
    private int projectId;
    private String projectName;
    private String projectDescription;
    private String projectLink;
    private String psnumber;
    private String username;
    @Enumerated(value= EnumType.STRING)
    private ProjectStatus projectStatus;

}
