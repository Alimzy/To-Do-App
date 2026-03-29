package org.Alimzy.todoList.services;

import org.Alimzy.todoList.data.models.Task;
import org.Alimzy.todoList.data.repositories.TaskRepository;
import org.Alimzy.todoList.dtos.TaskRequest;
import org.Alimzy.todoList.dtos.TaskResponse;
import org.Alimzy.todoList.exception.TaskAlreadyExistException;
import org.Alimzy.todoList.exception.TaskCannotBeFoundException;
import org.Alimzy.todoList.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServices{
    private final TaskRepository taskRepository;
    private final Mapper taskMapper;

    public TaskServices(TaskRepository taskRepository, Mapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    public TaskResponse addTask(TaskRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if (taskRepository.existsByName(request.getName())) {
            throw new TaskAlreadyExistException("Task with title '" + request.getName() + "' already exists");
        }
        Task task = taskMapper.map(request);
        Task savedTask = taskRepository.save(task);
        return taskMapper.map(savedTask);
    }

    public TaskResponse getTaskById(String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new TaskCannotBeFoundException("Task cannot be found");
        }
            Task task = optionalTask.get();




        return taskMapper.map(task);
    }

    public TaskResponse UpdateTask(String id,TaskRequest request){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new TaskCannotBeFoundException("Task cannot be found");
        }
        Task task = optionalTask.get();

        if(request.getName() == null || request.getName().isBlank()){
            throw new IllegalArgumentException("Task name cannot be empty");
        }

        if (!task.getName().equals(request.getName()) && taskRepository.existsByName(request.getName())) {
            throw new TaskAlreadyExistException("Task with Name '" + request.getName() + "' already exists");
        }

        task.setName(request.getName());
        task.setDescription(request.getDescription());
        return taskMapper.map(taskRepository.save(task));
    }


    public void deleteTask(String id){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskCannotBeFoundException("Task with id '" + id + "' not found");
        }

        taskRepository.deleteById(id);
    }

    public List<TaskResponse> getAllTasks() {

        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks found");
        }

        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : tasks) {

            TaskResponse response = taskMapper.map(task);

            responses.add(response);
        }
        return responses;
    }


}
