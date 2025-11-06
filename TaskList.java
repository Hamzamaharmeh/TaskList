import java.util.ArrayList;
public class TaskList  {
    private ArrayList<Task> list;
    private int nextId;

    public void add(String s) {
        list.add(new Task(s,nextId));
        nextId++;
    }

    public void toJSON() {
        
    }
}
