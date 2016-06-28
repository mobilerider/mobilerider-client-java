package com.mobilerider;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Channel implements Serializable
{
    private static final long serialVersionUID = -5439175926991676724L;

    @SerializedName("id")
    private String _id;

    public String getId() {
        return _id;
    }

    private void setId(String id) {
        _id = id;
    }

    @SerializedName("name")
    private String _name;

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    @SerializedName("description")
    private String _description;

    public String getDescription()
    {
        return _description;
    }

    public void setDescription(String description)
    {
        _description = description;
    }
}