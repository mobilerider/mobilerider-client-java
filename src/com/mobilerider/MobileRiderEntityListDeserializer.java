package com.mobilerider;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.List;

class MobileRiderEntityListDeserializer<TEntity> implements JsonDeserializer<Page<TEntity>>
{
    @Override
    public Page<TEntity> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
    {
        // Get the "meta" element from the parsed JSON
        JsonElement metaElement = je.getAsJsonObject().get("meta");

        // Get page, pageSize and total.
        Meta meta = (new Gson().fromJson(metaElement, Meta.class));

        // Get the "objects" element from the parsed JSON
        JsonElement objectsElement = je.getAsJsonObject().get("objects");

        // Deserialize it.
        // We use a new instance of Gson to avoid infinite recursion to this deserializer.
        Class<List<TEntity>> listOfChannelClass = (Class) List.class;

        List<TEntity> entities = (new Gson().fromJson(objectsElement, listOfChannelClass));

        Page<TEntity> page = new Page<TEntity>(entities, meta.getPageSize(), meta.getPage() - 1, meta.getTotal());

        return page;
    }

    private class Meta
    {
        @SerializedName("page")
        private Integer _page;

        public Integer getPage()
        {
            return _page;
        }

        public void setPage(Integer page)
        {
            if (page <= 0)
            {
                throw new IllegalArgumentException("page");
            }

            _page = page;
        }

        @SerializedName("limit")
        private Integer _pageSize;

        public Integer getPageSize()
        {
            return _pageSize;
        }

        public void setPageSize(Integer pageSize)
        {
            if (pageSize <= 0)
            {
                throw new IllegalArgumentException("pageSize");
            }

            _pageSize = pageSize;
        }

        @SerializedName("total")
        private Integer _total;

        public Integer getTotal()
        {
            return _total;
        }

        public void setTotal(Integer total)
        {
            if (total < 0)
            {
                throw new IllegalArgumentException("total");
            }

            _total = total;
        }
    }
}