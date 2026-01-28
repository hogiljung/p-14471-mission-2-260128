package org.example;

public class WiseSaying {
    public int id;
    public String content;
    public String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public String getInfo() {
        return this.id + " / " + this.author + " / " + this.content;
    }
}
