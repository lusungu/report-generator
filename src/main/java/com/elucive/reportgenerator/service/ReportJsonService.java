package com.elucive.reportgenerator.service;

import java.util.List;

import com.elucive.reportgenerator.DTO.ClaimDTO;
import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;

public interface ReportJsonService {
	
	List<EmployerStatisticsDTO> employerStats();

	List<SectorStatisticsDTO> sectorStats();
		
	List<PayrollDependantStatisticsDTO> payrollDependantStatistics();

	//
	List<ClaimDTO> claimStatistics();

	List<PayrollDTO> payrollStats();

}
