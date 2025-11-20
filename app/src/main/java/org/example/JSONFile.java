package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONFile {
    private BufferedReader reader;
    private StringBuilder JSONString;
    private Path path;
    private JSONTask tasks;

    public JSONFile(String fileName) {
        File f = new File(fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        path = Paths.get(fileName);
        try {
            reader = Files.newBufferedReader(path);
            String readString = reader.readLine();
            if(readString == null) readString = "";
            JSONString = new StringBuilder(readString);
            if(JSONString.isEmpty()) {
                JSONString.insert(0,"[]");
            }
            tasks = new JSONTask(JSONString.toString());
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLastId() {
        Pattern p = Pattern.compile("\"id\": ([0-9]*).*}]");
        Matcher m = p.matcher(JSONString);
        if(m.find()) {
            return Integer.valueOf(m.group(1));

        } else {
            return 0;
        }
    }

    public void update(Consumer<Task> c, int id) {
        try {
            Task t = tasks.find(id);
            c.accept(t);
            int start = tasks.startPosition();
            int end = tasks.endPosition();
            JSONString.replace(start,end,t.toString());
            write();
        } catch (NoSuchElementException e) {
            System.out.println("No task found");
        }
    }
    public void add(Task t) {
        int start = JSONString.length() - 1;
        String toAdd = "";
        if(start >  5) {
            toAdd += ",";
        }
        toAdd += t.toString();
        JSONString.insert(start,toAdd);
        write();
    }
    public void delete(int id) {
        try {
            tasks.find(id);
            int start = tasks.startPosition();
            int end =  tasks.endPosition();
            if(JSONString.charAt(start - 1) == ',') {
                JSONString.delete(start - 1, start);
                start--;
                end--;
            }
            JSONString.delete(start, end);
            write();
        } catch (NoSuchElementException e) {
            System.out.println("Task not found");
        }

    }
    private void write() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(JSONString.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(Predicate<Task> p) {
        System.out.println("Id Body Status Created at Last updated");
        while(true) {
            tasks.advance();
            if(!tasks.hasNext()) break;
            Task t = tasks.next();
            if(p.test(t)) System.out.println(t.print());
        }
    }
}
