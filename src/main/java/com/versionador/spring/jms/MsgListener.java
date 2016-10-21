package com.versionador.spring.jms;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.xmp.XMPMeta;
import com.itextpdf.xmp.XMPMetaFactory;
import com.versionador.spring.model.Gd;
import com.versionador.spring.service.GdService;

public class MsgListener implements MessageListener {
	
	@Autowired
	private GdService gdService;
	
	private static final Logger logger = LoggerFactory.getLogger(MsgListener.class);
 
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        PdfReader ReadInputPDF;
        Gd gd = new Gd();
        
        try {
        	File file = new File(mapMessage.getString("message"));
        	ReadInputPDF = new PdfReader(file.toString());
    		byte Document_MetaData[] = ReadInputPDF.getMetadata();
    		String strFileContent = new String(Document_MetaData); 
        	gd = GdPopulate(strFileContent, file, gd);
        	gdService.insertGd(gd);
        	deleteFile(file.toPath());
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    public Gd GdPopulate(String xmpString, File file, Gd gd) {
		XMPMeta xmpMeta;
		try {			
			Path path = Paths.get(file.getCanonicalPath());
			xmpMeta = XMPMetaFactory.parseFromString(xmpString);
			gd.setNomeArquivo(file.getName());
			gd.setDataCriacao(xmpMeta.getPropertyString("http://www.wdev.com.br/namespace/1.0/", "dataCriacao"));
			gd.setDescricao(xmpMeta.getPropertyString("http://www.wdev.com.br/namespace/1.0/", "descricao"));
			gd.setGd(xmpMeta.getPropertyString("http://www.wdev.com.br/namespace/1.0/", "gd"));
			gd.setVersao(xmpMeta.getPropertyString("http://www.wdev.com.br/namespace/1.0/", "versao"));
			gd.setArquivo(Files.readAllBytes(path));
		} catch (Exception e) {
			logger.error("EXCEPTION :: " + e);
		}
		return gd;
	}
    
    public Boolean deleteFile(Path path) throws IOException {
		File file = new File(path.toString());
		return file.delete();
	}
    
 
}