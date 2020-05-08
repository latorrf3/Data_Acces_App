package com.flc.backend;

import java.util.List;

import com.flc.info.GeneralSoftware;

/**
 * DAO stands for Data Access Object. DAO Design Pattern is used to separate the data persistence logic in a separate layer. This way, the service remains completely in dark about how the low-level operations to access the database is done. This is known as the principle of Separation of Logic.
 * @author Federico Latorre
 *
 */
public interface GeneralSoftwareDAO {
	
	/**
	 * It shows all the items of the specified list
	 * @return List with all the items
	 */
	List<GeneralSoftware> getGeneralSoftwareInfo();
	
	/**
	 * It deletes the duplicate rows of the database
	 * @param generalSoftware an item of the List allGeneralSoftware
	 * @return List without the duplicates
	 */
	List<GeneralSoftware> deleteGeneralSoftware(GeneralSoftware generalSoftware);

}
