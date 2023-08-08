package com.elucive.reportgenerator.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ClaimDTO {
	
	private String year;
	List<ClaimMetaDTO> meta;

}
