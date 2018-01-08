package ch.ffhs.dinf.osre.engine.api;

import java.util.List;

public class ActivityGroup {

	private String title;
	private List<ActivityEntry> entries;
	private String footer;

	public String getTitle() {
		return title;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ActivityEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<ActivityEntry> entries) {
		this.entries = entries;
	}
}
