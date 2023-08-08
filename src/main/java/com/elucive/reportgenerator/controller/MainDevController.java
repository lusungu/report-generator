package com.elucive.reportgenerator.controller;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.elucive.reportgenerator.DTO.ClaimDTO;
import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;
import com.elucive.reportgenerator.service.ReportJsonService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Profile(value = { "dev" })
public class MainDevController {

	private final ReportJsonService reportService;

	@GetMapping("")
	public String mainPage(final Model model) {

		List<EmployerStatisticsDTO> employerStats = reportService.employerStats();
		List<SectorStatisticsDTO> sectorStats = reportService.sectorStats();
		List<PayrollDependantStatisticsDTO> payrollDependantStats = reportService.payrollDependantStatistics();

		List<ClaimDTO> claimStats = reportService.claimStatistics();
		List<PayrollDTO> payrollStats = reportService.payrollStats();

		model.addAttribute("payrollDependantStats", payrollDependantStats);
		model.addAttribute("payrollStats", payrollStats);
		model.addAttribute("claimStats", claimStats);
		model.addAttribute("employerStats", employerStats);
		model.addAttribute("sectorStats", sectorStats);

		return "report";
	}

}
