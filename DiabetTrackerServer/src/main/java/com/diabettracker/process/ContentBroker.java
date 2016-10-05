package com.diabettracker.process;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Transforme une chaine JSON en map classe, valeur
 * et vice et versa
 * @author Claude Moulin
 */

public class ContentBroker {
	Map<String, Object> map;
	ObjectMapper mapper;

	public ContentBroker() {
		map = new HashMap<String, Object>();
		mapper = new ObjectMapper();
	}

	public ContentBroker(String content) {
		mapper = new ObjectMapper();
		parse(content);
	}

	public ContentBroker(Map<String, Object> map) {
		this.map = map;
		mapper = new ObjectMapper();
	}

	public void put(String key, Object value) {
		map.put(key, value);
	}

	public String get(String key) {
		return (String) map.get(key);
	}

	public int getInt(String key) {
		return (int) map.get(key);
	}

	public ArrayList<String> getArrayListString(String key) {
		return (ArrayList<String>) map.get(key);
	}

	public ArrayList<Integer> getArrayListInteger(String key) {
		return (ArrayList<Integer>) map.get(key);
	}

	public ArrayList<Object> getObject(String key) {
		return (ArrayList<Object>) map.get(key);
	}

	public ArrayList<ObjectMapper> getObjectMapper(String key) {
		return (ArrayList<ObjectMapper>) map.get(key);
	}

	public Map<String, Object> getMap() {
		return map;
	}

	private void parse(String content) {
		try {
			map = mapper.readValue(content, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toJson() {
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, map);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sw.toString();
	}
}
