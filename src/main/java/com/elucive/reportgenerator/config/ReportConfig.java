package com.elucive.reportgenerator.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class ReportConfig {
	
	@Bean
	public WebClient fetchCountriesApiClient() {

		final int size = 16 * 1024 * 1024;
		//@formatter:off
		
		HttpClient httpClient = HttpClient.create()
				  .responseTimeout(Duration.ofSeconds(10));
		
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
				.build();
		return WebClient.builder()
				.exchangeStrategies(strategies)
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
		//@formatter:on
		
	}

}
