package com.wolters;
import net.minidev.json.JSONUtil;

/**
 * Converts a scanned PDF file to a searchable PDF file
 * Input PDF is converted to DOCX file and then saved as PDF file
 * Input file location is hard-coded for now, therefore this file should be placed in the resources folder.
 * Intermediate and Final PDF file are saved in the resources folder
 */

public class App {

    public static void main(String[] args)
    {
        String inputFile = "example_input.pdf";
        ExportPDFToDOCX exportPDFToDOCX = new ExportPDFToDOCX();
        exportPDFToDOCX.exportToPDF( "src/main/resources/" + inputFile);

        String interimFile = "example_input.docx";
        CreatePDFFromDOCX createPDFFromDOCX = new CreatePDFFromDOCX();
        createPDFFromDOCX.saveToPDF(interimFile);

    }

}
