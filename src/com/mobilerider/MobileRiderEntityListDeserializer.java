package com.mobilerider;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

class MobileRiderEntityListDeserializer<EntityType> implements JsonDeserializer<List<EntityType>>
{
    @Override
    public List<EntityType> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
    {
        // Get the "object" element from the parsed JSON
        //JsonElement object = je.getAsJsonObject().get("object");

        // Deserialize it.
        // We use a new instance of Gson to avoid infinite recursion to this deserializer.
        //return new Gson().fromJson(object, type);

        return null;
    }
}
