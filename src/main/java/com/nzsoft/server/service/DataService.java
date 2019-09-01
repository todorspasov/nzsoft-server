package com.nzsoft.server.service;

import com.nzsoft.server.model.ModeFilter;
import com.nzsoft.server.model.ServiceData;

public interface DataService {

	ServiceData getData(String teamId);

	void changeMode(ModeFilter mode);
}
