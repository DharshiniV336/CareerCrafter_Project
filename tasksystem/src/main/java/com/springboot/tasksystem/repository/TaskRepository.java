package com.springboot.tasksystem.repository;

import com.springboot.tasksystem.dto.response.TaskResponseDto;
import com.springboot.tasksystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("""
select new com.springboot.tasksystem.dto.response.TaskResponseDto(t.id,t.title,t.description,t.dueDate,t.priority,t.status) from Task t
join t.customer c
join c.user u
where u.username=?1
""")
    List<TaskResponseDto> getAllTask(String customerUsername);
}
