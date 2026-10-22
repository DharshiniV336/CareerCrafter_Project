package com.springboot.tasksystem.service;

import com.springboot.tasksystem.dto.request.TaskDto;
import com.springboot.tasksystem.dto.request.TaskUpdateDto;
import com.springboot.tasksystem.dto.response.TaskResponseDto;
import com.springboot.tasksystem.exception.InvalidCallException;
import com.springboot.tasksystem.exception.ResourceNotFoundException;
import com.springboot.tasksystem.mapper.TaskMapper;
import com.springboot.tasksystem.model.Customer;
import com.springboot.tasksystem.model.Task;
import com.springboot.tasksystem.repository.CustomerRepository;
import com.springboot.tasksystem.repository.TaskRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CustomerRepository customerRepository;


    public TaskResponseDto addTask(@Valid TaskDto taskDto, String customerUsername) {
        Customer customer=customerRepository.findByUserUsername(customerUsername);
        Task task= TaskMapper.mapDtotoEntity(taskDto);
        task.setCustomer(customer);
        return TaskMapper.mapEntitytoDto(taskRepository.save(task));
    }

    public TaskResponseDto getTaskById(Long taskId, String customerUsername) {
        Task task=taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Invalid task ID"));
        if(!task.getCustomer().getUser().getUsername().equals(customerUsername) || !task.getCustomer().getUser().getIsActive()){
            throw new InvalidCallException("This user unable to perform this");
        }
        return TaskMapper.mapEntitytoDto(task);
    }

    public List<TaskResponseDto> getAllTask(String customerUsername) {
        Customer customer=customerRepository.findByUserUsername(customerUsername);
        if(!customer.getUser().getIsActive()){
            throw new InvalidCallException("This user unable to perform this");
        }
        return taskRepository.getAllTask(customerUsername);
    }

    public TaskResponseDto updateTask(Long taskId, TaskUpdateDto taskUpdateDto, String customerUsername) {
        Task task=taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Invalid taskID"));
        if(!task.getCustomer().getUser().getUsername().equals(customerUsername) || !task.getCustomer().getUser().getIsActive()){
            throw new InvalidCallException("This user unable to perform this");
        }
        boolean flag=false;
        if (taskUpdateDto.dueDate() != null) {
            task.setDueDate(taskUpdateDto.dueDate());
            flag=true;
        }
        if (taskUpdateDto.priority() != null) {
            task.setPriority(taskUpdateDto.priority());
            flag=true;
        }
        if (taskUpdateDto.status() != null) {
            task.setStatus(taskUpdateDto.status());
            flag=true;
        }
        if(flag==false){
            throw new InvalidCallException("Update operation terminated");
        }
        return TaskMapper.mapEntitytoDto(taskRepository.save(task));
    }

    public void deleteTask(Long taskId, String customerUsername) {
        Task task=taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Invalid taskID"));
        Customer customer=customerRepository.findByUserUsername(customerUsername);
        if(!task.getCustomer().getUser().getUsername().equals(customerUsername) || !task.getCustomer().getUser().getIsActive()){
            throw new InvalidCallException("This user unable to perform this");
        }
        taskRepository.deleteById(taskId);
    }
}
