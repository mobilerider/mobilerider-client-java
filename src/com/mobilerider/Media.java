package com.mobilerider;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Media implements Serializable
{
    private static final long serialVersionUID = 6770928527452675692L;

    @SerializedName("id")
    private String _id;

    public String getId()
    {
        return _id;
    }

    private void setId(String id)
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

    @SerializedName("views")
    private int _views;

    public int getViews()
    {
        return _views;
    }

    private void setViews(int views)
    {
        if (views < 0)
        {
            throw new IllegalArgumentException("views");
        }

        _views = views;
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


    @SerializedName("thumbnails")
    private Map<String, String> _thumbnailUris;

    public Map<String, String> getThumbnailUris()
    {
        return _thumbnailUris;
    }

    @SerializedName("files")
    private Map<String, String> _mediaUris;

    public Map<String, String> getMediaUris()
    {
        return _mediaUris;
    }

    public Media()
    {
        _thumbnailUris = new HashMap<String, String>();
        _mediaUris = new HashMap<String, String>();
    }
}