package org.Alimzy.todoList.data.repositories;

import org.Alimzy.todoList.data.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository <Task, String>{
    boolean existsByName(String Name);
}
