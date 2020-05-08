package com.flc.backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
import java.util.function.Predicate;

import com.flc.info.SoftwareInfo;
import com.flc.middletier.Selector;

/**
 * Implementation of the SoftwareDAO
 * @author Federico Latorre
 *
 */
public class SoftwareDAOImpl implements SoftwareDAO {
	
	/**
	 * List that will contains all the entries
	 */
	private List<SoftwareInfo> allSoftware = new ArrayList<SoftwareInfo>();
	
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
//	private static String game  = Selector.getAName();
//	private String sql = "SELECT * FROM all_software";
//	private String selectDB = "soft_" + Selector.selectAWeek() + "_2020";
	/**
	 * It contains the name of the database. It calls a static method to input the desired database
	 */
	private String selectDB = Selector.selectDB();
	
	/**
	 * SQL instructions for the method getAllSoftwareInfo() 
	 */
	private String getAllQuery = "SELECT * FROM " + selectDB;
	/**
	 * SQL instructions for the method addSoftware() 
	 */
	private String AddQuery = "INSERT INTO " + selectDB + " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	/**
	 * SQL instructions for the method deleteSoftware() 
	 */
	private String deleteQuery = "DELETE FROM " + selectDB + "\nWHERE id = ?;";
	/**
	 * SQL instructions for the method updateSoftware() 
	 */
	private String updateQuery = "UPDATE " + selectDB + "\nSET pos = ?, system = ?, game = ?, genre = ?, publisher = ?, release_date = ?, price = ?, sales = ?, total_sales = ?, sellthrough = ?, comparison = ?, week = ?\nWHERE id = ?;";
	/**
	 * SQL instructions for the method getSoftwareInfoById() 
	 */
	private String idQuery = "SELECT * FROM " + selectDB + " WHERE id = ?";
	
