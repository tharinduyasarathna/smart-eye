package lk.tharindu.backend.repository;

import lk.tharindu.backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
}
