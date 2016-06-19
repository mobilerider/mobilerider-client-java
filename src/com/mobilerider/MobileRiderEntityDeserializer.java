package com.mobilerider;

import com.google.gson.*;
import java.lang.reflect.Type;

class MobileRiderEntityDeserializer<TEntity> implements JsonDeserializer<TEntity>
{
    @Override
    public TEntity deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
    {
        // Get the "object" element from the parsed JSON
        JsonElement objectElement = je.getAsJsonObject().get("object");

        // Deserialize it.
        // We use a new instance of Gson to avoid infinite recursion to this deserializer.
        return new Gson().fromJson(objectElement, type);
    }
}
