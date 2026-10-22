package com.springboot.tasksystem.dto.response;

public record CustomerResponseDto(
        Long id,
        String name,
        String city,
        String email
) {
}
