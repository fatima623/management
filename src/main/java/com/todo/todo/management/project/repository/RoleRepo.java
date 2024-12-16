package com.todo.todo.management.project.repository;

import com.todo.todo.management.project.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

public interface RoleRepo extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
