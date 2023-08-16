package com.elucive.reportgenerator.converter.pdf;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.xhtmlrenderer.pdf.ITextRenderer;

import com.elucive.reportgenerator.DTO.ClaimDTO;
import com.elucive.reportgenerator.DTO.PayrollDTO;
import com.elucive.reportgenerator.api.DTO.EmployerStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.PayrollDependantStatisticsDTO;
import com.elucive.reportgenerator.api.DTO.SectorStatisticsDTO;
import com.elucive.reportgenerator.service.ReportJsonService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/report/download/pdf")
@RequiredArgsConstructor
public class ReportPDFController {
	
	private final ReportJsonService reportService;
	
	@GetMapping("")
	public void generatePDFReport(final ModelMap model, HttpServletResponse response) {
				
		try {
			List<EmployerStatisticsDTO> employerStats = reportService.employerStats();
			List<SectorStatisticsDTO> sectorStats = reportService.sectorStats();
			List<PayrollDependantStatisticsDTO> payrollDependantStats = reportService.payrollDependantStatistics();

			List<ClaimDTO> claimStats = reportService.claimStatistics();
			List<PayrollDTO> payrollStats = reportService.payrollStats();
			
			Map<List<EmployerStatisticsDTO>, List<EmployerStatisticsDTO>> employerStatData = 
					new HashMap<List<EmployerStatisticsDTO>, List<EmployerStatisticsDTO>>();
			employerStatData.put(employerStats, employerStats);
			
			Map<List<SectorStatisticsDTO>, List<SectorStatisticsDTO>> sectorStatData = 
					new HashMap<List<SectorStatisticsDTO>, List<SectorStatisticsDTO>>();
			sectorStatData.put(sectorStats, sectorStats);
			
			Map<List<PayrollDependantStatisticsDTO>, List<PayrollDependantStatisticsDTO>> payrollDepStatData = 
					new HashMap<List<PayrollDependantStatisticsDTO>, List<PayrollDependantStatisticsDTO>>();
			payrollDepStatData.put(payrollDependantStats, payrollDependantStats);
			
			Map<List<ClaimDTO>, List<ClaimDTO>> claimStatData = 
					new HashMap<List<ClaimDTO>, List<ClaimDTO>>();
			claimStatData.put(claimStats, claimStats);
			
			Map<List<PayrollDTO>, List<PayrollDTO>> payrollStatData = 
					new HashMap<List<PayrollDTO>, List<PayrollDTO>>();
			payrollStatData.put(payrollStats, payrollStats);
			
			
			String processedHtml = convertToHtml("report", employerStatData, sectorStatData,
					payrollStatData, payrollDepStatData, claimStatData);
						
			String fileName = "Report_";
				        
	        response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=" + fileName+".pdf");
			
			final OutputStream outStream = response.getOutputStream();
			
			/*
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			
			renderer.createPDF(outStream, false);
			renderer.finishPDF();
			
	        outStream.close(); */
	        
	        PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(processedHtml, null);
            builder.toStream(outStream);
            builder.run();
	        
	        log.info("Downloaded - {}", fileName);
	        outStream.close();
		} catch (Exception e) {
			log.error("Error occured", e);
		}
		
	}
	
	private String convertToHtml(String templateName,
			Map<List<EmployerStatisticsDTO>, List<EmployerStatisticsDTO>> employerStatData,
			Map<List<SectorStatisticsDTO>, List<SectorStatisticsDTO>> sectorStatData,
			Map<List<PayrollDTO>, List<PayrollDTO>> payrollStatData,
			Map<List<PayrollDependantStatisticsDTO>, List<PayrollDependantStatisticsDTO>> payrollDepStatData,
			Map<List<ClaimDTO>, List<ClaimDTO>> claimStatData) {
		
		Assert.notNull(templateName, "The templateName can not be null");		
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("templates/");
        templateResolver.setCharacterEncoding("UTF-8");
        
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        
        Context ctx = new Context();
		if (employerStatData != null) {
			Iterator<Entry<List<EmployerStatisticsDTO>, List<EmployerStatisticsDTO>>> itMap = employerStatData.entrySet().iterator();
			while (itMap.hasNext()) {
				Entry<List<EmployerStatisticsDTO>, List<EmployerStatisticsDTO>> pair = itMap.next();
				ctx.setVariable("employerStats", pair.getValue());
			}
		}
		
		if (sectorStatData != null) {
			Iterator<Entry<List<SectorStatisticsDTO>, List<SectorStatisticsDTO>>> itMap = sectorStatData.entrySet().iterator();
			while (itMap.hasNext()) {
				Entry<List<SectorStatisticsDTO>, List<SectorStatisticsDTO>> pair = itMap.next();
				ctx.setVariable("sectorStats", pair.getValue());
			}
		} 
		
		if (claimStatData != null) {
			Iterator<Entry<List<ClaimDTO>, List<ClaimDTO>>> itMap = claimStatData.entrySet().iterator();
			while (itMap.hasNext()) {
				Entry<List<ClaimDTO>, List<ClaimDTO>> pair = itMap.next();
				ctx.setVariable("claimStats", pair.getValue());
			}
		}
		
		if (payrollStatData != null) {
			Iterator<Entry<List<PayrollDTO>, List<PayrollDTO>>> itMap = payrollStatData.entrySet().iterator();
			while (itMap.hasNext()) {
				Entry<List<PayrollDTO>, List<PayrollDTO>> pair = itMap.next();
				ctx.setVariable("payrollStats", pair.getValue());
			}
		} 

        ctx.setVariable("nyu", "Emanuel");
		String processedHtml = templateEngine.process(templateName, ctx);
		
		return processedHtml;
	}	

}
