package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private String body;
    private final int id;
    private TaskStatus status;
    private String createdAt;
    private String updatedAt;
    
    public Task(int id, String s) {
        this(s,id,TaskStatus.TODO,LocalDate.now().toString(),"-");
    }

    public Task(int id) {
        this(id,"");
    }
    public Task(String body, int id, TaskStatus status) {
        this(body,id,status,LocalDate.now().toString(),"-");
    }
    public Task(String body,int id,TaskStatus status, String createdAt, String updatedAt) {
       this.body = body;
       this.id = id;
       this.status = status;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
    }
    public String toString() {
        String template = "{\"id\": %d,\"body\": %s,\"status\": %s,\"created at\": %s,\"last updated\": %s}";
        return String.format(template,id,body,status,createdAt, updatedAt);
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public int getId() {
        return id;
    }
   public void setBody(String newBodY) {
        body = newBodY;
   }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Task)) return false;

        Task t = (Task) o;
        return this.body.equals(t.body) && this.id == t.id &&
                this.status == t.status && this.createdAt.equals(t.createdAt) &&
                this.updatedAt.equals(t.updatedAt);
    }

    public static Task getTask(String s) {
        Pattern p = Pattern.compile("\".*?\": (.*?)[,|$]");
        Matcher m = p.matcher(s);
        m.find();
        Task t = new Task(Integer.valueOf(m.group(1)));
        m.find();
        t.setBody(m.group(1));
        m.find();
        t.setStatus(TaskStatus.valueOf(m.group(1)));
        m.find();
        t.setCreatedAt(m.group(1));
        m.find();
        if(m.hitEnd()) return t;
        t.setUpdatedAt(m.group(1));
        return t;
    }
    public String print() {
        String formatted = "%d %s %s %s %s";
        return String.format(formatted, id, body, status, createdAt, updatedAt);
    }

    public void update() {
        this.updatedAt = LocalDate.now().toString();
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

