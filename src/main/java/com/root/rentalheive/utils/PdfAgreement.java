package com.root.rentalheive.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public class PdfAgreement {
    public static ByteArrayOutputStream generatePdfStream(Map<String, Object> rowData, String title) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
        Paragraph titleParagraph = new Paragraph(title, titleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);


        Font boldFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);

        for (Map.Entry<String, Object> entry : rowData.entrySet()) {
            String columnName = entry.getKey();
            Object value = entry.getValue();

            Paragraph columnNameParagraph = new Paragraph(columnName, boldFont);
            document.add(columnNameParagraph);

            if (columnName.equals("Equipment(s)")) {
                if (value instanceof java.util.List) {
                    java.util.List<Map<String, Object>> equipmentList = (List<Map<String, Object>>) value;
                    PdfPTable table = new PdfPTable(5);
                    table.addCell("Name");
                    table.addCell("Type");
                    table.addCell("Price per day (DMA)");
                    table.addCell("End Date");
                    table.addCell("Duration in (days)");
                    for (Map<String, Object> equipmentInfo : equipmentList) {
                        table.setWidthPercentage(100);
                        table.setSpacingBefore(10f);

                        table.addCell(equipmentInfo.get("Name").toString());
                        table.addCell(equipmentInfo.get("Type").toString());
                        table.addCell(equipmentInfo.get("Price per day").toString());
                        table.addCell(equipmentInfo.get("End Date").toString());
                        table.addCell(equipmentInfo.get("Duration in (days)").toString());
                    }
                    document.add(table);
                }
            } else {
                Paragraph valueParagraph = new Paragraph(value.toString());
                document.add(valueParagraph);
            }

            document.add(new Paragraph("\n"));
        }

        String terms = "ACCEPTANCE\n" +
                "2.1 The Conditions apply to the Contract to the exclusion of any other terms that the Customer seeks to impose or incorporate, or which are implied by trade, custom, practice or course of dealing.\n" +
                "2.2 The Order constitutes an offer by the Customer to hire the Equipment in accordance with these Conditions. The Customer is responsible for ensuring that the terms of the Order are complete and accurate.\n" +
                "2.3 The Order shall only be deemed to be accepted when Oceanscan issues a written acceptance of the Order, at which point the Contract shall come into existence.\n" +
                "2.4 The Contract constitutes the entire agreement between the parties. The Customer acknowledges that it has not relied on any statement, promise, representation, assurance or warranty made or given by or on behalf of Oceanscan which is not set out in the Contract.\n" ;

        Paragraph termAndConditionLabel = new Paragraph("terme and condition :", boldFont);
        Paragraph termAndConditionContent = new Paragraph(terms);
        Paragraph signature = new Paragraph("your signature her:", boldFont);

        document.add(termAndConditionLabel);
        document.add(termAndConditionContent);
        document.add(signature);

        document.close();
        return outputStream;
    }
}
