package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTaskTest() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "test_task", "test_task_content"));
        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> resultAllTask = dbService.getAllTasks();

        //Then
        assertEquals(1, resultAllTask.size());
    }

    @Test
    public void getTaskTest() throws TaskNotFoundException {
        //Given
        List<Task> tasks = List.of(new Task(1L, "test_task", "test_task_content"));
        when(taskRepository.findById(tasks.get(0).getId())).thenReturn(Optional.ofNullable(tasks.get(0)));

        //When
        Task task = dbService.getTask(1L);

        //Then
        assertEquals(tasks.get(0), task);
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(1L, "test_task", "test_task_content");
        when(taskRepository.save(task)).thenReturn(task);

        //When
        Task resultTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), resultTask.getId());
        assertEquals(task.getTitle(), resultTask.getTitle());
        assertEquals(task.getContent(), resultTask.getContent());
    }

    @Test
    public void deleteTaskTest() {
        //Given
        Task task = new Task(1L, "test_task", "test_content");
        Long id = task.getId();

        //When
        dbService.deleteById(id);

        //Then
        verify(taskRepository, times(1)).deleteById(id);
    }
}