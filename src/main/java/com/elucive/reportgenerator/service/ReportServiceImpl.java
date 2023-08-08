package com.elucive.reportgenerator.service;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.elucive.reportgenerator.DTO.ClaimDTO;
import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.ClaimStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;
import com.elucive.reportgenerator.util.ReportUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final WebClient webClient;
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(15);
	private static final String ENDPOINT_URL = "http://localhost:8889/api/v1/statistics";

	@Override
	public List<EmployerStatisticsDTO> employerStats() {

		final String employerEndpoint = "/employer";

		//@formatter:off
		String response = webClient.get()
				.uri(ENDPOINT_URL + employerEndpoint)
				.retrieve()
				.bodyToMono(String.class)
				.block(REQUEST_TIMEOUT);
		//@formatter:on

		List<EmployerStatisticsDTO> employerStats = translateToEmployerStats(response);
		return employerStats;
	}

	@Override
	public List<SectorStatisticsDTO> sectorStats() {
		final String sectorEndpoint = "/sector";

		//@formatter:off
		String response = webClient.get()
				.uri(ENDPOINT_URL + sectorEndpoint)
				.retrieve()
				.bodyToMono(String.class)
				.block(REQUEST_TIMEOUT);
		//@formatter:on

		List<SectorStatisticsDTO> sectorStats = translateToSectorStats(response);
		for (SectorStatisticsDTO sector : sectorStats) {
			sector.setClassDescription(sector.getClassDescription());
		}
		
		return sectorStats;
	}

	@Override
	public List<ClaimStatisticsDTO> claimStats() {
		final String claimEndpoint = "/claim";

		//@formatter:off
		String response = webClient.get()
				.uri(ENDPOINT_URL + claimEndpoint)
				.retrieve()
				.bodyToMono(String.class)
				.block(REQUEST_TIMEOUT);
		//@formatter:on

		List<ClaimStatisticsDTO> claimStats = translateToClaimStats(response);
		return claimStats;
	}
	
	@Override
	public List<PayrollStatisticsDTO> payrollStatistics() {
		final String payrollEndpoint = "/payroll";

		//@formatter:off
		String response = webClient.get()
				.uri(ENDPOINT_URL + payrollEndpoint)
				.retrieve()
				.bodyToMono(String.class)
				.block(REQUEST_TIMEOUT);
		//@formatter:on

		List<PayrollStatisticsDTO> payrollStats = translateToPayrolStats(response);
		return payrollStats;
	}

	@Override
	public List<PayrollDependantStatisticsDTO> payrollDependantStatistics() {
		final String payrollDepEndpoint = "/payroll/dependants";

		//@formatter:off
		String response = webClient.get()
				.uri(ENDPOINT_URL + payrollDepEndpoint)
				.retrieve()
				.bodyToMono(String.class)
				.block(REQUEST_TIMEOUT);
		//@formatter:on

		List<PayrollDependantStatisticsDTO> payrollDepStats = 
				translateToPayrollDependantsStats(response);
		return payrollDepStats;
	}
	
	@Override
	public List<ClaimDTO> claimStatistics() {
		return ReportUtil.convertToClaimDTO(claimStats());
	}
	
	@Override
	public List<PayrollDTO> payrollStats() {
		return ReportUtil.convertToPayrollDTO(payrollStatistics());
	}

	private List<ClaimStatisticsDTO> translateToClaimStats(String response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, ClaimStatisticsDTO.class);
		try {
			return objectMapper.readValue(response, collectionType);
		} catch (JsonMappingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		} catch (JsonProcessingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		}

		return null;
	}

	private static List<EmployerStatisticsDTO> translateToEmployerStats(String response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, EmployerStatisticsDTO.class);
		try {
			return objectMapper.readValue(response, collectionType);
		} catch (JsonMappingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		} catch (JsonProcessingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		}

		return null;
	}

	private List<SectorStatisticsDTO> translateToSectorStats(String response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, SectorStatisticsDTO.class);
		try {
			return objectMapper.readValue(response, collectionType);
		} catch (JsonMappingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		} catch (JsonProcessingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		}

		return null;
	}
	
	private List<PayrollStatisticsDTO> translateToPayrolStats(String response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, PayrollStatisticsDTO.class);
		try {
			return objectMapper.readValue(response, collectionType);
		} catch (JsonMappingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		} catch (JsonProcessingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		}
		return null;
	}
	
	private List<PayrollDependantStatisticsDTO> translateToPayrollDependantsStats(String response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, PayrollDependantStatisticsDTO.class);
		try {
			return objectMapper.readValue(response, collectionType);
		} catch (JsonMappingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		} catch (JsonProcessingException e) {
			log.error("Failed to fetch countries from endpoint {}", ENDPOINT_URL, e);
		}
		return null;
	}

}
