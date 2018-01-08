package ch.ffhs.dinf.osre.apachepdf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Utils {

	public static enum Style {
		H1(21, PDType1Font.HELVETICA_BOLD), H2(18, PDType1Font.HELVETICA_BOLD), H3(14,
				PDType1Font.HELVETICA_BOLD), bold(12, PDType1Font.HELVETICA_BOLD), italic(12,
						PDType1Font.HELVETICA_OBLIQUE), bold_italic(12, PDType1Font.HELVETICA_BOLD_OBLIQUE), small(8,
								PDType1Font.HELVETICA), normal(12, PDType1Font.HELVETICA);

		public int fontsize;
		public PDType1Font font;

		Style(int fontsize, PDType1Font font) {
			this.fontsize = fontsize;
			this.font = font;
		}

	}

	/**
	 * Quelle :
	 * https://stackoverflow.com/questions/19635275/how-to-generate-multiple-lines-in-pdf-using-apache-pdfbox
	 * 
	 * @param inputText
	 * @param fontSize
	 * @param pagewith
	 * @param pdfFont
	 * @return
	 * @throws IOException
	 */
	public static String[] makeSubtextByFontSize(String inputText, int fontSize, float pagewith, PDFont pdfFont)
			throws IOException {

		if (inputText == null) {
			inputText = "";
		}

		List<String> lines = new ArrayList<String>();
		String[] split = inputText.split("\n");

		for (String string : split) {

			int lastSpace = -1;
			while (string.length() > 0) {
				int spaceIndex = string.indexOf(' ', lastSpace + 1);
				if (spaceIndex < 0)
					spaceIndex = string.length();
				String subString = string.substring(0, spaceIndex);
				float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
				if (size > pagewith) {
					if (lastSpace < 0) {
						lastSpace = spaceIndex;
					}
					subString = string.substring(0, lastSpace);
					lines.add(subString);
					string = string.substring(lastSpace).trim();
					lastSpace = -1;
				} else if (spaceIndex == string.length()) {
					lines.add(string);
					string = "";
				} else {
					lastSpace = spaceIndex;
				}
			}

		}
		
		return lines.toArray(new String[0]);
	}

}
