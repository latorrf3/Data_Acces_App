package com.flc.process;

import com.flc.backend.GeneralSoftwareDAO;
import com.flc.backend.GeneralSoftwareDAOImpl;
import com.flc.backend.SoftwareDAO;
import com.flc.backend.SoftwareDAOImpl;
import com.flc.frontend.Console;
import com.flc.middletier.GeneralService;
import com.flc.middletier.SoftwareService;

/**
 * It contains the main method of the program
 * @author Federico Latorre
 *
 */
public class Start {

	public static void main(String[] args) throws Exception {
		SoftwareDAO softwareDataSource = new SoftwareDAOImpl();
//		HardwareDAO hardwareDataSource = new HardwareDAOImpl();
		GeneralSoftwareDAO generalDataSource = new GeneralSoftwareDAOImpl();
		SoftwareService soft = new SoftwareService(softwareDataSource);
//		HardwareService hard = new HardwareService(hardwareDataSource);
		GeneralService gen = new GeneralService(generalDataSource);
//		Console display = new Console(soft, hard);
//		Console display = new Console(soft);
		Console display = new Console(soft, gen);
		display.launch();
	}

}
