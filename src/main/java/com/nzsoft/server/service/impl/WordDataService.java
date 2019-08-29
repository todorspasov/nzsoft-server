package com.nzsoft.server.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.nzsoft.server.model.ServiceData;
import com.nzsoft.server.service.DataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WordDataService implements DataService {

	private static final String BLACK_HAT_FILTER = "black-h@t";
	private static final String RANDOM_WORD_FILTER = "random-word";

	private static enum OperationMode {
		RANDOM_WORD,
		PREDEFINED_WORD,
		BLACK_HAT
	}
	
	private OperationMode operationMode;
	
	@Override
	public ServiceData getData(String filter) {
		applyFilter(filter);
		return ServiceData.builder().id(UUID.randomUUID().toString()).data(getFilteredData()).build();
	}

	private String getFilteredData() {
		if (operationMode == OperationMode.RANDOM_WORD) {
			String randomWord = randomWord();
			log.info("Generating a random word: {}", randomWord);
			return randomWord;
		} else if (operationMode == OperationMode.PREDEFINED_WORD) {
			String predefinedWord = predefinedWord();
			log.info("Generating a predefined word: {}", predefinedWord);
			return predefinedWord;
		}
		log.warn("Black Hat mode is ON. No word produced.");
		return "";
	}

	private void applyFilter(String filter) {
		if (StringUtils.isNotEmpty(filter)) {
			if (operationMode == OperationMode.BLACK_HAT) {
				//only another black hat can kill this black hat
				operationMode = BLACK_HAT_FILTER.equals(filter) ? OperationMode.PREDEFINED_WORD : operationMode;
			} else {
				operationMode = BLACK_HAT_FILTER.equals(filter) ? OperationMode.BLACK_HAT : (RANDOM_WORD_FILTER.equals(filter) ? OperationMode.RANDOM_WORD : OperationMode.PREDEFINED_WORD);
			}
		}
	}

	private String predefinedWord() {
		return "sos";
	}

	private String randomWord() {
		return RandomStringUtils.randomAlphabetic(3, 7);
	}

}
