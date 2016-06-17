package com.mobilerider;

import com.google.gson.*;
import java.lang.reflect.Type;

class MobileRiderEntityDeserializer<EntityType> implements JsonDeserializer<EntityType>
{
    @Override
    public EntityType deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        // Get the "object" element from the parsed JSON
        JsonElement object = je.getAsJsonObject().get("object");

        // Deserialize it.
        // We use a new instance of Gson to avoid infinite recursion to this deserializer.
        return new Gson().fromJson(object, type);
    }
}
