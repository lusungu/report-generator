package com.elucive.reportgenerator.api.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SectorStatisticsDTO {

	private BigDecimal numberOfEmployers;
	private String classCode;
	private String classDescription;
	private BigDecimal coveredThisYear;
	private BigDecimal coveredLastYear;
	private BigDecimal numberOfClaims;
	
}
