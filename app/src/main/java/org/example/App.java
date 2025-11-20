package org.example;


public class App {

    private static TaskList taskList;
    public static void main(String[] args) {
        taskList = new TaskList();
        String command = args[0];
        command(command,args);
    }
    private static void command(String command,String []args) {
        if(command.equals("add")) {
            taskList.add(args[1]);
        } else if(command.equals("update")) {
             taskList.update(Integer.valueOf(args[1]), args[2]);
        } else if(command.equals("delete")) {
            taskList.delete(Integer.valueOf(args[1]));
        } else if(command.equals("mark-in-progress")) {
            taskList.markInProgress(Integer.valueOf(args[1]));
        } else if(command.equals("mark-done")) {
            taskList.markDone(Integer.valueOf(args[1]));
        } else if(command.equals("list")) {
            if(args.length == 1) {
                taskList.list("");
            } else {
                taskList.list(args[1]);
            }
        }
    }

}
