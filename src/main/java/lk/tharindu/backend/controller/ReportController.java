package lk.tharindu.backend.controller;

import lk.tharindu.backend.model.Report;
import lk.tharindu.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/smarteye")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/report",method = RequestMethod.POST)
    public Report genarateReport(@RequestBody Report report){
        return reportService.GenarateReport(report);
    }

    @RequestMapping(value = "/report",method = RequestMethod.GET)
    public List<Report> fetchAllReports(){
        return reportService.fetchAllReports();
    }

    @RequestMapping(value = "/report/{id}",method = RequestMethod.GET)
    public ResponseEntity<Report> fetchReportById(@PathVariable Integer id){
       Report report =new Report();
       report.setId(id);
       Report report1 = reportService.fetchReport(report);


        if(report1==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(report1);
        }


    }

    //update existing report ( only details )
    @RequestMapping(value="/updateReport/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Report> updateReport(@PathVariable Integer id, @RequestBody Report report){
        Report tempreport =new Report();
        report.setId(id);
        Report report1 = reportService.fetchReport(tempreport);

        if (!reportService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        report1.setDetails(report.getDetails());
        return ResponseEntity.ok(reportService.GenarateReport(report1));
    }

    //delete existing report
    @RequestMapping(value = "/deleteReport/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Report> deleteReports(@PathVariable Integer id){
        if (!reportService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        reportService.deteteById(id);
        return ResponseEntity.ok().build();
    }
}
