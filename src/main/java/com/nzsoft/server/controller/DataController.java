package com.nzsoft.server.controller;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public ServiceData getData(@RequestParam(required = false) String filter) {
		log.info("Received get data request with filter: {}", filter);
		return dataService.getData(filter);
	}

}