	@Override
	public List<SoftwareInfo> getAllSoftwareInfo() {
		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
				Statement stmt = conn.createStatement();) {
//			String sql = "SELECT * FROM all_software";
//			String sql = "SELECT * FROM soft_14_2020";
//			WeekSelection week = new WeekSelection();
//			String sql = new WeekSelection().selectSoftwareWeek();
//			String sql = "SELECT * FROM soft_" + (new WeekSelection().selectSoftwareWeek()) + "_2020";
//			String sql = "SELECT * FROM " + WeekSelection.selectDB();
//			String sql = selectWeek();
			ResultSet rs = stmt.executeQuery(getAllQuery);
			
			while (rs.next()) {
				SoftwareInfo sales = new SoftwareInfo(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getDouble(12));
				allSoftware.add(sales);
			}
		} catch (SQLException sqlException) {
				sqlException.printStackTrace();
		}
		return allSoftware;		
	}

	@Override
	public void addSoftware(SoftwareInfo sales) {

		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
				PreparedStatement prepstmt = conn.prepareStatement(AddQuery)){			
			
			prepstmt.setString(1, sales.getPos());
			prepstmt.setString(2, sales.getSystem());
			prepstmt.setString(3, sales.getGame());
			prepstmt.setString(4, sales.getGenre());
			prepstmt.setString(5, sales.getPublisher());
			prepstmt.setDate(6, Date.valueOf(sales.getReleaseDate()));
			prepstmt.setInt(7, sales.getPrice());
			prepstmt.setInt(8, sales.getSales());
			prepstmt.setInt(9, sales.getTotalSales());
			prepstmt.setString(10, sales.getSellthrough());
			prepstmt.setDouble(11, sales.getComparison());
			prepstmt.setInt(12, sales.getWeek());
			
			prepstmt.executeUpdate();
			conn.close();
			
			System.out.println("Entry sucesfully added");
			
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
		
	}

	@Override
	public void updateSoftware(SoftwareInfo sales) {
//		String query = "UPDATE soft_" + WeekSelection.selectSoftwareWeek() + "_2020\n" +
//						"SET pos = ?, system = ?, game = ?, genre = ?, publisher = ?, release_date = ?, price = ?, sales = ?, total_sales = ?, sellthrough = ?, comparison = ?, week = ?\n" +
//						"WHERE id = ?;";	
				
		try(Connection conn = DriverManager.getConnection(dbURL, user, pw);
				PreparedStatement prepstmt = conn.prepareStatement(updateQuery);) {
					prepstmt.setString(1, sales.getPos());
					prepstmt.setString(2, sales.getSystem());
					prepstmt.setString(3, sales.getGame());
					prepstmt.setString(4, sales.getGenre());
					prepstmt.setString(5, sales.getPublisher());
					prepstmt.setDate(6, Date.valueOf(sales.getReleaseDate()));
					prepstmt.setInt(7, sales.getPrice());
					prepstmt.setInt(8, sales.getSales());
					prepstmt.setInt(9, sales.getTotalSales());
					prepstmt.setString(10, sales.getSellthrough());
					prepstmt.setDouble(11, sales.getComparison());
					prepstmt.setInt(12, sales.getWeek());
					prepstmt.setLong(13, sales.getId());
					
					
					prepstmt.executeUpdate();
					prepstmt.close();
					
					System.out.println("Entry sucesfully updated");
					
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}		
	}

	@Override
	public void deleteSoftware(SoftwareInfo sales) {
//		String query = "DELETE FROM soft_" + WeekSelection.selectSoftwareWeek() + "_2020\n"
//						+ "WHERE id = ?;";
		
		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
				PreparedStatement prepstmt = conn.prepareStatement(deleteQuery);) {
			
			prepstmt.setLong(1, sales.getId());
			
			prepstmt.executeUpdate();
			prepstmt.close();
			
			System.out.println("Sales entry with id: " + sales.getId() + " was succesfully deleted from DB");
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		
	}
			
    @Override
	public SoftwareInfo getSoftwareInfoById(Long id) {
		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
//				PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM soft_" + WeekSelection.selectSoftwareWeek() + "_2020 WHERE id = ?")
				PreparedStatement prepstmt = conn.prepareStatement(idQuery);) {
			prepstmt.setLong(1, id);
			
			ResultSet rs1 = prepstmt.executeQuery();
			if (rs1.next()) {
				SoftwareInfo sales = new SoftwareInfo(rs1.getLong(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getDate(7).toLocalDate(), rs1.getInt(8), rs1.getInt(9), rs1.getInt(10), rs1.getString(11), rs1.getDouble(12));
				return sales;
			}
			rs1.close();
			prepstmt.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<SoftwareInfo> addAllSoftware() {
		String allQuery = 
				"INSERT INTO all_software (system, game, genre, publisher, release_date, price)\n" +
				"SELECT system, game, genre, publisher, release_date, price FROM " + selectDB + ";";
		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
				Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(allQuery);
			
			// make sure autocommit is off (postgres)
//			conn.setAutoCommit(false);
//
//			Statement stmt1 = conn.createStatement(
//			                   ResultSet.TYPE_SCROLL_INSENSITIVE, //or ResultSet.TYPE_FORWARD_ONLY
//			                   ResultSet.CONCUR_READ_ONLY);
//			
//			ResultSet srs = stmt1.executeQuery("SELECT * FROM all_software;");
			
			
//			ResultSet rs1 = stmt.execute(allQuery);
			
//			while (srs.next()) {
//				SoftwareInfo software = new SoftwareInfo(srs.getString(1), srs.getString(2), srs.getString(3), srs.getString(4), srs.getDate(5).toLocalDate(), srs.getInt(6), srs.getInt(7));
//				
//				allSoftware.add(software);
//				
//				for (int i = 0; i < allSoftware.size(); i++) {
//					for (int j = i + 1; j < allSoftware.size(); j++) {
//						if (allSoftware.get(i) == allSoftware.get((j))) {
//							allSoftware.remove(i);
//						}
//					}
//				}
//			}
			
			return allSoftware;
//			conn.close();

			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return allSoftware;
	}
	
//	@Override
//	public List<SoftwareInfo> getGeneralSoftwareInfo() {
//		try (Connection conn = DriverManager.getConnection(dbURL, user, pw);
//				Statement stmt2 = conn.createStatement();) {
//			ResultSet rs2 = stmt2.executeQuery( "SELECT * FROM all_software");
//			
//			while (rs2.next()) {
//				SoftwareInfo sales = new SoftwareInfo(rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getDate(6).toLocalDate(), rs2.getInt(7));
//				allSoftware.add(sales);
//			}
//			
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		
//		return allSoftware;
//	}
	
	// Collection of statics methods that use the Predicate Interface
	public static final Predicate<SoftwareInfo> IS_NSW = nsw -> nsw.getSystem().equals("NSW");
	public static final Predicate<SoftwareInfo> IS_PS4 = ps4 -> ps4.getSystem().equals("PS4");
	public static final Predicate<SoftwareInfo> IS_3DS = threeds -> threeds.getSystem().equals("3DS");
	public static final Predicate<SoftwareInfo> IS_XBONE = xbone -> xbone.getSystem().equals("XB1");
	
//	public static Predicate<SoftwareInfo> is_equal = item -> item.equals(item);
	
	private static String game = "One Pi";
//	private static String lowerCaseGame = game.toLowerCase();
	public static final Predicate<SoftwareInfo> IS_NAME = name -> name.getGame().contains(game);
	
//	public static final Predicate<SoftwareInfo> IS_NAME = new Predicate<SoftwareInfo>() {		
//	public boolean test(SoftwareInfo soft) {
////		System.out.println("Type the game name");
////		Scanner reader = new Scanner(System.in);
////		reader.nextLine();
////		String name = reader.nextLine();
////		String name = Selector.getAName();
//		
//		boolean game = soft.getGame().contains(name);
//		return game;
//	}
//};

//	public String selectWeek() {
//			System.out.println("Choose a week, please");
//			Scanner reader = new Scanner(System.in);
//			int selection = reader.nextInt();
//			return "SELECT * FROM soft_" + selection + "_2020";
//		}
	
}
