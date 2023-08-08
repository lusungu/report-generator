package com.elucive.reportgenerator.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClaimMetaDTO {
	
	private String claimType;
	private BigDecimal numberOfClaims;

}
