package com.springboot.tasksystem.controller;

import com.springboot.tasksystem.dto.request.TaskDto;
import com.springboot.tasksystem.dto.request.TaskUpdateDto;
import com.springboot.tasksystem.dto.response.TaskResponseDto;
import com.springboot.tasksystem.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    //all task
    @GetMapping("/api/task/all")
    public List<TaskResponseDto> getAllTask(Principal principal){
        return taskService.getAllTask(principal.getName());
    }

    //Retrieve a single task by its ID
    @GetMapping("/api/task/get/{taskId}")
    public TaskResponseDto getTaskById(@PathVariable Long taskId,Principal principal){
        return taskService.getTaskById(taskId, principal.getName());
    }

    //Add new task
    @PostMapping("/api/task/add")
    public TaskResponseDto addTask(@Valid @RequestBody TaskDto taskDto,Principal principal){
        return taskService.addTask(taskDto, principal.getName());
    }

    //update an existing task
    @PutMapping("/api/task/update/{taskId}")
    public TaskResponseDto updateTask(@PathVariable Long taskId,@RequestBody TaskUpdateDto taskUpdateDto,Principal principal){
        return taskService.updateTask(taskId,taskUpdateDto, principal.getName());
    }

    //Delete task
    @DeleteMapping("/api/task/delete/{taskId}")
    public void deleteTask(@PathVariable Long taskId,Principal principal){
        taskService.deleteTask(taskId, principal.getName());
    }

}
