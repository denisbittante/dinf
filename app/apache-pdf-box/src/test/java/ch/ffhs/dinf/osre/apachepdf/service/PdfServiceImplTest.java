package ch.ffhs.dinf.osre.apachepdf.service;

import static org.junit.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import ch.ffhs.dinf.osre.engine.api.PdfRequest;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

public class PdfServiceImplTest {

	PdfServiceImpl testee = new PdfServiceImpl();

	@Test
	public void test() throws IOException {

		PdfRequest pdfRequest = new PdfRequest();
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("title", "Aktivitäts Titel");
		hashMap.put("datum-von", "12.02.2017 17:00");
		hashMap.put("datum-bis", "12.02.2017 16:00");
		hashMap.put("description", TOM_SAWYER+ " "+ TOM_SAWYER);
		hashMap.put("place", "Musterstrasse 12\n9000 St. Gallen\nSchweiz");
		hashMap.put("incharge", "Denis Bittante");
		hashMap.put("helper", "Lars Hauser, Someoneelse");
		hashMap.put("author", "Denis Bittante");
		HashMap<String, String> hashMap2 = new HashMap<>();
		hashMap2.put("test2", "test322");
		data.add(hashMap);
		data.add(hashMap2);
		pdfRequest.setData(data);
		PdfResponse createPdfSzenario1 = testee.createPdfSzenario1(pdfRequest);

		if (createPdfSzenario1.getFile().length() > 0) {

			File tempFile2 = File.createTempFile("szenario1", ".pdf");
			
			FileOutputStream fileInputStreamReader = new FileOutputStream(tempFile2);
			fileInputStreamReader.write(Base64.decodeBase64(createPdfSzenario1.getFile()));;
			fileInputStreamReader.close();

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(tempFile2);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File is not exists!");
		}

	}

	
	private static final String TOM_SAWYER = (
			"Die Sommerabende waren lang. Noch war's nicht dunkel geworden. Toms Pfeifen ver" +
			"stummte plötzlich. Ein Fremder stand vor ihm, ein Junge, nur vielleicht einen Z" +
			"oll größer als er selbst. \n \nDie Erscheinung eines Fremden irgendwelchen Alters od" +
			"er Geschlechtes war ein Ereignis in dem armen, kleinen Städtchen St. Petersburg" +
			". Und dieser Junge war noch dazu sauber gekleidet, – sauber gekleidet an einem " +
			"Wochentage! Das war einfach geradezu unfaßlich, überwältigend! Seine Mütze war " +
			"ein niedliches, zierliches Ding, seine dunkelblaue, dicht zugeknöpfte Tuchjacke" +
			" nett und tadellos: auch die Hosen waren ohne Flecken. Schuhe hatte er an, Schu" +
			"he, und es war doch heute erst Freitag, noch zwei ganze Tage bis zum Sonntag! U" +
			"m den Hals trug er ein seidenes Tuch geschlungen. Er hatte so etwas Zivilisiert" +
			"es, so etwas Städtisches an sich, das Tom in die innerste Seele schnitt. Je meh" +
			"r er dieses Wunder von Eleganz anstarrte, je mehr er die Nase rümpfte über den " +
			"»erbärmlichen Schwindel«, wie er sich innerlich ausdrückte, desto schäbiger und" +
			" ruppiger dünkte ihn seine eigene Ausstattung. Keiner der Jungen sprach. Wenn d" +
			"er eine sich bewegte, bewegte sich auch der andere, aber immer nur seitwärts im" +
			" Kreise herum. So standen sie einander gegenüber, Angesicht zu Angesicht, Auge " +
			"in Auge." );
}
