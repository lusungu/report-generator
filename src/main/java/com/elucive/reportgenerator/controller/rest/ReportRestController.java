package com.elucive.reportgenerator.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.ClaimStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;
import com.elucive.reportgenerator.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportRestController {
	
	private final ReportService reportService;
	
	@GetMapping("")
	public ResponseEntity<List<EmployerStatisticsDTO>> employerStatistics(){
		return ResponseEntity.ok().body(reportService.employerStats()); 
	}
	
	@GetMapping("/sector")
	public ResponseEntity<List<SectorStatisticsDTO>> sectorStatistics(){
		return ResponseEntity.ok().body(reportService.sectorStats()); 
	}
	
	@GetMapping("/claim")
	public ResponseEntity<List<ClaimStatisticsDTO>> claimStatistics(){
		return ResponseEntity.ok().body(reportService.claimStats()); 
	}
	
	@GetMapping("/payroll")
	public ResponseEntity<List<PayrollDTO>> payrollStats(){
		return ResponseEntity.ok().body(reportService.payrollStats()); 
	}
	
	@GetMapping("/payroll/dependant")
	public ResponseEntity<List<PayrollDependantStatisticsDTO>> payrollDepStatistics(){
		return ResponseEntity.ok().body(reportService.payrollDependantStatistics()); 
	}

}
