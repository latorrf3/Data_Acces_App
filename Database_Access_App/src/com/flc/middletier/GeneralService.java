package com.flc.middletier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.flc.backend.GeneralSoftwareDAO;
import com.flc.info.GeneralSoftware;

/**
 * The service layer stands on top of it to handle business requirements.
 * @author Federico Latorre
 */
public class GeneralService {

	public static Predicate<GeneralSoftware> IS_NAME;
	/**
	 * List that contains all items
	 */
	private List <GeneralSoftware> allGeneralSales;
	/**
	 * Where the items are constructed
	 */
	private GeneralSoftwareDAO generalDataSource;

	public GeneralService(GeneralSoftwareDAO source) {
		generalDataSource = source;
		setAllGeneralSales(generalDataSource.getGeneralSoftwareInfo());
	}
	
	public List <GeneralSoftware> getAllGeneralSales() {
		return allGeneralSales;
	}
	
	public void setAllGeneralSales(List <GeneralSoftware> allGeneralSales) {
		this.allGeneralSales = allGeneralSales;
	}
	
	/**
	 * Method used in coordination with the Predicate methods to discriminate items of the List according to a set of rules
	 * @param allSales the List allGeneralSales
	 * @param criterion the List with selected characteristics
	 * @return a List
	 */
	public List<GeneralSoftware> getGeneralListFromCriterion (List<GeneralSoftware> allSales, Predicate<GeneralSoftware> criterion) {
		List<GeneralSoftware> selection = new ArrayList<GeneralSoftware>();
		
		for (GeneralSoftware generalSoft : allSales ) {
			if (criterion.test(generalSoft)) {
				selection.add(generalSoft);
			}
		}
		return selection;
	}
	
	
}
