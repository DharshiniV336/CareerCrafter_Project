package com.springboot.tasksystem.dto.response;

import com.springboot.tasksystem.enums.Priority;
import com.springboot.tasksystem.enums.Status;

import java.time.LocalDate;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        Status status
) {
}
