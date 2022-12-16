/**
 * @author brody gaudel
 * This class essentially contains the functionality
 * that automatically generates the bank account statement (bank details) (RIB In French)
 *  of a customer's bank account.
 */

package com.brody.ebank.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.brody.ebank.entities.Compter;
import com.brody.ebank.repositories.CompterRepository;

@Service
@Slf4j
public class GenerateRibServiceImpl implements GenerateRibService {
	
	private CompterRepository compterRepository;
	
	public GenerateRibServiceImpl(CompterRepository compterRepository) {
		this.compterRepository = compterRepository;
	}


	/**
	 * Generate RIB Automatically
	 * @return RIB type String
	 */
	@Override
	public String generate() {
		log.info("In generate()");
		try {
			List<Compter> compters = compterRepository.findAll();
			if(compters.isEmpty()) {
				Compter compter = new Compter((long) 99999);

				log.info("RIB Generated");
				return generateByCompter(compter);
			}
			else {
				Compter compter = compters.get(compters.size()-1);
				compterRepository.deleteById(compter.getId());
				log.info("RIB Generated");
				return generateByCompter(compter);
			}	
		}catch(Exception e) {
			log.error("RIB Not Generated Return Null Due To An Exception :"+e);
			return null;
		}
	}

	/**
	 * Save the last rib generated
	 * @param compter type of Compter
	 * @return String
	 */
	private String generateByCompter(Compter compter) {
		log.info("In generateByCompter()");
		Long cpt = compter.getId()+1;
		Compter compt = new Compter(cpt);
		compterRepository.save(compt);
		String head = "20442044";
		String body = cpt.toString();
		log.info("RIB Generated");
		return head+body;
	}

}
