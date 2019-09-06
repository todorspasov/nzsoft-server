package com.nzsoft.server.controller;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nzsoft.server.model.ModeFilter;
import com.nzsoft.server.model.ServiceData;
import com.nzsoft.server.service.DataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/data")
public class DataController {

	private DataService dataService;

	@Autowired
	public DataController(DataService dataService) {
		this.dataService = Validate.notNull(dataService);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ServiceData getData(@RequestParam(required = false) String teamId) {
		log.info("Received get data request by team: {}", teamId);
		return dataService.getData(teamId);
	}

	@RequestMapping(path="/mode", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
	@ResponseStatus(HttpStatus.OK)
	public String getMode() {
		log.info("Retrieving mode");
		return dataService.getMode().name();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changeMode(@RequestBody(required = true) ModeFilter mode) {
		log.info("Received change mode request with filter: {}", mode);
		dataService.changeMode(mode);
	}
}
