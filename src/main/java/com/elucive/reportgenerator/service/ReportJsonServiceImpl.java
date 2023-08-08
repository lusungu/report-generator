package com.elucive.reportgenerator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elucive.reportgenerator.DTO.ClaimDTO;
import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.ClaimStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;
import com.elucive.reportgenerator.util.ReportConverterUtil;
import com.elucive.reportgenerator.util.ReportUtil;

@Service
public class ReportJsonServiceImpl implements ReportJsonService {
	
	@Override
	public List<EmployerStatisticsDTO> employerStats() {
		String fileName = "employerStats.json";
		return ReportConverterUtil.convertEmployerStatsFromJson(fileName, EmployerStatisticsDTO.class);
	}

	@Override
	public List<SectorStatisticsDTO> sectorStats() {
		String fileName = "sector.json";
		return ReportConverterUtil.convertSectorStatsFromJson(fileName, SectorStatisticsDTO.class);
	}

	@Override
	public List<PayrollDependantStatisticsDTO> payrollDependantStatistics() {
		String fileName = "payrollDependantStats.json";
		return ReportConverterUtil.convertPayrollDepStatsFromJson(fileName, PayrollDependantStatisticsDTO.class);
	}

	@Override
	public List<ClaimDTO> claimStatistics() {
		String fileName = "claimStats.json";
		return ReportUtil.convertToClaimDTO(ReportConverterUtil.convertClaimStatsFromJson(fileName, ClaimStatisticsDTO.class));
		 
	}

	@Override
	public List<PayrollDTO> payrollStats() {
		String fileName = "PayrollStats.json";
		return ReportConverterUtil.convertPayrollStatsFromJson(fileName, PayrollDTO.class);
	}

}
