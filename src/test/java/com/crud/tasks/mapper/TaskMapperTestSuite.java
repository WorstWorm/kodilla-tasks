package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTestSuite {
    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_title", "test_content");

        //When
        Task resultTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), resultTask.getId());
        assertEquals(taskDto.getTitle(), resultTask.getTitle());
        assertEquals(taskDto.getContent(), resultTask.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "test_title", "test_content");

        //When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), resultTaskDto.getId());
        assertEquals(task.getTitle(), resultTaskDto.getTitle());
        assertEquals(task.getContent(), resultTaskDto.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "test_title1", "test_content1"));
        tasks.add(new Task(2L, "test_title2", "test_content2"));
        tasks.add(new Task(3L, "test_title3", "test_content3"));
        //When
        List<TaskDto> resultTaskDtoList = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.size(), resultTaskDtoList.size());
        assertEquals(tasks.get(0).getId(), resultTaskDtoList.get(0).getId());
        assertEquals(tasks.get(1).getTitle(), resultTaskDtoList.get(1).getTitle());
        assertEquals(tasks.get(2).getContent(), resultTaskDtoList.get(2).getContent());
    }
}
