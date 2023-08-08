package com.elucive.reportgenerator.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.ClaimStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportConverterUtil {
	
	private static final String RESOURCE_LOCATION = "src/main/resources/";
	
	public static List<EmployerStatisticsDTO> convertEmployerStatsFromJson(String fileName, 
			Class<?> elementClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, elementClass);
		try {			
			
			var path = Paths.get(RESOURCE_LOCATION, fileName);
			return objectMapper.readValue(Files.readString(path), collectionType);
			
		} catch (JsonMappingException e) {
			log.error("Failed to map object", e);
		} catch (JsonProcessingException e) {
			log.error("An error occured", e);
		} catch (IOException e) {
			log.error("Could not convert json file to required body of {}", elementClass, e);
		}
		
		return null;
	}

	public static List<SectorStatisticsDTO> convertSectorStatsFromJson(String fileName,
			Class<?> elementClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, elementClass);
		try {			
			
			var path = Paths.get(RESOURCE_LOCATION, fileName);
			return objectMapper.readValue(Files.readString(path), collectionType);
			
		} catch (JsonMappingException e) {
			log.error("Failed to map object", e);
		} catch (JsonProcessingException e) {
			log.error("An error occured", e);
		} catch (IOException e) {
			log.error("Could not convert json file to required body of {}", elementClass, e);
		}
		
		return null;
	}
	
	public static List<ClaimStatisticsDTO> convertClaimStatsFromJson(String fileName,
			Class<?> elementClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, elementClass);
		try {			
			
			var path = Paths.get(RESOURCE_LOCATION, fileName);
			return objectMapper.readValue(Files.readString(path), collectionType);
			
		} catch (JsonMappingException e) {
			log.error("Failed to map object", e);
		} catch (JsonProcessingException e) {
			log.error("An error occured", e);
		} catch (IOException e) {
			log.error("Could not convert json file to required body of {}", elementClass, e);
		}
		
		return null;
	}
	
	public static List<PayrollDTO> convertPayrollStatsFromJson(String fileName,
			Class<?> elementClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, elementClass);
		try {			
			
			var path = Paths.get(RESOURCE_LOCATION, fileName);
			return objectMapper.readValue(Files.readString(path), collectionType);
			
		} catch (JsonMappingException e) {
			log.error("Failed to map object", e);
		} catch (JsonProcessingException e) {
			log.error("An error occured", e);
		} catch (IOException e) {
			log.error("Could not convert json file to required body of {}", elementClass, e);
		}
		
		return null;
	}
	
	public static List<PayrollDependantStatisticsDTO> convertPayrollDepStatsFromJson(String fileName,
			Class<?> elementClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypeFactory typeFactory = objectMapper.getTypeFactory();
		CollectionType collectionType = 
				typeFactory.constructCollectionType(List.class, elementClass);
		try {			
			
			var path = Paths.get(RESOURCE_LOCATION, fileName);
			return objectMapper.readValue(Files.readString(path), collectionType);
			
		} catch (JsonMappingException e) {
			log.error("Failed to map object", e);
		} catch (JsonProcessingException e) {
			log.error("An error occured", e);
		} catch (IOException e) {
			log.error("Could not convert json file to required body of {}", elementClass, e);
		}
		
		return null;
	}

}
