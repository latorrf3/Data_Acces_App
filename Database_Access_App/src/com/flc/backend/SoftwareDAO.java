package com.flc.backend;

import java.util.List;
//import java.util.Optional;

import com.flc.info.SoftwareInfo;

/**
 * DAO stands for Data Access Object. DAO Design Pattern is used to separate the data persistence logic in a separate layer. This way, the service remains completely in dark about how the low-level operations to access the database is done. This is known as the principle of Separation of Logic.
 * @author Federico Latorre
 *
 */
public interface SoftwareDAO {
	
	/**
	 * It shows all the items of the specified list
	 * @return the List allSoftware
	 */
	List<SoftwareInfo> getAllSoftwareInfo();
//	List<SoftwareInfo> getGeneralSoftwareInfo();
	
	/**
	 * It adds a new entry to the List
	 * @param sales an item of the List allSoftwareSales
	 */
	void addSoftware(SoftwareInfo sales);
	/**
	 * It can edit an entry of the List
	 * @param sales an item of the List allSoftwareSales
	 */
	void updateSoftware(SoftwareInfo sales);
	/**
	 * It deletes an entry of the List
	 * @param sales an item of the List allSoftwareSales
	 */
	void deleteSoftware(SoftwareInfo sales);
	/**
	 * It shows an entry of the list by attribute id
	 * @param id the id attribute of an item of the List allSoftwareSales
	 * @return an item of the List allSoftware
	 */
	SoftwareInfo getSoftwareInfoById(Long id);
//	List<SoftwareInfo> getSoftwareByGame(String game);
	
	/**
	 * It copies the entries of a List too  another one, while reshaping the size of the List according the new ones
	 * @return the List allSoftwareSales
	 */
	List<SoftwareInfo> addAllSoftware();
	
//	Optional<SoftwareInfo> getSoftwareInfoById(Long id);
//	List<SoftwareInfo> getSoftwareInfoBySystem(String system);

}
