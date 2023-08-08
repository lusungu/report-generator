package com.elucive.reportgenerator.api.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EmployerStatisticsDTO {

	private BigDecimal numberOfEmployers;
	private String branch;
	private String province;
	private BigDecimal createdLastYear;
	private BigDecimal createdThisYear;
	private BigDecimal closed;
	private BigDecimal assessed;
	private BigDecimal unassessed;
	private BigDecimal actualAssessment;
	private BigDecimal targetAssessment;
	private BigDecimal achievedAssessmentRate;
	private BigDecimal compliant;
	private BigDecimal compliantRate;
	private BigDecimal actualCollection;
	private BigDecimal targetCollection;
	private BigDecimal achievedCollectionRate;
	private BigDecimal coveredThisYear;
	private BigDecimal coveredLastYear;
	
}
