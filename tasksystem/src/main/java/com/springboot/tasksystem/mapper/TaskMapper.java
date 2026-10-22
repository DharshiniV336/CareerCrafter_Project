package com.springboot.tasksystem.mapper;

import com.springboot.tasksystem.dto.request.TaskDto;
import com.springboot.tasksystem.dto.response.TaskResponseDto;
import com.springboot.tasksystem.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public static Task mapDtotoEntity(TaskDto taskDto){
        Task task=new Task();
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setDueDate(taskDto.dueDate());
        task.setPriority(taskDto.priority());
        task.setStatus(taskDto.status());
        return task;
    }

    public static TaskResponseDto mapEntitytoDto(Task task){
        return new TaskResponseDto(task.getId(),task.getTitle(),task.getDescription(),task.getDueDate(),task.getPriority(),task.getStatus());
    }
}
