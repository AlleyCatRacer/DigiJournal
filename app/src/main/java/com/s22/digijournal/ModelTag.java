package com.s22.digijournal;

public class ModelTag
{
    private String tagName;
    private String color;

    public ModelTag(String name)
    {
        tagName = name;
    }

    public ModelTag(String name, String color)
    {
        tagName = name;
        this.color = color;
    }
}
