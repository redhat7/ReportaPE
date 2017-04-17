package com.example.capsula.reportapetrabajadorprueba.data.repositories.remote.deserializador;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Desarrollo3 on 23/02/2017.
 */

public class DateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        String date = element.getAsString();
        //"yyyy-MM-dd",Locale.ENGLISH
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Failed to parse Date due to:" + e);
            return null;
        }
    }
/* implements JsonDeserializer<Date>
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String date = json.getAsString();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss.SSSZ");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            return format.parse(date);
        } catch (ParseException exp) {
            System.out.println("Failed to parse Date:" + exp);
            return null;
        }
    }*/
}

