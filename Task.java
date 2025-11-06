import java.time.LocalDateTime;
public class Task {
    private final String body;
    private final int id;
    private TaskStatus status;
    private String createdAt;
    private String updatedAt;
    
    public Task(String s, int id) {
        body = s;
        this.id = id;
        status = TaskStatus.NOT_DONE;
        createdAt = LocalDateTime.now().toString();
        updatedAt = "-";
    }

    public String toJSON() {
        String template = """
            {
                "id" : %d,
                "body" : %s,
                "status": %s,
                "created at": %s,
                "last updated": %s
            }
            """
        return String.format(template,id,body,createdAt,updatedAt);
    }
}
