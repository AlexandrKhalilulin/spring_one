package org.example.service;

import org.example.dao.TaskDAO;
import org.example.controller.domain.Status;
import org.example.controller.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class TaskService {

    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getAll(int offset, int limit) {
        return taskDAO.getAll(offset, limit);
    }

    public int getAllCount() {
        return taskDAO.getAllCount();
    }

    @Transactional
    public Task edit(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Not found task with id: " + id);
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    public Task create(Status status, String description) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public void delete(int id) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Not found task with id: " + id);
        }
        taskDAO.delete(task);
    }

}
