package com.xe.xecdApiClient.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonUtils
{

	private Gson gson;
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
	private static final String TIME_ZONE = "UTC";

	public JsonUtils()
	{
		init(DATE_FORMAT, TIME_ZONE);
	}

	private void init(String dateFormat, String timeZone)
	{
		
		DateFormat df = new SimpleDateFormat(dateFormat);
		
		df.setTimeZone(TimeZone.getTimeZone(timeZone));

		final DateFormat gsonDf = df;

		JsonSerializer<Date> dateSerializer = new JsonSerializer<Date>()
		{

			@Override
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
			{
				if(src == null)
				{
					return null;
				}
				return new JsonPrimitive(gsonDf.format(src));
			}
		};

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, dateSerializer);

		gson = gsonBuilder.create();
	}

	public <T> T fromJson(String json, Class<T> clazz)
	{
		return gson.fromJson(json, clazz);
	}

	public String toJson(Object obj)
	{
		return gson.toJson(obj);
	}

}
