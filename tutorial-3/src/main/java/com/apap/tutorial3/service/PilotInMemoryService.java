package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.apap.tutorial3.model.PilotModel;

@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	
	@Override
	public List<PilotModel> getPilotList(){
		return archivePilot;
	}
	
	@Override
	public void deletePilot(PilotModel pilot) {
		archivePilot.remove(pilot);
	}

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel searchedPilot = null;
		for(int i = 0; i < archivePilot.size(); i++) {
			if(licenseNumber.equalsIgnoreCase(archivePilot.get(i).getLicenseNumber())) {
				searchedPilot = archivePilot.get(i);
			}
		}
		return searchedPilot;
	}
	
	@Override
	public PilotModel getPilotDetailByIdNumber(String idNumber) {
		// TODO Auto-generated method stub
		PilotModel searchedPilot = null;
		for(int i = 0; i < archivePilot.size(); i++) {
			if(idNumber.equalsIgnoreCase(archivePilot.get(i).getId())) {
				searchedPilot = archivePilot.get(i);
			}
		}
		return searchedPilot;
	}
}
