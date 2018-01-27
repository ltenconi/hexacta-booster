package com.hexacta.booster.codegeneration;

/**
 * 
 */
public class TextFile {

    private String text;

    private String name;

    private String path;

    public TextFile() {
    }

    public TextFile(final String text, final String name, final String path) {

        this.text = text;
        this.name = name;
        this.path = path;

    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
