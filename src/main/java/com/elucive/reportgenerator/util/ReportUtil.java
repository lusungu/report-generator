package com.elucive.reportgenerator.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.elucive.reportgenerator.DTO.ClaimDTO;
import com.elucive.reportgenerator.DTO.ClaimMetaDTO;
import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.DTO.PayrollMetaDTO;
import com.elucive.reportgenerator.DTO.PayrollYearsDTO;
import com.elucive.reportgenerator.api.DTO.ClaimStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollStatisticsDTO;

public class ReportUtil {
	
	public static List<ClaimDTO> convertToClaimDTO(List<ClaimStatisticsDTO> claimStats) {
		
		List<ClaimDTO> response = new ArrayList<ClaimDTO>();		
		List<String> years = getYears(claimStats);
		
		int yearSize = years.size();
		int claimStatSize = claimStats.size();
				
		for (int j = 0; j < yearSize; j++) {
			String year = years.get(j);
			
			ClaimDTO claimDTO = new ClaimDTO();
			claimDTO.setYear(year);

			List<ClaimMetaDTO> metaList = new ArrayList<ClaimMetaDTO>();
			for (int i = 0; i < claimStatSize; i++) {
				ClaimStatisticsDTO claimStat = claimStats.get(i);
				if(claimStat.getYear().equalsIgnoreCase(year)) {
					ClaimMetaDTO meta = new ClaimMetaDTO();
					meta.setClaimType(claimStat.getClaimType());
					meta.setNumberOfClaims(claimStat.getNumberOfClaims());
					metaList.add(meta);
				}
			}
			claimDTO.setMeta(metaList);
			response.add(claimDTO);			
		}		
		return response;
	}

	private static List<String> getYears(List<ClaimStatisticsDTO> claimStats) {
		Set<String> years = new HashSet<String>();
		
		for (ClaimStatisticsDTO claimStat : claimStats) {
			years.add(claimStat.getYear());
		}
		
		return new ArrayList<>(years);
	}

	public static List<PayrollDTO> convertToPayrollDTO(List<PayrollStatisticsDTO> payrollStatistics) {
		List<PayrollDTO> response = setCategories(payrollStatistics);
		
		Set<String> trnYears = getTransactionalYears(payrollStatistics);
		response = assignTranYearDetails(response, trnYears, payrollStatistics);

		return response;
	}

	private static List<PayrollDTO> assignTranYearDetails(List<PayrollDTO> payrolls, Set<String> trnYears,
			List<PayrollStatisticsDTO> payrollStatistics) {
		
		for (PayrollDTO payroll : payrolls) {
			List<PayrollYearsDTO> tranYears = new ArrayList<PayrollYearsDTO>();
			for (String tranYear : trnYears) {
				PayrollYearsDTO payrollYear = new PayrollYearsDTO();
				payrollYear.setTranYear(tranYear);
				payrollYear = assignPayrollYearMetaData(payrollYear, payroll.getCategory(), payrollStatistics);
				tranYears.add(payrollYear);
			}
			payroll.setTranYears(tranYears);
		}		
		return payrolls;
	}

	private static PayrollYearsDTO assignPayrollYearMetaData(PayrollYearsDTO payrollYear, String category,
			List<PayrollStatisticsDTO> payrolls) {
		
		int payrollSize = payrolls.size();
		
		List<PayrollMetaDTO> meta = new ArrayList<PayrollMetaDTO>();
		
		BigDecimal numberOfBeneficiaries = new BigDecimal("0");
		BigDecimal totalPaid = new BigDecimal("0");
		
		for (int i = 0; i < payrollSize; i++) {
			PayrollStatisticsDTO payroll = payrolls.get(i);
						
			if(payroll.getCategory().equalsIgnoreCase(category) &&
					payroll.getTranYear().equalsIgnoreCase(payrollYear.getTranYear())) {
				
				numberOfBeneficiaries = payroll.getNumberOfBeneficiaries().add(numberOfBeneficiaries);
				totalPaid = payroll.getTotalPaid().add(totalPaid);
			}			
		}
		
		PayrollMetaDTO payrollMeta = new PayrollMetaDTO();
		payrollMeta.setNumberOfBeneficiaries(numberOfBeneficiaries);
		payrollMeta.setTotalPaid(totalPaid);
		meta.add(payrollMeta);
		payrollYear.setMeta(meta);
		
		return payrollYear;
	}

	private static List<PayrollDTO> setCategories(List<PayrollStatisticsDTO> payrollStatistics) {
		List<PayrollDTO> response = new ArrayList<PayrollDTO>();
		
		Set<String> categories = new HashSet<String>();
		payrollStatistics.stream().forEach(payrollStat -> categories.add(payrollStat.getCategory()));
		
		for (String category : categories) {
			PayrollDTO payroll = new PayrollDTO();
			payroll.setCategory(category);
			response.add(payroll);
		}				
		return response;
	}
	
	private static Set<String> getTransactionalYears(List<PayrollStatisticsDTO> payrollStatistics) {
		Set<String> response = new HashSet<String>();
		int payrollSize = payrollStatistics.size();
		for (int i = 0; i < payrollSize; i++) {
			PayrollStatisticsDTO payroll = payrollStatistics.get(i);
			response.add(payroll.getTranYear());
		}
		return response;
	}

}
