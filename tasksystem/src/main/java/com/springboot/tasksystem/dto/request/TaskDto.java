package com.springboot.tasksystem.dto.request;

import com.springboot.tasksystem.enums.Priority;
import com.springboot.tasksystem.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskDto(
        @NotBlank(message = "Title is required")
        @NotNull(message = "Title is required")
        String title,
        @NotBlank(message = "Description is required")
        @NotNull(message = "Description is required")
        String description,
        @NotBlank(message = "Due date is required")
        @NotNull(message = "Due date is required")
        LocalDate dueDate,
        @NotBlank(message = "Priority is required")
        @NotNull(message = "Priority is required")
        Priority priority,
        @NotBlank(message = "Status is required")
        @NotNull(message = "Status is required")
        Status status
) {
}
