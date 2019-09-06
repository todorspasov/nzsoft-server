package com.nzsoft.server.service;

import com.nzsoft.server.model.ModeFilter;
import com.nzsoft.server.model.ServiceData;

public interface DataService {

	static enum OperationMode {
		RANDOM_WORD,
		PREDEFINED_WORD,
		BLACK_HAT,
		RANDOM_WEATHER_ID
	}

	ServiceData getData(String teamId);

	void changeMode(ModeFilter mode);

	OperationMode getMode();
}
