package com.nzsoft.server.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.nzsoft.server.model.ModeFilter;
import com.nzsoft.server.model.ServiceData;
import com.nzsoft.server.service.DataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WordDataService implements DataService {

	private static final String BLACK_HAT_FILTER = "black-h@t";
	private static final String RANDOM_WORD_FILTER = "random-word";
	private static final String WEATHER_STATION_FILTER = "weather-station";

	private static enum OperationMode {
		RANDOM_WORD,
		PREDEFINED_WORD,
		BLACK_HAT,
		RANDOM_WEATHER_ID
	}
	
	private OperationMode operationMode;
	
	public WordDataService() {
		this.operationMode = OperationMode.RANDOM_WORD;
	}
	
	@Override
	public ServiceData getData(String outTeamId) {
		String teamId = outTeamId == null ? "" : outTeamId;
		return ServiceData.builder().id(UUID.randomUUID().toString().concat(teamId)).data(getFilteredData()).build();
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
		} else if (operationMode == OperationMode.RANDOM_WEATHER_ID) {
			String randomID = randomID();
			log.info("Generating a random weather ID : {}", randomID);
			return randomID;
		}
		log.warn("Black Hat mode is ON. No word produced.");
		return "";
	}

	@Override
	public void changeMode(ModeFilter mode) {
		if (mode != null && StringUtils.isNotBlank(mode.getMode())) {
			if (operationMode == OperationMode.BLACK_HAT) {
				//only another black hat can kill this black hat
				operationMode = BLACK_HAT_FILTER.equals(mode.getMode()) ? OperationMode.PREDEFINED_WORD : operationMode;
			} else if (operationMode == OperationMode.RANDOM_WORD){
				operationMode = BLACK_HAT_FILTER.equals(mode.getMode()) ? OperationMode.BLACK_HAT : (RANDOM_WORD_FILTER.equals(mode.getMode()) ? OperationMode.RANDOM_WORD : OperationMode.PREDEFINED_WORD);
			} else {
				operationMode = OperationMode.RANDOM_WEATHER_ID;
			}
		}
	}

	private String predefinedWord() {
		return "sos";
	}

	private String randomWord() {
		return RandomStringUtils.randomAlphabetic(3, 7);
	}
	
	private String randomID() {
		String[] data = new String[] {"1796236","1816670","1174872","1185241","1850147","524901","1701668","1792947","1275339","3448439","1795565","1809858","1273294","1791247","1172451","1835848","1815286","2314302","726848"};
		return data[RandomUtils.nextInt(0, data.length)];
	}

}
