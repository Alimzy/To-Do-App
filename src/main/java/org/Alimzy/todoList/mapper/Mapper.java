package org.Alimzy.todoList.mapper;

import org.Alimzy.todoList.data.models.Task;
import org.Alimzy.todoList.dtos.TaskRequest;
import org.Alimzy.todoList.dtos.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Task map(TaskRequest request) {
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        return task;
    }

    public TaskResponse map(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getName());
        response.setDescription(task.getDescription());
        return response;
    }

}