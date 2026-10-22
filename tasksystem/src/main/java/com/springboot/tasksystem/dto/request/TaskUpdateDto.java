package com.springboot.tasksystem.dto.request;

import com.springboot.tasksystem.enums.Priority;
import com.springboot.tasksystem.enums.Status;

import java.time.LocalDate;

public record TaskUpdateDto(
        LocalDate dueDate,
        Priority priority,
        Status status
) {
}
