package com.versionador.spring.scheduling;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import com.itextpdf.xmp.XMPMeta;
import com.itextpdf.xmp.XMPMetaFactory;
import com.versionador.spring.domain.QueueList;
import com.versionador.spring.jms.MsgProducer;
import com.itextpdf.text.pdf.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fileReaderScheduler")
public class FileReaderScheduler {
	
	@Autowired
    private MsgProducer producer;
	
	private static final Logger logger = LoggerFactory.getLogger(FileReaderScheduler.class);
	
	private String fileserverFolder = "C:/Users/eduardo.chaves/workspace/WDEV-VersionadorEN/folder/";
	

	public void readFiles() throws IOException {
		File folder = new File(fileserverFolder);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			try {
				PdfReader ReadInputPDF;
				ReadInputPDF = new PdfReader(file.toString());
				byte Document_MetaData[] = ReadInputPDF.getMetadata();
				String strFileContent = new String(Document_MetaData); 
				if (file.isFile()) {
					if (XMPHandlerValidator(strFileContent)) {
						producer.sendMessage(file.getCanonicalPath(), QueueList.APROVED);
					} else {
						producer.sendMessage(file.getCanonicalPath(), QueueList.FAILED);
						deleteFile(file.toPath());
					}
			    }
	        } catch (Exception e) {
	        	logger.error("EXCEPTION :: " + e);
	        }
		}
	}
	
	public Boolean XMPHandlerValidator(String xmpString) {
		XMPMeta xmpMeta;
		try {		
			String updatedXmpString = xmpString.trim();
			int i = updatedXmpString.lastIndexOf('<');
			if (i > 0 && updatedXmpString.substring(i).startsWith("<?xpacket end")) {
				updatedXmpString = updatedXmpString.substring(0, i);
			}
			xmpMeta = XMPMetaFactory.parseFromString(updatedXmpString);
			if (xmpMeta != null) {
				return true;
			} 

		} catch (Exception e) {
			logger.error("EXCEPTION :: " + e);
		}
		return false;
	}
	
	public void deleteFile(Path path) {
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}
	
}
