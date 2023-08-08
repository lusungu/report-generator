package com.elucive.reportgenerator.DTO;

import java.util.List;

import lombok.Data;

@Data
public class PayrollYearsDTO {
	
	private String tranYear;
	private List<PayrollMetaDTO> meta;

}
