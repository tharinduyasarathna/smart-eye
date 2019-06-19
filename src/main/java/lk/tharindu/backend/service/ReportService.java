package lk.tharindu.backend.service;

import lk.tharindu.backend.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    Report GenarateReport(Report report);
    List<Report> fetchAllReports();
    Report fetchReport(Report report);
    Optional<Report> findById(Integer id);
    void deteteById(Integer id);
}
