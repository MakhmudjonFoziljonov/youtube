package com.youtube.controller;

import com.youtube.entity.ReportEntity;
import com.youtube.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ReportEntity createReport(@RequestBody ReportEntity report) {
        return reportService.createReport(report);
    }

    @GetMapping
    public List<ReportEntity> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ReportEntity getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Integer id) {
        reportService.deleteReport(id);
    }
}

