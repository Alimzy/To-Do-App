package org.Alimzy.todoList.exception;

public class TaskCannotBeFoundException extends RuntimeException {
    public TaskCannotBeFoundException(String message){
        super(message);
    }

}
