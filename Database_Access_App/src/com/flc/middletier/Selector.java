package com.flc.middletier;

import java.util.Scanner;

/**
 * It contains static methods related to different input values
 * @author Federico Latorre
 *
 */
public class Selector {
	private static Scanner reader = new Scanner(System.in);
	private static Scanner superReader = new Scanner(System.in);
	

//	public static int selectSoftwareWeek() {
//		System.out.println("Choose a week for software, please");
//		Scanner reader = new Scanner(System.in);
//		int selection = reader.nextInt();
//		return selection;
//		
//		return "SELECT * FROM soft_" + selection + "_2020";
//		return "SELECT * FROM soft_14_2020, hard_14_2020";
//		return "SELECT * FROM soft_14_2020
//		return "SELECT * FROM soft_" + selection + "_2020";
//	}
	
	/**
	 * It allows the user to select a specific database by typing its name
	 * @return selection an imput from the user
	 */
	public static String selectDB() {
		System.out.println("Select a DB, please");
//		reader.nextLine();
		String selection = reader.nextLine();
//		query = 
		
		return selection;
	}
	
	/**
	 * It allows the user to input a name
	 * @return game a input from the user
	 */
	public static String getAName() {
		System.out.println("Please, type the game you want to find");
//		superReader.nextLine();
		String game = superReader.nextLine();
		
		return game;
	}
	
//	public static int selectHardwareWeek() {
//		System.out.println("Choose a week for hardware, please");
//		Scanner reader = new Scanner(System.in);
//		int selection = reader.nextInt();
////		int selection = 0;
////		int selection = selectWeek();
//		return selection;
////		return "SELECT * FROM hard_" + selection + "_2020";
//	}
	
//	public static String getAQuery() {
//		String sql = "SELECT * FROM soft_" + (new Selector().selectSoftwareWeek()) + "_2020";
//		return sql;
//	}
}
