package com.elucive.reportgenerator.DTO;

import java.util.List;

import lombok.Data;

@Data
public class PayrollDTO {
	
	private String category;
	private List<PayrollYearsDTO> tranYears;
	
}
