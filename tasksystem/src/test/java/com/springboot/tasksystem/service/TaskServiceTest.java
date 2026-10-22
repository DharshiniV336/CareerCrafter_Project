package com.springboot.tasksystem.service;

import com.springboot.tasksystem.dto.request.TaskDto;
import com.springboot.tasksystem.dto.response.TaskResponseDto;
import com.springboot.tasksystem.enums.Priority;
import com.springboot.tasksystem.enums.Status;
import com.springboot.tasksystem.model.Customer;
import com.springboot.tasksystem.model.Task;
import com.springboot.tasksystem.repository.CustomerRepository;
import com.springboot.tasksystem.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void addTaskTest(){
        String customerUsername="xyz";
        TaskDto taskDto=new TaskDto("title","description", LocalDate.parse("2026-11-30"), Priority.LOW, Status.IN_PROGRESS);
        Customer customer=new Customer();

        when(customerRepository.findByUserUsername(customerUsername)).thenReturn(customer);
        when(taskRepository.save(any(Task.class))).thenReturn(any(Task.class));

        taskService.addTask(taskDto,customerUsername);

        verify(taskRepository,times(1)).save(any(Task.class));
        verify(customerRepository,times(1)).findByUserUsername(customerUsername);

        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(taskRepository);
    }
    @Test
    public void getAllTaskTest() {
        String customerUsername = "Sam";
        Task task=new Task();
        Customer customer=new Customer();
        TaskResponseDto responseDto1=new TaskResponseDto(1L,"title1","description1",LocalDate.parse("2026-11-30"), Priority.LOW, Status.IN_PROGRESS);
        TaskResponseDto responseDto2=new TaskResponseDto(2L,"title2","description2",LocalDate.parse("2026-12-11"), Priority.MEDIUM, Status.IN_PROGRESS);


        List<TaskResponseDto> taskList = List.of(responseDto1,responseDto2);
        when(customerRepository.findByUserUsername(customerUsername)).thenReturn(customer);
        when(taskRepository.getAllTask(customerUsername)).thenReturn(taskList);

        List<TaskResponseDto> result = taskService.getAllTask(customerUsername);
        assertEquals(2, result.size());
        assertEquals(taskList, result);

        verify(customerRepository, times(1)).findByUserUsername(customerUsername);
        verify(taskRepository, times(1)).getAllTask(customerUsername);

        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void deleteTaskTest() {
        Long taskId = 1L;
        String customerUsername = "Sam";
        Task task=new Task();
        Customer customer=new Customer();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(customerRepository.findByUserUsername(customerUsername)).thenReturn(customer);


        taskService.deleteTask(taskId, customerUsername);

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);

        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(taskRepository);
    }
}
