package com.apap.tutorial4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add (Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	//pilot itu dibikin pas disubmit di addPilot.html trus disimpen di db lewat yang ini
	//@modelattribute buat ngambil data yang dibikin di addPilot
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);	
		List<FlightModel> listOfFlights = pilot.getPilotFlight();
		model.addAttribute("pilot", pilot);
		model.addAttribute("listOfFlights", listOfFlights);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String deleteFlightSubmit(@PathVariable(value = "licenseNumber") String licenseNumber) {
		pilotService.deletePilot(pilotService.getPilotDetailByLicenseNumber(licenseNumber));
		return "delete";
	}
	
	//
	//
	//
	
	//buat naro data pilot yang udah ada ke htmlnya
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		return "updatePilot";
	}
	
	//ini buat ngirim ke servicenya, pilotU itu objek sementara yang gak dimasukin ke db, abis submit ini dijalanin
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.POST)
	private String updatePilotSubmit(@PathVariable(value = "licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilotU) {
		pilotService.updatePilot(licenseNumber, pilotU);
		return "update";
	}
}
