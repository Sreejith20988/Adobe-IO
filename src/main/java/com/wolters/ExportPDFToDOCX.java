package com.wolters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.platform.operation.ExecutionContext;
import com.adobe.platform.operation.auth.Credentials;
import com.adobe.platform.operation.exception.SdkException;
import com.adobe.platform.operation.exception.ServiceApiException;
import com.adobe.platform.operation.exception.ServiceUsageException;
import com.adobe.platform.operation.io.FileRef;
import com.adobe.platform.operation.pdfops.ExportPDFOperation;
import com.adobe.platform.operation.pdfops.options.exportpdf.ExportPDFTargetFormat;

/**
 * export a PDF file to a Word (DOCX) file
 * <p>
 * Refer to README.md for instructions on how to run the samples.
 */
public class ExportPDFToDOCX {

    // Initialize the logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportPDFToDOCX.class);

    public void exportToPDF(String file) {

        try {
      
            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .fromFile("pdftools-api-credentials.json")
                    .build();
            //Create an ExecutionContext using credentials and create a new operation instance.
            ExecutionContext executionContext = ExecutionContext.create(credentials);
            ExportPDFOperation exportPdfOperation = ExportPDFOperation.createNew(ExportPDFTargetFormat.DOCX);

            // Set operation input from a local PDF file
            FileRef sourceFileRef = FileRef.createFromLocalFile(file);
            exportPdfOperation.setInput(sourceFileRef);

            // Execute the operation.
            FileRef result = exportPdfOperation.execute(executionContext);

            //resources folder
            String resourceFolder = "src/main/resources/";

            // Save the result to the specified location.
            result.saveAs(resourceFolder + "example_input.docx");

        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error("Exception encountered while executing operation", ex);
        }
    }


}
