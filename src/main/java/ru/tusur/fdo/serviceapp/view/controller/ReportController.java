package ru.tusur.fdo.serviceapp.view.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.tusur.fdo.serviceapp.domain.service.ReportService;
import ru.tusur.fdo.serviceapp.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller
 * by Oleg Alekseev
 * 02.06.14.
 */
@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportService service;

    @RequestMapping("/")
    public String reportsHome(){
        return "reports";
    }

    @RequestMapping("/employee/")
    public ModelAndView requestsInRangeByEmployee() {
        return new ModelAndView("reportByEmployee");
    }

    @RequestMapping("/period/")
    public ModelAndView requestsInRange() {
        return new ModelAndView("reportInRange");
    }

    @RequestMapping(value = "/period/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getReportInPerion(HttpServletRequest req) throws IOException, DocumentException {
        LocalDate from = DateUtils.localFromString(req.getParameter("dateFrom"));
        LocalDate to = DateUtils.localFromString(req.getParameter("dateTo"));
        byte[] pdfContents = service.requestsInPeriod(from, to);
        HttpHeaders headers = new HttpHeaders();
        String fileName = "report" + DateUtils.stringFromLocalDate(LocalDate.now()) + ".pdf";
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getReportByEmployees(HttpServletRequest req) throws DocumentException, IOException {
        LocalDate from = DateUtils.localFromString(req.getParameter("dateFrom"));
        LocalDate to = DateUtils.localFromString(req.getParameter("dateTo"));
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        byte[] pdfContents = service.requestsByEmployeesInPeriod(from, to, employeeId);
        HttpHeaders headers = new HttpHeaders();
        String fileName = "report" + DateUtils.stringFromLocalDate(LocalDate.now()) + ".pdf";
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
    }

}
