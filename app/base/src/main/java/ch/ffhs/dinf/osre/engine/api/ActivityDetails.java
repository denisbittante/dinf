package ch.ffhs.dinf.osre.engine.api;

public class ActivityDetails {

	private String title;
	private String datumVon;
	private String datumBis;
	private String description;
	private String place;
	private String incharge;
	private String helper;
	private String author;

	public ActivityDetails() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDatumVon() {
		return datumVon;
	}

	public void setDatumVon(String datumVon) {
		this.datumVon = datumVon;
	}

	public String getDatumBis() {
		return datumBis;
	}

	public void setDatumBis(String datumBis) {
		this.datumBis = datumBis;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getIncharge() {
		return incharge;
	}

	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}

	public String getHelper() {
		return helper;
	}

	public void setHelper(String helper) {
		this.helper = helper;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
