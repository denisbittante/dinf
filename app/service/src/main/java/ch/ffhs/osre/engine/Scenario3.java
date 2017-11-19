package ch.ffhs.osre.engine;

public interface Scenario3 extends Scenario2 {



	/**
	 * Set Footer with [Page] / [Count Pages]
	 */
	void setfooter();

	/**
	 * Set PageBorder all around = 1cm
	 */
	void setPageBorder();

	/**
	 * FontFamily = Arrial Narrow
	 */
	void setFontFamily();

	/**
	 * Backgroundcolor = yellow
	 */
	void setBackgroundColor();

	/**
	 * Creates a Table at the beginning of the PDF
	 */
	void createTable();
	
}
