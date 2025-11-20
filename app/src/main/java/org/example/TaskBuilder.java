package org.example;

public class TaskBuilder {
    private Task task;
    TaskBuilder(int id) {
        task = new Task(id);
    }

    public Task build() {
        return task;
    }
    public TaskBuilder setBody(String body) {
        task.setBody(body);
        return this;
    }
    public TaskBuilder setStatus(TaskStatus status) {
        task.setStatus(status);
        return this;
    }
    public TaskBuilder setCreationDate(String date) {
        task.setCreatedAt(date);
        return this;
    }
    public TaskBuilder setLastUpdated(String date) {
        task.setUpdatedAt(date);
        return this;
    }
}
