package com.flc.middletier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.flc.backend.SoftwareDAO;
import com.flc.info.SoftwareInfo;

/**
 * The service layer stands on top of it to handle business requirements.
 * @author Federico Latorre
 *
 */
public class SoftwareService {
	
	/**
	 * List that contains all items
	 */
	private List<SoftwareInfo> allSoftwareSales;
//	private List <SoftwareInfo> allGeneralSales;
	/**
	 * Where the items are constructed
	 */
	private SoftwareDAO softwareDataSource;
	
	public SoftwareService(SoftwareDAO source) {
		softwareDataSource = source;
		allSoftwareSales = softwareDataSource.getAllSoftwareInfo();
//		allGeneralSales = softwareDataSource.getGeneralSoftwareInfo();
		
	}

	/**
	 * Method that returns the List with all the items
	 * @return the List allSoftwareSales
	 */
	public List<SoftwareInfo> getAllSoftwareSales() {
		return allSoftwareSales;
	}

	public void setAllSoftwareSales(List<SoftwareInfo> allSales) {
		this.allSoftwareSales = allSales;
	}
	
	/**
	 * Method used in coordination with the Predicate methods to discriminate items of the List according to a set of rules
	 * @param sales the List sales
	 * @param criterion the List with selected characteristics
	 * @return a List
	 */
	public List<SoftwareInfo> getListFromCriterion (List<SoftwareInfo> sales, Predicate<SoftwareInfo> criterion) {
		List<SoftwareInfo> selection = new ArrayList<SoftwareInfo>();
		
		for (SoftwareInfo soft : sales ) {
			if (criterion.test(soft)) {
				selection.add(soft);
			}
		}
		return selection;
	}

//	public List <SoftwareInfo> getAllGeneralSales() {
//		return allGeneralSales;
//	}
//
//	public void setGeneralSales(List <SoftwareInfo> allGeneralSales) {
//		this.allGeneralSales = allGeneralSales;
//	}
	
//	public Set<String> getSoftwareBySystem(String value) {
//		Set<String> temp = new HashSet<String>();
//		if (new SoftwareInfo().getSystem().equals(value)) {
//			for (SoftwareInfo sales: allSoftwareSales) {
//				temp.add(sales.getGame());
//			}
//		}
//		return temp;
//	}
	
}
