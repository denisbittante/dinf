package ch.ffhs.dinf.osre.engine.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PdfResponse {

	private String name;
	private String description;
	private String file;
	private String status;
	private List<HashMap<String, String>> input;

	public PdfResponse(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public List<HashMap<String, String>> getInput() {
		
		
		input = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap1 = new HashMap<String, String>();
		hashMap1.put("test 10", "test2");
		hashMap1.put("test 11", "test2");
		HashMap<String, String> hashMap2 = new HashMap<String, String>();
		hashMap2.put("test 12", "test2");
		hashMap2.put("test 13", "test2");
		hashMap2.put("test 14", "test2");
		input.add(hashMap1);
		input.add(hashMap2);
		
		return input;
	}

	public void setInput(List<HashMap<String, String>> input) {
		this.input = input;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
