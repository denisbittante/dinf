package ch.ffhs.dinf.osre.itext.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.ElementPropertyContainer;

public class Utils {

	public enum Style {

		//@formatter:off
		H1			(21, StandardFonts.HELVETICA_BOLD),
		H2			(18, StandardFonts.HELVETICA_BOLD),
		H3			(14, StandardFonts.HELVETICA_BOLD),
		bold		(12, StandardFonts.HELVETICA_BOLD),
		italic		(12, StandardFonts.HELVETICA_OBLIQUE), 
		bold_italic	(12, StandardFonts.HELVETICA_BOLDOBLIQUE), 
		small		(8,  StandardFonts.HELVETICA), 
		normal		(12, StandardFonts.HELVETICA);
		//@formatter:on

		public int fontsize;
		private String font;

		Style(int fontsize, String font) {
			this.fontsize = fontsize;
			this.font = font;
		}

		public PdfFont getFont() {

			try {
				return PdfFontFactory.createFont(font);
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	public ElementPropertyContainer style(ElementPropertyContainer element, Style style) {

		element.setFont(style.getFont());
		element.setFontSize(style.fontsize);
		return element;
	}

}
