package lk.tharindu.backend.service.impl;

import lk.tharindu.backend.exception.BodyContentNotValidException;
import lk.tharindu.backend.exception.DataNotFoundException;
import lk.tharindu.backend.model.Report;
import lk.tharindu.backend.repository.ReportRepository;
import lk.tharindu.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public Report GenarateReport(Report report) {

        if (report.getType().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty Type");
        }
        if (report.getDetails().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty Details");
        }
        return reportRepository.save(report);
    }

    @Override
    public List<Report> fetchAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report fetchReport(Report report) {
        Optional<Report> optionalReport= reportRepository.findById(report.getId());
        if(optionalReport.isPresent()){
            return optionalReport.get();
        }else {
            throw new DataNotFoundException("Report does not exist");
        }
    }

    @Override
    public Optional<Report> findById(Integer id) {
        Optional<Report> optionalReport= reportRepository.findById(id);
        if(optionalReport.isPresent()){
            return reportRepository.findById(id);
        }else {
            throw new DataNotFoundException("Report does not exist");
        }
    }

    @Override
    public void deteteById(Integer id) {
            reportRepository.deleteById(id);
    }
}
