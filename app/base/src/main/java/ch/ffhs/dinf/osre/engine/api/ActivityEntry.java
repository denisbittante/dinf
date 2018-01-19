package ch.ffhs.dinf.osre.engine.api;

public class ActivityEntry {

	public ActivityEntry() {
	}

	public ActivityEntry(String time, String title, String person, boolean isSubtitle) {
		this.time = time;
		this.title = title;
		this.person = person;
		this.isSubtitle = isSubtitle;
	}

	public ActivityEntry(String title) {
		this.title = title;
		this.isSubtitle = true;
	}

	private String time;
	private String title;
	private String person;
	private Boolean isSubtitle;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getIsSubtitle() {
		return isSubtitle;
	}

	public void setIsSubtitle(Boolean isSubtitle) {
		this.isSubtitle = isSubtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public boolean isSubtitle() {
		return isSubtitle;
	}

	public void setSubtitle(boolean isSubtitle) {
		this.isSubtitle = isSubtitle;
	}

}
