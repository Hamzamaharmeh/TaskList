package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TaskList  {

    private final JSONFile file;
    public TaskList() {
        file = new JSONFile("/home/hamza/Documents/list.JSON");
    }

    public void add(String text) {
        int id = file.getLastId() + 1;
        Task t = new Task(id,text);
        file.add(t);
    }

    public void update(int id, String newBody) {
        file.update((t -> {
            t.setBody(newBody);
            t.update();}),id);
    }


    public void delete(int id) {
        file.delete(id);
    }
    public void markInProgress(int id) {
        file.update((t -> t.setStatus(TaskStatus.IN_PROGRESS)), id);
    }
    public void markDone(int id) {
        file.update((t -> t.setStatus(TaskStatus.DONE)), id);
    }
    public void list(String status) {
       if(status.equals("")) {
           file.print((t -> true));
       }  else if(status.equals("done")) {
           file.print((t -> t.getStatus() == TaskStatus.DONE));
       } else if(status.equals("todo")) {
           file.print((t -> t.getStatus() == TaskStatus.TODO));
       } else if(status.equals("in-progress")) {
           file.print((t -> t.getStatus() == TaskStatus.IN_PROGRESS));
       }
    }
}
