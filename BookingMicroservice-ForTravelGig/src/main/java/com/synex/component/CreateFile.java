package com.synex.component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.synex.domain.Booking;

@Component
public class CreateFile {
    
    public String PDF(String message,String path, String fileName){
        String basePath = System.getProperty("user.dir");
        String filePath = basePath+path+fileName;
		try {
            Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(filePath));
			doc.open();
			doc.add(new Paragraph(message));
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
            filePath = null;
		} catch (DocumentException e) {
			e.printStackTrace();
            filePath = null;
		}

        return filePath;
    }

    public String messageForm(Booking bd){
        StringBuilder message = new StringBuilder();
        message.append("\nBooking Id: "+bd.getHotelId());
        message.append("\nCheck In: "+bd.getCheckInDate());
        message.append("\nCheck Out: "+bd.getCheckOutDate());
        message.append("\nGuest No. : "+bd.getGuests());
        message.append("\nRoom No. : "+bd.getNoRooms());
        message.append("\nRoom Type : "+bd.getRoomType());
        message.append("\nCustomer Name : "+bd.getUserName());
        return message.toString();
    }
}
