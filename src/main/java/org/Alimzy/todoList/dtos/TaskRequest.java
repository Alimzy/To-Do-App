package org.Alimzy.todoList.dtos;

import lombok.Data;

@Data
public class TaskRequest {
    private String name;
    private String description;
}
