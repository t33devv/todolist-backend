package com.t33dev.todolist.controller;

import com.t33dev.todolist.model.Task;
import com.t33dev.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public String addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return "Added task";
    }

    @GetMapping("/getAll")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/update/{id}")
    public void updateAccount(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllTasks() {
        for (Task task : taskService.getAllTasks()) {
            taskService.deleteTask(task.getId());
        }
    }
}
