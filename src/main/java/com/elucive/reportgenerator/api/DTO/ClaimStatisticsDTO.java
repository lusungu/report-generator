package com.elucive.reportgenerator.api.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClaimStatisticsDTO {

	private String year;
	private String claimType;
	private BigDecimal numberOfClaims;
	
}
