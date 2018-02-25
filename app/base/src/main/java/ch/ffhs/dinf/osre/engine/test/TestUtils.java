package ch.ffhs.dinf.osre.engine.test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

import ch.ffhs.dinf.osre.engine.api.ActivityDetails;
import ch.ffhs.dinf.osre.engine.api.ActivityEntry;
import ch.ffhs.dinf.osre.engine.api.ActivityGroup;
import ch.ffhs.dinf.osre.engine.api.Contact;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

public class TestUtils {

	public static PdfRequestScenario1 getPdfRequest1() {

		PdfRequestScenario1 pdfRequest = new PdfRequestScenario1();
		ArrayList<ActivityDetails> data = new ArrayList<ActivityDetails>();
		ActivityDetails aD = new ActivityDetails();
		aD.setTitle("Aktivit�ts Titel");
		aD.setDatumBis("12.02.2017 16:00");
		aD.setDatumVon("12.02.2017 16:00");
		aD.setDescription(TOM_SAWYER);
		aD.setPlace("Musterstrasse 12\n9000 St. Gallen\nSchweiz");
		aD.setIncharge("Denis Bittante");
		aD.setHelper("Lars Hauser, Someoneelse");
		aD.setAuthor("Denis Bittante");
		data.add(aD);
		ActivityDetails aD2 = new ActivityDetails();
		aD2.setTitle("Aktivit�ts Titel");
		aD2.setDatumBis("12.02.2017 16:00");
		aD2.setDatumVon("12.02.2017 16:00");
		aD2.setDescription(TOM_SAWYER + " " + TOM_SAWYER);
		aD2.setPlace("Musterstrasse 12\n9000 St. Gallen\nSchweiz");
		aD2.setIncharge("Denis Bittante");
		aD2.setHelper("Lars Hauser, Someoneelse");
		aD2.setAuthor("Denis Bittante");
		data.add(aD2);
		pdfRequest.setData(data);

		return pdfRequest;

	}

	private static final String TOM_SAWYER = ("Die Sommerabende waren lang. Noch war's nicht dunkel geworden. Toms Pfeifen ver"
			+ "stummte pl�tzlich. Ein Fremder stand vor ihm, ein Junge, nur vielleicht einen Z"
			+ "oll gr��er als er selbst. \n \nDie Erscheinung eines Fremden irgendwelchen Alters od"
			+ "er Geschlechtes war ein Ereignis in dem armen, kleinen St�dtchen St. Petersburg"
			+ ". Und dieser Junge war noch dazu sauber gekleidet, � sauber gekleidet an einem "
			+ "Wochentage! Das war einfach geradezu unfa�lich, �berw�ltigend! Seine M�tze war "
			+ "ein niedliches, zierliches Ding, seine dunkelblaue, dicht zugekn�pfte Tuchjacke"
			+ " nett und tadellos: auch die Hosen waren ohne Flecken. Schuhe hatte er an, Schu"
			+ "he, und es war doch heute erst Freitag, noch zwei ganze Tage bis zum Sonntag! U"
			+ "m den Hals trug er ein seidenes Tuch geschlungen. Er hatte so etwas Zivilisiert"
			+ "es, so etwas St�dtisches an sich, das Tom in die innerste Seele schnitt. Je meh"
			+ "r er dieses Wunder von Eleganz anstarrte, je mehr er die Nase r�mpfte �ber den "
			+ "�erb�rmlichen Schwindel�, wie er sich innerlich ausdr�ckte, desto sch�biger und"
			+ " ruppiger d�nkte ihn seine eigene Ausstattung. Keiner der Jungen sprach. Wenn d"
			+ "er eine sich bewegte, bewegte sich auch der andere, aber immer nur seitw�rts im"
			+ " Kreise herum. So standen sie einander gegen�ber, Angesicht zu Angesicht, Auge " + "in Auge.");

