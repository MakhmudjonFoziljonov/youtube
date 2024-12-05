package com.youtube.service;

import com.youtube.entity.ReportEntity;
import com.youtube.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportEntity createReport(ReportEntity report) {
        return reportRepository.save(report);
    }

    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    public ReportEntity getReportById(Integer id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    public void deleteReport(Integer id) {
        reportRepository.deleteById(id);
    }
}

