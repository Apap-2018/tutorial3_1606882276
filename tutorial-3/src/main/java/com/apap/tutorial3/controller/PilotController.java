package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add (@RequestParam(value = "id", required = true) String id,
					   @RequestParam(value = "licenseNumber", required = true) String licenseNumber,
					   @RequestParam(value = "name", required = true) String name,
					   @RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}

	@RequestMapping(value= {"/pilot/view/license-number/"})
	public String emptyLicenseNumber() {
		return "error-page-empty";
	}
	
	@RequestMapping(value= {"/pilot/view/license-number"})
	public String emptyLicenseNumber2() {
		return "error-page-empty";
	}
	
	@RequestMapping(value= {"/pilot/view/license-number/{licenseNumber}"})
	public String licenseNumberPath(@PathVariable Optional <String> licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
		
		if(archive != null) {
			model.addAttribute("pilot", archive);
			return "view-pilot";
		}
		
		else {
			return "error-page";
		}
	}
	
	@RequestMapping("/pilot/update/license-number/fly-hour/{newHour}")
	public String emptyChangeHour() {
		return "error-change-empty";
	}
	
	@RequestMapping("/pilot/update/license-number//fly-hour/{newHour}")
	public String emptyChangeHour2() {
		return "error-change-empty";
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{newHour}")
	public String changeHour(@PathVariable String licenseNumber, @PathVariable String newHour, Model model ) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(archive != null) {
			archive.setFlyHour(Integer.parseInt(newHour));
			return "success-change";
		}
		
		else {
			return "error-change";
		}
	}
	
	@RequestMapping("/pilot/delete/id/")
	public String emptyDelete() {
		return "error-delete-empty";
	}
	
	@RequestMapping("/pilot/delete/id")
	public String emptyDelete2() {
		return "error-delete-empty";
	}
	
	@RequestMapping("/pilot/delete/id/{id}")
	public String deletePilot(@PathVariable String id, Model model ) {
		PilotModel archive = pilotService.getPilotDetailByIdNumber(id);
		if(archive != null) {
			pilotService.deletePilot(archive);
			return "success-delete";
		}
		
		else {
			return "error-delete";
		}
	}
}
