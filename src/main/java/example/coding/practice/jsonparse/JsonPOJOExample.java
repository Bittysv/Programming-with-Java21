package example.coding.practice.jsonparse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPOJOExample {
	public static record Person(String name, int age) {
	}

	public static void main(String[] args) throws Exception {
		String json = "{\"name\":\"Alice\",\"age\":30}";
		ObjectMapper mapper = new ObjectMapper();
		
		Person p = mapper.readValue(json, Person.class);
		System.out.println(p);
		
		var node = mapper.readTree(json);
		System.out.println(node.get("name").asText());

	}

}
