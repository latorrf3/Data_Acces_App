package com.flc.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.flc.info.GeneralSoftware;

/**
 * Implementation of the GeneralSoftwareDAO
 * @author Federico Latorre
 *
 */
public class GeneralSoftwareDAOImpl implements GeneralSoftwareDAO {
	
	/**
	 * List that contains all the entries
	 */
	private List<GeneralSoftware> allGeneralSoftware = new ArrayList<GeneralSoftware>();
	
	/**
	 * Address where the database is located
	 */
	private String dbURL = "jdbc:mysql://localhost:3306/mediacreate_db";
	/**
	 * Login name of the database's administrator
	 */
	private String user = "root";
	/**
	 * Password to access the database
	 */
	private String pw = "";

	@Override
	public List<GeneralSoftware> getGeneralSoftwareInfo() {
		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
				Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM all_software");
			
			while (rs.next()) {
				GeneralSoftware software = new GeneralSoftware(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getInt(7));
				allGeneralSoftware.add(software);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return allGeneralSoftware;
	}
	
	@Override
	public List<GeneralSoftware> deleteGeneralSoftware(GeneralSoftware generalSoftware) {// Wprk in progress
		Long idJ = null;
		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
				PreparedStatement prepstmt = conn.prepareStatement("DELETE FROM all_software WHERE id = ?;");) {
			
			for (int i = 0; i < allGeneralSoftware.size(); i++) {
				for (int j = i + 1; j < allGeneralSoftware.size(); j++) {
					if (allGeneralSoftware.get(i).getGame().equals(allGeneralSoftware.get(j).getGame())) {
						idJ = allGeneralSoftware.get(j).getId();
//						return idJ;
					}
				}
			}
			
			prepstmt.setLong(1, 20);
			
			prepstmt.executeUpdate();
			prepstmt.close();
//			if (generalSoftware.getId() == null) {
//				System.out.println("No entry was deleted");
//			} else {
				System.out.println("Sales entry with id: " + idJ + " was succesfully deleted from DB.");
//			}			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return allGeneralSoftware;
		
	}

//	public Long discriminateSoftware() {
//		Long idJ;
//		for (int i = 0; i < allGeneralSoftware.size(); i++) {
//			for (int j = i + 1; j < allGeneralSoftware.size(); j++) {
//				if (allGeneralSoftware.get(i).getGame().equals(allGeneralSoftware.get(j).getGame())) {
//					idJ = allGeneralSoftware.get(j).getId();
//				} else {
//					return null;
//				}
//			}
//		} 
//		return idJ;
//	}
	
	// Collection of statics methods that use the Predicate Interface
	public static final Predicate<GeneralSoftware> IS_NSW = nsw -> nsw.getSystem().equals("NSW");
	public static final Predicate<GeneralSoftware> IS_PS4 = ps4 -> ps4.getSystem().equals("PS4");
	public static final Predicate<GeneralSoftware> IS_3DS = threeds -> threeds.getSystem().equals("3DS");
	public static final Predicate<GeneralSoftware> IS_XBONE = xbone -> xbone.getSystem().equals("XB1");
	
	private static String game = "One Piece";
//	private static String lowerCaseGame = game.toLowerCase();
	public static final Predicate<GeneralSoftware> IS_NAME = name -> name.getGame().contains(game);
	
}
