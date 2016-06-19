package com.mobilerider;

import com.google.gson.annotations.SerializedName;

public class Media
{
    @SerializedName("id")
    private String _id;

    public String getId()
    {
        return _id;
    }

    public void setId(String id)
    {
        _id = id;
    }

    @SerializedName("title")
    private String _title;

    public String getTitle()
    {
        return _title;
    }

    public void setTitle(String title)
    {
        _title = title;
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