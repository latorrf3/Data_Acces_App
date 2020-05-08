package com.flc.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flc.info.HardwareInfo;
import com.flc.middletier.Selector;

/**
 * @deprecated
 * @author Federico Latorre
 *
 */
public class HardwareDAOImpl /*implements HardwareDAO*/ {
//	
//	private List<HardwareInfo> allHardware = new ArrayList<HardwareInfo>();
//	
//	private String dbURL = "jdbc:mysql://localhost:3306/mediacreate_db";
//	private String user = "root";
//	private String pw = "";
//	
//	@Override
//	public List<HardwareInfo> getAllHardwareInfo() {
//		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
//				Statement stmt = conn.createStatement();) {
//			String sql = "SELECT * FROM hard_14_2020";
////			String sql = selectWeek();
////			String sql = new WeekSelection().selectHardwareWeek();
////			String sql = "SELECT * FROM hard_" + (new WeekSelection().selectHardwareWeek()) + "_2020";
//
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				HardwareInfo sales = new HardwareInfo(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
//				allHardware.add(sales);
//			}
//		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
//		}
//		return allHardware;
//	}
//	@Override
//	public void addHardware(HardwareInfo sales) {
//		try(Connection conn = DriverManager.getConnection(dbURL, user, pw);
//				Statement stmt = conn.createStatement();) {
//			
//			allHardware.add(sales);
//		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
//		}
//		
//	}
//	@Override
//	public void updateHardware(HardwareInfo sales) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void deleteHardware(HardwareInfo sales) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public HardwareInfo getHardwareInfoById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	public String selectWeek() {
//		System.out.println("Choose a week, please");
//		Scanner reader = new Scanner(System.in);
//		int selection = reader.nextInt();
//		return "SELECT * FROM hard_" + selection + "_2020";
//	}
	

}
