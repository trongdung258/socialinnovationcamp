package com.example.yuu.socialinnovationcamp.Model;

/**
 * Created by Yuu on 8/13/2016.
 */
public class Diary {
    private String content;
    private String dateTime;

    public Diary(String content, String time) {
        this.content = content;
        this.dateTime = time;
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }
}
