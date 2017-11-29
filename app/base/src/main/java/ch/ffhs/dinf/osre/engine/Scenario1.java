package ch.ffhs.dinf.osre.engine;

public interface Scenario1 {

	final static String AUTHOR = "TEST Author";
	final static String FILENAME = "SZENARIO 1 TEST";
	final static String SUBJECT = "Subject of Scenario 1";
	final static String KEYWORDS = "szenario1, pdf-test, loadtest";

	/**
	 * Set creator of File
	 */
	void setAuthor();

	/**
	 * Set Filename ();
	 */
	void setFileName();

	/**
	 * Defines HELVETICA Size 12 and Normal
	 * 
	 * @throws Exception
	 */
	void setFont() throws Exception;

	/**
	 * Prints out every Chapter one by one
	 * @throws Exception 
	 */
	void createChapters() throws Exception;

	/**
	 * Set PageSize to A4
	 */
	void setPageSize();

	/**
	 * Set Subject Information of the PDF
	 */
	void setSubject();

	/**
	 * Set Keywords that fit PDF
	 */
	void setKeywords();
}
