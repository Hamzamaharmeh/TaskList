package org.example;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONTask {

    private Pattern taskGetter;
    private Matcher taskGetterMatcher;
    private String JSONString;
    private int startPos;
    private int endPos;
    public JSONTask(String JSONString ) {
        this.JSONString = JSONString;
        taskGetter = Pattern.compile("(\"id\": .*?)}");
        taskGetterMatcher = taskGetter.matcher(JSONString);
    }
    public Task next() {
        return Task.getTask(taskGetterMatcher.group(1));
    }

    public boolean hasNext() {
        return !taskGetterMatcher.hitEnd();
    }
    public void advance() {
        taskGetterMatcher.find();
    }

    public Task find(int id) throws NoSuchElementException {
        Pattern p = Pattern.compile("\\{(\"id\": "+ id +",.*?)}");
        Matcher m = p.matcher(JSONString);

        if(m.find()) {
            startPos = m.start();
            endPos = m.end();
            return Task.getTask(m.group(1));
        }
        else throw  new NoSuchElementException();
    }
    public int startPosition() {
        return startPos;
    }
    public int endPosition() {
        return endPos;
    }

}