	public static PdfRequestScenario2 getPdfRequest2() {
		PdfRequestScenario2 pdfRequest2 = new PdfRequestScenario2();
		ArrayList<ActivityGroup> groups = new ArrayList<>();
		ActivityGroup acGr1 = new ActivityGroup();
		ActivityGroup acGr2 = new ActivityGroup();
		ActivityGroup acGr3 = new ActivityGroup();

		// -------
		acGr1.setTitle("21.2.12 Vortr�ge f�r diesen Tag");
		acGr1.setFooter("Special Information as Footnote");
		ArrayList<ActivityEntry> entries = new ArrayList<>();
		entries.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("19:05", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("Ein Untertitel"));
		entries.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		entries.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		acGr1.setEntries(entries);
		// -------
		acGr2.setTitle("21.2.12 Vortr�ge f�r diesen Tag");
		acGr2.setFooter("Special Information as Footnote");
		ArrayList<ActivityEntry> entries2 = new ArrayList<>();
		entries2.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("19:03", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("Ein Untertitel"));
		entries2.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("19:00", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("19:50", "Ein Titel", "D.Bittante", false));
		entries2.add(new ActivityEntry("Ein Untertitel"));
		acGr2.setEntries(entries2);
		// ---------
		acGr3.setTitle("21.2.12 Vortr�ge f�r diesen Tag");
		acGr3.setFooter("Special Information as Footnote");
		ArrayList<ActivityEntry> entries3 = new ArrayList<>();
		entries3.add(new ActivityEntry("19:00", "Ein Titel sehr langer titel und noch ein wort und noch ein wort",
				"D.Bittante", false));
		entries3.add(new ActivityEntry("19:12", "Ein Titel", "D.Bittante", false));
		entries3.add(new ActivityEntry("Ein Untertitel"));
		entries3.add(new ActivityEntry("19:25", "Ein Titel", "D.Bittante", false));
		entries3.add(new ActivityEntry("19:25", "Ein Titel", "D.Bittante", false));
		entries3.add(new ActivityEntry("Ein Untertitel"));
		entries3.add(new ActivityEntry("19:25", "Ein Titel", "D.Bittante", false));
		entries3.add(new ActivityEntry("19:25", "Ein Titel", "D.Bittante", false));
		entries3.add(new ActivityEntry("19:25", "Ein Titel", "D.Bittante", false));
		entries3.add(new ActivityEntry("19:25", "Ein Titel", "D.Bittante", false));
		acGr3.setEntries(entries3);
		// --------
		groups.add(acGr1);
		groups.add(acGr2);
		groups.add(acGr3);

		pdfRequest2.setGroup(groups);
		return pdfRequest2;

	}

	public static PdfRequestScenario3 getPdfRequest3() {

		PdfRequestScenario3 pdfRequest3 = new PdfRequestScenario3();
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		Contact contact = new Contact();
		contact.setGender("m");
		contact.setAddress("Musterstrasse 12");
		contact.setBirthday("2.2.1988");
		contact.setEmail("max.muster2000@gmx.ch");
		contact.setFirstname("Max");
		contact.setMob("+41 55 555 55 55");
		contact.setName("Mustername");
		contact.setNote("schlaue Notiz f�r diese Person");
		contact.setPlace("Ort");
		contact.setTel("+41 55 555 55 55");
		contact.setZip("9000");
		contacts.add(contact);

		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		createContact(contacts);
		pdfRequest3.setContacts(contacts);

		return pdfRequest3;

	}

	private static void createContact(ArrayList<Contact> contacts) {
		Contact contact2 = new Contact();
		contact2.setGender("f");
		contact2.setAddress("Musterstrasse 12");
		contact2.setBirthday("2.2.1988");
		contact2.setEmail("max.muster2000@gmx.ch");
		contact2.setFirstname("Maxine");
		contact2.setMob("+41 55 555 55 55");
		contact2.setName("Mustername");
		contact2.setNote("keine Notiz");
		contact2.setPlace("Ort");
		contact2.setTel("+41 55 222 22 22");
		contact2.setZip("9000");
		contacts.add(contact2);
	}

	public static void showPdf(PdfResponse pdfResponse) throws IOException, FileNotFoundException {

		// To display the PDF directly on the screen
		
		 if (pdfResponse.getFile().length() > 0) {
		  
		  File tempFile2 = File.createTempFile("szenario", ".pdf");
		  
		  FileOutputStream fileInputStreamReader = new
		  FileOutputStream(tempFile2);
		  fileInputStreamReader.write(Base64.decodeBase64(pdfResponse.getFile()));
		  fileInputStreamReader.close();
		  
		  if (Desktop.isDesktopSupported()) {
		  Desktop.getDesktop().open(tempFile2); } else {
		  System.out.println("Awt Desktop is not supported!"); }
		  
		  } else { System.out.println("File is not exists!"); }
		 
	}

}
