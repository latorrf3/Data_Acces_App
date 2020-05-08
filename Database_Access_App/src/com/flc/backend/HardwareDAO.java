package com.flc.backend;

import java.util.List;

import com.flc.info.HardwareInfo;

/**
 * @deprecated
 * @author Federico Latorre
 *
 */
public interface HardwareDAO {

	List<HardwareInfo> getAllHardwareInfo();
	
	void addHardware(HardwareInfo sales);
	void updateHardware(HardwareInfo sales);
	void deleteHardware(HardwareInfo sales);
	
	HardwareInfo getHardwareInfoById(Long id);
	
}
