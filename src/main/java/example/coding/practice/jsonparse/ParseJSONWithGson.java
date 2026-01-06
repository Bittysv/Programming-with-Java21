package example.coding.practice.jsonparse;

import com.google.gson.Gson;

public class ParseJSONWithGson {
	
	record Person(String name, int age) {}
	
	public static void main(String[] args) {
		String json = "{\"name\":\"Bob\", \"age\": 30}";
		Gson gson = new Gson();
		Person p = gson.fromJson(json, Person.class);
		System.out.println(p);
	}

}
