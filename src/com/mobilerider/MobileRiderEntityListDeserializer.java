package com.mobilerider;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ObjectConstructor;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

class MobileRiderEntityListDeserializer<TEntity> implements JsonDeserializer<Page<TEntity>>
{
    private final Class<TEntity> _entityClass;

    private Class<TEntity> getEntityClass()
    {
        return _entityClass;
    }

    public MobileRiderEntityListDeserializer(Class<TEntity> entityClass)
    {
        if (entityClass == null)
        {
            throw new IllegalArgumentException("entityClass");
        }

        _entityClass = entityClass;
    }

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
        TEntity[] arrayOfEntities = (TEntity[]) Array.newInstance(getEntityClass(), 0);
        Class<TEntity[]> arrayOfEntityType = (Class<TEntity[]>) arrayOfEntities.getClass();

        TEntity[] entities = (new Gson().fromJson(objectsElement, arrayOfEntityType));

        Page<TEntity> page = new Page<TEntity>(Arrays.asList(entities), meta.getPageSize(), meta.getPage() == 0 ? 0 : meta.getPage() - 1, meta.getTotal());

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