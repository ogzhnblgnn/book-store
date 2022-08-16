package com.example.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Role;
import com.example.backend.services.Abstract.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        var response = roleService.getAllRoles();
        return ResponseEntity
        .status(response.getStatusCode())
        .body(response);
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        var response = roleService.createRole(role);
        return ResponseEntity
        .status(response.getStatusCode())
        .body(response);
    }
}
