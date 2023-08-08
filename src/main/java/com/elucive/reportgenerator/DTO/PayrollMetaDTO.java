package com.elucive.reportgenerator.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PayrollMetaDTO {

	private BigDecimal numberOfBeneficiaries;
	private BigDecimal totalPaid;

}
