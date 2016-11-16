package com.sobjectparser.report;

import com.itextpdf.text.Font;

import lombok.Data;

@Data
public class FormattingPDF {
	public static Font titleFormat = new Font(Font.FontFamily.COURIER, 21, Font.BOLD);
	public static Font paragraphFormat = new Font(Font.FontFamily.COURIER, 11);
}
