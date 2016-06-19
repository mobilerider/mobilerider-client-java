package com.mobilerider;

import com.google.gson.annotations.SerializedName;

public class Channel
{
    @SerializedName("id")
    private String _id;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
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