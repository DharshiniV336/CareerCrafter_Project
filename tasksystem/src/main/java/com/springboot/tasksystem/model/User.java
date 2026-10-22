package com.springboot.tasksystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_at")
    private LocalDate createdAt;

    @PrePersist
    public void init(){
        isActive=true;
        createdAt=LocalDate.now();
    }
}
