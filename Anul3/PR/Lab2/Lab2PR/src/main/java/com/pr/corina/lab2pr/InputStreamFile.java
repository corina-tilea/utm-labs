/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab2pr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author corina
 */
public class InputStreamFile {
    
       public static void convertInputStreamToFile(InputStream inputStream, String fileName) {
           
	OutputStream outputStream = null;

	try {
		// write the inputStream to a FileOutputStream
		outputStream =
                    new FileOutputStream(fileName);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}

		System.out.println("Done!");

	} catch (IOException e) {
		e.printStackTrace();
	} finally {
//		if (outputStream != null) {
//			try {
//				// outputStream.flush();
//				outputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
            System.out.println("[INFO]: "+fileName+" has been saved!");
	}
    }
    
}
