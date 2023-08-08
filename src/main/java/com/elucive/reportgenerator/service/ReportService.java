package com.elucive.reportgenerator.service;

import java.util.List;

import com.elucive.reportgenerator.DTO.ClaimDTO;
import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.ClaimStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;


public interface ReportService {
	
	List<EmployerStatisticsDTO> employerStats();

	List<SectorStatisticsDTO> sectorStats();
	
	List<ClaimStatisticsDTO> claimStats();
	
	List<PayrollStatisticsDTO> payrollStatistics();
	
	List<PayrollDependantStatisticsDTO> payrollDependantStatistics();

	//
	List<ClaimDTO> claimStatistics();

	List<PayrollDTO> payrollStats();

}
