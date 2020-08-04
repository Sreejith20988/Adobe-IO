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
import com.adobe.platform.operation.pdfops.CreatePDFOperation;

/**
 * create a PDF file from a DOCX file.
 * <p>
 * Refer to README.md for instructions on how to run the samples.
 */
public class CreatePDFFromDOCX {

    // Initialize the logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePDFFromDOCX.class);

    public void saveToPDF(String file) {

        try {

            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .fromFile("pdftools-api-credentials.json")
                    .build();

            //Create an ExecutionContext using credentials and create a new operation instance.
            ExecutionContext executionContext = ExecutionContext.create(credentials);
            CreatePDFOperation createPdfOperation = CreatePDFOperation.createNew();

            // Set operation input from a source file.
            FileRef source = FileRef.createFromLocalFile("src/main/resources/" + file);
            createPdfOperation.setInput(source);

            // Execute the operation.
            FileRef result = createPdfOperation.execute(executionContext);

            //resources folder
            String resourceFolder = "src/main/resources/";

            // Save the result to the specified location.
            result.saveAs(resourceFolder + "example_output.pdf");


        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error("Exception encountered while executing operation", ex);
        }
    }
}
