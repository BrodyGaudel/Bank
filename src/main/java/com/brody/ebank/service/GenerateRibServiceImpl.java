package com.brody.ebank.service;

/**
 * @author brody gaudel
 * This class essentially contains the functionality 
 * that automatically generates the bank account statement (bank details) (RIB In French)
 *  of a customer's bank account.
 */

import java.util.List;

import org.springframework.stereotype.Service;

import com.brody.ebank.entities.Compter;
import com.brody.ebank.repositories.CompterRepository;

@Service
public class GenerateRibServiceImpl implements GenerateRibService {
	
	private CompterRepository compterRepository;
	
	public GenerateRibServiceImpl(CompterRepository compterRepository) {
		this.compterRepository = compterRepository;
	}



	@Override
	public String generate() {
		
		try {
			List<Compter> compters = compterRepository.findAll();
			if(compters.isEmpty()) {
				Compter compter = new Compter((long) 99999);
				
				return generateByCompter(compter);
			}
			else {
				Compter compter = compters.get(compters.size()-1);
				compterRepository.deleteById(compter.getId()); 
				return generateByCompter(compter);
			}	
		}catch(Exception e) {
			return null;
		}
	}
	
	private String generateByCompter(Compter compter) {
		Long cpt = compter.getId()+1;
		Compter compt = new Compter(cpt);
		compterRepository.save(compt);
		String head = "20442044";
		String body = cpt.toString();
		return head+body;
	}

}
