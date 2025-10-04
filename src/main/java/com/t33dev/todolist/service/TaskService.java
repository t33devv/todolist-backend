package com.t33dev.todolist.service;

import com.t33dev.todolist.model.Task;
import com.t33dev.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        boolean exists = taskRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("Task id " + id + " does not exist");
        }
        taskRepository.deleteById(id);
    }

    public void updateTask(Long id, Task update) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Task id " + id + " does not exist"
                        ));
        task.setTitle(update.getTitle());
        task.setDescription(update.getDescription());
        task.setDueDate(update.getDueDate());
        taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Task id " + id + " does not exist"
                        )
                );
    }
}
