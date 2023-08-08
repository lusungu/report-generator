package com.elucive.reportgenerator.api.DTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayrollStatisticsDTO {
	
	private String category;
	private String tranYear;
	private BigDecimal numberOfBeneficiaries;
	private BigDecimal totalPaid;

}
