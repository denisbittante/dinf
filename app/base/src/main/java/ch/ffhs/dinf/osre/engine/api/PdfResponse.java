package ch.ffhs.dinf.osre.engine.api;

import java.util.List;

public class PdfResponse {

	private String name;
	private String description;
	private String file;
	private String status;
	private List<ActivityDetails> input;

	public PdfResponse(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public List<ActivityDetails> getInput() {
		return input;
	}

	public void setInput(List<ActivityDetails> input) {
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
