package ru.tusur.fdo.serviceapp.domain.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.Request;
import ru.tusur.fdo.serviceapp.util.DateUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain.service
 * by Oleg Alekseev
 * 02.06.14.
 */
@Service
public class ReportService {

    private static final String APPLICATION_CTX = "classpath:spring/applicationContext.xml";

    private static final String REPORTS_FONT = "classpath:font/tahoma.ttf";

    @Autowired
    private RequestService requestService;

    @Autowired
    private PersonService personService;

    private Font font;

    public byte[] requestsByEmployeesInPeriod(LocalDate from, LocalDate to, int employeeId) throws DocumentException, IOException {
        Person employee = personService.getById(employeeId);
        List<Request> reportRequests = requestService.requestsInRangeByEmployee(from, to, employee);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document pdfDocument = initReport("Заявки по сотруднику", baos);
        font.setSize(12);
        Paragraph para = new Paragraph(employee.getLastName() + " " + employee.getFirstName() + " " + employee.getMiddleName());
        para.setAlignment(Element.ALIGN_CENTER);
        pdfDocument.add(para);
        PdfPTable table = new PdfPTable(5);
        table.addCell(paragraph("ID"));
        table.addCell(paragraph("Заголовок"));
        table.addCell(paragraph("Желаемая дата"));
        table.addCell(paragraph("Крайний срок"));
        table.addCell(paragraph("Назначена на"));
        for (Request request : reportRequests) {
            table.addCell(paragraph(Integer.toString(request.getId())));
            table.addCell(paragraph(request.getTitle()));
            table.addCell(paragraph(DateUtils.stringFromLocalDate(request.getTargetDate())));
            table.addCell(paragraph(DateUtils.stringFromLocalDate(request.getDueDate())));
            table.addCell(paragraph(request.getAssignee().getEmail()));
        }
        pdfDocument.add(table);
        pdfDocument.close();
        return baos.toByteArray();
    }

    private Document initReport(String header, ByteArrayOutputStream baos) throws DocumentException, IOException {
        Document pdfDocument = new Document();
        PdfWriter.getInstance(pdfDocument, baos);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CTX);
        Resource resource = applicationContext.getResource(REPORTS_FONT);
        BaseFont baseFont = BaseFont.createFont(resource.getFile().getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(baseFont);
        pdfDocument.open();
        Paragraph paragraph = new Paragraph(header, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        font.setSize(24);
        paragraph.setFont(font);
        pdfDocument.add(paragraph);
        pdfDocument.add(new Paragraph(""));
        pdfDocument.add(new Paragraph(""));
        return pdfDocument;
    }

    private Paragraph paragraph(String text) {
        return new Paragraph(text, font);
    }

}
