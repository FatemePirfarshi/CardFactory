package com.example.cardfactory.data.remot;

import android.util.Log;

import com.example.cardfactory.data.model.Card;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetCardItemDeserializer implements JsonDeserializer<List<Card>> {

    @Override
    public List<Card> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        List<Card> cardList = new ArrayList<>();
        Log.e("cardDeserializer", "deserializer called");

        JsonObject bodyObject = json.getAsJsonObject();

        JsonArray cardArray = bodyObject.getAsJsonArray("cards");

        for (int i = 0; i < cardArray.size(); i++) {
            JsonObject jsonObject = cardArray.get(i).getAsJsonObject();

            int code = jsonObject.get("code").getAsInt();
            Log.e("cardDeserializer", String.valueOf(code));

            String title = jsonObject.get("title").getAsString();
            String description = jsonObject.get("description").getAsString();
            String tag = jsonObject.get("tag").getAsString();

            Card card = new Card(code, title, description, tag);
            cardList.add(checkCode(card, jsonObject));
        }

        return cardList;
    }

    //some items don't have sound or image this method check this point
    private Card checkCode(Card card, JsonObject jsonObject) {

        switch (card.getCode()) {
            case 0:
                card.setImageLink(jsonObject.get("image").getAsString());
                break;
            case 2:
                card.setSoundLink(jsonObject.get("sound").getAsString());
                break;
        }
        return card;
    }
}
