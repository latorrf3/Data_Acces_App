package com.flc.frontend;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
import java.util.List;
//import java.util.Optional;
import java.util.Scanner;
//import java.util.Set;

import com.flc.backend.GeneralSoftwareDAOImpl;
import com.flc.backend.SoftwareDAOImpl;
import com.flc.files.Writer;
import com.flc.info.GeneralSoftware;
import com.flc.info.SoftwareInfo;
import com.flc.middletier.GeneralService;
import com.flc.middletier.SoftwareService;

/**
 * it deals with the user interface to perform different tasks in the database mediacreate_db 
 * @author Federico Latorre
 *
 */
public class Console {

	/**
	 * it controls the switch selection in the method showSoftwareBySystem()
	 * @author Federico Latorre
	 * @deprecated
	 */
	enum Sys {NSW, PS4, XB1, DS /*3DS*/};// 3DS is not a valid value for an Enum because it starts with a number
	/**
	 * it controls the decision-chart of the user (Y: yes; N: no)
	 * @author Federico Latorre
	 *
	 */
	enum Answer {Y, N};
	
	private SoftwareService softwareService;
	private GeneralService generalService;
//	private HardwareService hardwareService;
	private Scanner reader = new Scanner (System.in);
	
//	/**
//	 * @param service
//	 */
//	public Console(SoftwareService softwareService, HardwareService hardwareService) {
//		this.softwareService = softwareService;
//		this.hardwareService = hardwareService;
//		
//	}
	
//	/**
//	 * @param softwareService
//	 */
//	public Console(SoftwareService softwareService) {
//		this.softwareService = softwareService;
//	}

	/**
	 * @param softwareService object of the class SoftwareService
	 * @param generalService object of the class general Service
	 */
	public Console(SoftwareService softwareService, GeneralService generalService) {
//		super();
		this.softwareService = softwareService;
		this.generalService = generalService;
	}

	public void launch() throws Exception {
		navigation();
	}
	
//	private void navigation1() {
//		System.out.println("Choose a week, please.");
//		int selection = reader.nextInt();
//		
//		switch (selection) {
//		case 13:
//		}
//	}
	/**
	 * navigation method: it manages the user interface to select the desired option by choosing it typing the corresponding number
	 * @throws Exception related to the Enum Sys and the method ShowSoftwareByGame()
	 */
	private void navigation() throws Exception {
		System.out.println("[1] Show software sales info 	[2] Add new software sales  	     [3] Edit a software sales entry\n"
				+ 		   "[4] Show software sakes by Id  	[5] Show sales by System    	     [6] Delete software sales by Id\n"
				+ 		   "[7] Show sales by Game*         [8] Show the main database  	     [9] Updated the general database with a selected week**\n"
				+ 		   "\n[Else] Exit");
		int selection = reader.nextInt();
		switch (selection) {
		case 1:
			showAllSoftwareSales();// it works
//			optionalWriteData();
			break;
		case 2:
			addNewSoftware();// it works
			break;
		case 3:
			editSoftware();// it works
			break;
		case 4:
			showSoftwareById();// it works
			break;
		case 5:
			showSoftwareBySystem();// it works
//			optionalWriteData();
			break;
		case 6:
			deleteSoftwareByID();// it works
			break;
		case 7:
			ShowSoftwareByGame();// Working progress
			break;
		case 8:
			showGeneralSoftware();// it works
			break;
		case 9:
			updateAllSoftware();// it works
			break;
		case 10:
			filterGeneralSoftware();// Working progress
			break;
		case 11:
			showGeneralSoftwareBySystem();// it works
			break;
		case 12:
			ShowGeneralSoftwareByGame();// Working progress
			break;
		default:
			System.out.println("Closing program");
//			case 5:
//			writeHardwareData();// it works
//			break;
//		case 6:
//			writeAllData();// it works
//			break;
//			case 2:
//			showAllHardwareSales();// it works
//			break;
//		case 3:
//			showAllSoftwareSales();// it works
//			showAllHardwareSales();// it works
//			break;			
		}
	}
	
	/**
	 * it prints all the software sales of a selected week without discrimination
	 * it uses List interface and lambda functions
	 */
	private void showAllSoftwareSales() {
		List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
		moreSoftwareSales.forEach(softwareSales -> System.out.println(softwareSales));
		
//		----------------- Print feature ------------------
		System.out.println("Do you want to print the data? [y] yes; [n] no");
		reader.nextLine();
		String answer = reader.nextLine();
		
		answer = answer.toUpperCase();
		Answer choosedAnswer = Answer.valueOf(answer);
		
		switch (choosedAnswer) {
		case Y:
			new File("sales").mkdir();
			System.out.println("Choose a name for the txt file, please.");
//			reader.nextLine();
			String name = reader.nextLine();
			
			File soft = new File ("sales/" + name + ".txt");
//			List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
			
			String formattedString = moreSoftwareSales.toString()
				    .replace(",", "\n")  //remove the commas
				    .replace("[", "")  //remove the right bracket
				    .replace("]", "")  //remove the left bracket
				    .trim();   
			
			Writer writer = new Writer();
			writer.writerInData(soft, formattedString);
			break;
		case N:
			System.out.println("Ok ...");
			break;
		default:
			throw new IllegalArgumentException("Please, type y or n.");
		}
	}
	
//	private void showAllHardwareSales() {
//		List<HardwareInfo> moreHardwareSales = hardwareService.getAllHardwareSales();
//		moreHardwareSales.forEach(hardwareSales -> System.out.println(hardwareSales));
//	}
	
	/**
	 * it adds a new software sales into a selected week by typing its attributes manually one by one
	 */
	private void addNewSoftware() {
		System.out.println("Welcome to addNewSoftware part");
		System.out.println("Please enter: position");
		reader.nextLine();// 
		String pos = reader.nextLine();
		System.out.println("please enter: system");
		String sys = reader.nextLine();
		System.out.println("please enter: game");
		String game = reader.nextLine();
		System.out.println("please enter: genre");
		String genre =reader.nextLine();
		System.out.println("please enter: publisher");
		String publ = reader.nextLine();
		System.out.println("please enter: release date: yyyy MM dd");
		String date = reader.nextLine();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy MM dd"); 
		System.out.println("please enter: price");
		int price = reader.nextInt();
		System.out.println("please enter: sales");
		int sales = reader.nextInt(); 
		System.out.println("please enter: total sales");
		int total = reader.nextInt();
		reader.nextLine();// 
		System.out.println("please enter: sellthrough");
		String sellt = reader.nextLine();
		System.out.println("please enter: comparison");
		Double comp = reader.nextDouble();
		System.out.println("please enter:  week");
		int week = reader.nextInt();
		SoftwareDAOImpl newSoftware = new SoftwareDAOImpl();
//		newSoftware.addSoftware(new SoftwareInfo(reader.nextLine(), reader.nextLine(), reader.nextLine(), reader.nextLine(), reader.nextLine(), LocalDate.now(), reader.nextInt(), reader.nextInt(), reader.nextInt(), reader.nextLine(), reader.nextDouble(), reader.nextInt()));
		newSoftware.addSoftware(new SoftwareInfo(pos, sys, game, genre, publ, LocalDate.parse(date, (formatDate)), price, sales, total, sellt, comp, week));
		System.out.println(new SoftwareInfo(pos, sys, game, genre, publ, LocalDate.parse(date, (formatDate)), price, sales, total, sellt, comp, week));
	}
	
	/**
	 * it shows a software sales in a selected week by typing its Id in the console 
	 */
	private void showSoftwareById() {
		
		System.out.println("Please select a game by Id");
		Long id = reader.nextLong();
//		
//		SoftwareDAOImpl softwareById = new SoftwareDAOImpl();
//		System.out.println(softwareById);
		SoftwareDAOImpl softwareById = new SoftwareDAOImpl();
		
		System.out.println(softwareById.getSoftwareInfoById(id));		
	}
	
	private void editSoftware() {
		
		System.out.println("You are editining a entry");
		System.out.println("Please enter: position");
		reader.nextLine();// 
		String pos = reader.nextLine();
		System.out.println("please enter: system");
		String sys = reader.nextLine();
		System.out.println("please enter: game");
		String game = reader.nextLine();
		System.out.println("please enter: genre");
		String genre =reader.nextLine();
		System.out.println("please enter: publisher");
		String publ = reader.nextLine();
		System.out.println("please enter: release date: yyyy MM dd");
		String date = reader.nextLine();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy MM dd"); 
		System.out.println("please enter: price");
		int price = reader.nextInt();
		System.out.println("please enter: sales");
		int sales = reader.nextInt(); 
		System.out.println("please enter: total sales");
		int total = reader.nextInt();
		reader.nextLine();// 
		System.out.println("please enter: sellthrough");
		String sellt = reader.nextLine();
		System.out.println("please enter: comparison");
		Double comp = reader.nextDouble();
		System.out.println("please enter:  week");
		int week = reader.nextInt();
		System.out.println("Please, select the id of the software you want to edit:");
		Long id = reader.nextLong();
		
//		System.out.println("Please, choose a new name for the game");
//		reader.nextLine();
//		String game = reader.nextLine();
		
		SoftwareDAOImpl editedSoftware = new SoftwareDAOImpl();
//		editedSoftware.updateSoftware(new SoftwareInfo(), id);
//		editedSoftware.addSoftware(new SoftwareInfo(pos, sys, game, genre, publ, LocalDate.parse(date, (formatDate)), price, sales, total, sellt, comp, week));
//		editedSoftware.updateSoftware(new SoftwareInfo(id, pos, sys, game, genre, publ, LocalDate.parse(date, (formatDate)), price, sales, total, sellt, comp, week));
		editedSoftware.updateSoftware(new SoftwareInfo(id, pos, sys, game, genre, publ, LocalDate.parse(date, (formatDate)), price, sales, total, sellt, comp, week));
		System.out.println(new SoftwareInfo(id, pos, sys, game, genre, publ, LocalDate.parse(date, (formatDate)), price, sales, total, sellt, comp, week));
		}
	
	private void deleteSoftwareByID() {
		System.out.println("Please, select the id of the software you want to delete:");
		Long id = reader.nextLong();
		
		SoftwareDAOImpl deletedSoftware = new SoftwareDAOImpl();
		
		deletedSoftware.deleteSoftware(new SoftwareInfo(id));
		
//		System.out.println("Sales entry with id: " + id + "was succesfully deleted from DB");
	}
	
	/*
	 * it shows the software sales of a selected week pertaining to an specific system by typing said system in the console
	 * it uses List interface and lambda functions
	 */
	private void showSoftwareBySystem() throws Exception  {
		
//		System.out.println("Please select a game by system: NSW, PS4, XB1, DS(3DS)");
		System.out.println("Please, select a game by system: NSW, PS4, XB1 or 3DS");
		reader.nextLine();
		String sys = reader.nextLine().toUpperCase();
	
		List<SoftwareInfo> allSales = softwareService.getAllSoftwareSales();
//		------------  version with Enum -----------
//		try {
//			Sys system = Sys.valueOf(sys);
//			
//			switch (system) {
//			case NSW:
//				List<SoftwareInfo> softwareBySystemSW = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_NSW);
//				softwareBySystemSW.forEach(soft -> System.out.println(soft));
//				break;
//			case PS4:
//				List<SoftwareInfo> softwareBySystem4 = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_PS4);
//				softwareBySystem4.forEach(soft -> System.out.println(soft));
//				break;
//			case DS:
//				List<SoftwareInfo> softwareBySystemDS = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_3DS);
//				softwareBySystemDS.forEach(soft -> System.out.println(soft));
//			case XB1:
//				List<SoftwareInfo> softwareBySystem1 = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_XBONE);
//				softwareBySystem1.forEach(soft -> System.out.println(soft));
//				break;
//			}
//		} catch (IllegalArgumentException err) {
//			throw new Exception("You can only choose between NSW, PS4, XB1 and DS(3DS)");
//		}
		
//		--------------------- version without Enum -------------------------
//		List<SoftwareInfo> allSales = softwareService.getAllSoftwareSales();
		if (sys.equals("NSW")) {
			List<SoftwareInfo> softwareBySystem = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_NSW);
			softwareBySystem.forEach(soft -> System.out.println(soft));
			
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
//			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
			
		} else if (sys.equals("PS4")) {
			List<SoftwareInfo> softwareBySystem = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_PS4);
			softwareBySystem.forEach(soft -> System.out.println(soft));
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
//			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
		} else if (sys.equals("3DS")) {
			List<SoftwareInfo> softwareBySystem = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_3DS);
			softwareBySystem.forEach(soft -> System.out.println(soft));
			
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
		} else if (sys.equals("XB1")) {
			List<SoftwareInfo> softwareBySystem = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_XBONE);
			softwareBySystem.forEach(soft -> System.out.println(soft));
			
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
//			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
		} else {
			throw new IllegalArgumentException("Please, type a correct system");
		}
	}
	
	/**
	 * it shows the software sales of a desired week pertaining to an specific game by typing said game in the console
	 */
	private void ShowSoftwareByGame()  {
//		reader.nextLine();
//		String name = Selector.getAName();
		
		List<SoftwareInfo> allSales = softwareService.getAllSoftwareSales();
			
		List<SoftwareInfo> softwareByGame = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_NAME);			
		softwareByGame.forEach(game -> System.out.println(game));
		
//		----------------- Print feature ------------------
		System.out.println("Do you want to print the data? [y] yes; [n] no");
//		reader.nextLine();
		String answer = reader.nextLine();
		
		answer = answer.toUpperCase();
		Answer choosedAnswer = Answer.valueOf(answer);
		
		switch (choosedAnswer) {
		case Y:
			new File("sales").mkdir();
			System.out.println("Choose a name for the txt file, please.");
//			reader.nextLine();
			String name = reader.nextLine();
			
			File soft = new File ("sales/" + name + ".txt");
//			List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
			
			String formattedString = softwareByGame.toString()
				    .replace(",", "\n")  //remove the commas
				    .replace("[", "")  //remove the right bracket
				    .replace("]", "")  //remove the left bracket
				    .trim();   
			
			Writer writer = new Writer();
			writer.writerInData(soft, formattedString);
			break;
		case N:
			System.out.println("Ok ...");
			break;
		default:
			throw new IllegalArgumentException("Please, type y or n.");
		}
	}
	
	/**
	 * it writes the software sales data into a txt file in the folder sales
	 * it uses List interface
	 * @deprecated it has been substituted by optionalWriteData()
	 */
	private void writeSoftwareData() {
		new File("sales").mkdir();
		System.out.println("Choose a name for the txt file, please.");
		reader.nextLine();
		String name = reader.nextLine();
//		int name = reader.nextInt();
		
//		File soft = new File ("sales/soft_" + name + "_2020.txt");
		File soft = new File ("sales/" + name + ".txt");
		List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
//		String dataString = moreSoftwareSales.toString();
		
		String formattedString = moreSoftwareSales.toString()
			    .replace(",", "\n")  //remove the commas
			    .replace("[", "")  //remove the right bracket
			    .replace("]", "")  //remove the left bracket
			    .trim();   
		
		Writer writer = new Writer();
		writer.writerInData(soft, formattedString);
		
	}
	
//	private void writeHardwareData() {
//		new File("sales").mkdir();
//		System.out.println("Choose a name for the txt file, please.");
//		int name = reader.nextInt();
//		
//		File hard = new File ("sales/hard_" + name + "_2020.txt");
//		List<HardwareInfo> moreHardwareSales = hardwareService.getAllHardwareSales();
//		String dataString = "|System |  This Week |  Last Week |  Last Year |     YTD    |  Last YTD  |     LTD     |\n" + moreHardwareSales.toString();
//		
//		Writer writer = new Writer();
//		writer.writerInData(hard, dataString);
//		
//	}
	
//	private void writeAllData() {
//		new File("sales").mkdir();
//		System.out.println("Choose a name for the txt file, please.");
//		int name = reader.nextInt();
//		
//		File all = new File ("sales/all_" + name + "_2020.txt");
//		List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
//		List<HardwareInfo> moreHardwareSales = hardwareService.getAllHardwareSales();
//		String softwareDataString = moreSoftwareSales.toString();
//		String hardwareDataString = moreHardwareSales.toString();
//		String allDataString = softwareDataString +
//				"\n" + " --------\n " + hardwareDataString;
//		
//		Writer writer = new Writer();
//		writer.writerInData(all, allDataString);
//	}
	
	/**
	 * it copies the software sales of a selected week in to a general database
	 * it uses List interface and lambda functions
	 */
	private void updateAllSoftware() {
		SoftwareDAOImpl sinep = new SoftwareDAOImpl();
		List<SoftwareInfo> moreSoftwareSales = sinep.addAllSoftware();
		moreSoftwareSales.forEach(softwareSales -> System.out.println(softwareSales));
	}
	
	/**
	 * it shows all the entries of the "all_software" database
	 * it uses List interface and lambda functions
	 */
	private void showGeneralSoftware() {
		List<GeneralSoftware> generalSoftwareSales = generalService.getAllGeneralSales();
		generalSoftwareSales.forEach(softwareSales -> System.out.println(softwareSales));
		
//		----------------- Print feature ------------------
		System.out.println("Do you want to print the data? [y] yes; [n] no");
		reader.nextLine();
		String answer = reader.nextLine();
		
		answer = answer.toUpperCase();
		Answer choosedAnswer = Answer.valueOf(answer);
		
		switch (choosedAnswer) {
		case Y:
			new File("sales").mkdir();
			System.out.println("Choose a name for the txt file, please.");
//			reader.nextLine();
			String name = reader.nextLine();
			
			File soft = new File ("sales/" + name + ".txt");
//			List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
			
			String formattedString = generalSoftwareSales.toString()
				    .replace(",", "\n")  //remove the commas
				    .replace("[", "")  //remove the right bracket
				    .replace("]", "")  //remove the left bracket
				    .trim();   
			
			Writer writer = new Writer();
			writer.writerInData(soft, formattedString);
			break;
		case N:
			System.out.println("Closing program");
			break;
		default:
			throw new IllegalArgumentException("Please, type y or n.");
		}
	}
	
	/**
	 * it scans all the entries and deletes automatically the ones who have same game and system entries
	 * it uses List interface and lambda functions
	 */
	private void filterGeneralSoftware() {
		GeneralSoftwareDAOImpl updatedGeneralSoft = new GeneralSoftwareDAOImpl();
		updatedGeneralSoft.deleteGeneralSoftware(new GeneralSoftware());
	}
	
	/**
	 * it allows to print the List after its visualization in the console
	 * at the moment it has been implemented in every method that shows a List
	 * @deprecated
	 */
	private void optionalWriteData() {
		System.out.println("Do you want to print the data? [y] yes; [n] no");
		reader.nextLine();
		String answer = reader.nextLine();
		
		answer = answer.toUpperCase();
		Answer choosedAnswer = Answer.valueOf(answer);
		
		switch (choosedAnswer) {
		case Y:
			new File("sales").mkdir();
			System.out.println("Choose a name for the txt file, please.");
//			reader.nextLine();
			String name = reader.nextLine();
			
			File soft = new File ("sales/" + name + ".txt");
			List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
			
			String formattedString = moreSoftwareSales.toString()
				    .replace(",", "\n")  //remove the commas
				    .replace("[", "")  //remove the right bracket
				    .replace("]", "")  //remove the left bracket
				    .trim();   
			
			Writer writer = new Writer();
			writer.writerInData(soft, formattedString);
			break;
		case N:
			System.out.println("Ok ...");
			break;
		default:
			throw new IllegalArgumentException("Please, type y or n.");
		}
	}
	
private void showGeneralSoftwareBySystem() throws Exception  {
		
//		System.out.println("Please select a game by system: NSW, PS4, XB1, DS(3DS)");
		System.out.println("Please, select a game by system: NSW, PS4, XB1 or 3DS");
		reader.nextLine();
		String sys = reader.nextLine().toUpperCase();
	
		List<GeneralSoftware> allSales = generalService.getAllGeneralSales();
//		------------  version with Enum -----------
//		try {
//			Sys system = Sys.valueOf(sys);
//			
//			switch (system) {
//			case NSW:
//				List<SoftwareInfo> softwareBySystemSW = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_NSW);
//				softwareBySystemSW.forEach(soft -> System.out.println(soft));
//				break;
//			case PS4:
//				List<SoftwareInfo> softwareBySystem4 = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_PS4);
//				softwareBySystem4.forEach(soft -> System.out.println(soft));
//				break;
//			case DS:
//				List<SoftwareInfo> softwareBySystemDS = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_3DS);
//				softwareBySystemDS.forEach(soft -> System.out.println(soft));
//			case XB1:
//				List<SoftwareInfo> softwareBySystem1 = softwareService.getListFromCriterion(allSales, SoftwareDAOImpl.IS_XBONE);
//				softwareBySystem1.forEach(soft -> System.out.println(soft));
//				break;
//			}
//		} catch (IllegalArgumentException err) {
//			throw new Exception("You can only choose between NSW, PS4, XB1 and DS(3DS)");
//		}
		
//		--------------------- version without Enum -------------------------
//		List<SoftwareInfo> allSales = softwareService.getAllSoftwareSales();
		if (sys.equals("NSW")) {
			List<GeneralSoftware> softwareBySystem = generalService.getGeneralListFromCriterion(allSales, GeneralSoftwareDAOImpl.IS_NSW);
			softwareBySystem.forEach(soft -> System.out.println(soft));
			
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
//			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
			
		} else if (sys.equals("PS4")) {
			List<GeneralSoftware> softwareBySystem = generalService.getGeneralListFromCriterion(allSales, GeneralSoftwareDAOImpl.IS_PS4);
			softwareBySystem.forEach(soft -> System.out.println(soft));
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
//			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
		} else if (sys.equals("3DS")) {
			List<GeneralSoftware> softwareBySystem = generalService.getGeneralListFromCriterion(allSales, GeneralSoftwareDAOImpl.IS_3DS);
			softwareBySystem.forEach(soft -> System.out.println(soft));
			
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
		} else if (sys.equals("XB1")) {
			List<GeneralSoftware> softwareBySystem = generalService.getGeneralListFromCriterion(allSales, GeneralSoftwareDAOImpl.IS_XBONE);
			softwareBySystem.forEach(soft -> System.out.println(soft));
			
//			----------------- Print feature ------------------
			System.out.println("Do you want to print the data? [y] yes; [n] no");
//			reader.nextLine();
			String answer = reader.nextLine();
			
			answer = answer.toUpperCase();
			Answer choosedAnswer = Answer.valueOf(answer);
			
			switch (choosedAnswer) {
			case Y:
				new File("sales").mkdir();
				System.out.println("Choose a name for the txt file, please.");
//				reader.nextLine();
				String name = reader.nextLine();
				
				File soft = new File ("sales/" + name + ".txt");
//				List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
				
				String formattedString = softwareBySystem.toString()
					    .replace(",", "\n")  //remove the commas
					    .replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();   
				
				Writer writer = new Writer();
				writer.writerInData(soft, formattedString);
				break;
			case N:
				System.out.println("Ok ...");
				break;
			default:
				throw new IllegalArgumentException("Please, type y or n.");
			}
		} else {
			throw new IllegalArgumentException("Please, type a correct system");
		}
	}
	
	/**
	 * it shows the software sales of all_software to an specific game by typing said game in the console
	 */
	private void ShowGeneralSoftwareByGame()  {
//		reader.nextLine();
//		String name = Selector.getAName();
		
		List<GeneralSoftware> allSales =  generalService.getAllGeneralSales();
			
		List<GeneralSoftware> softwareByGame = generalService.getGeneralListFromCriterion(allSales, GeneralSoftwareDAOImpl.IS_NAME);			
		softwareByGame.forEach(game -> System.out.println(game));
		
//		----------------- Print feature ------------------
		System.out.println("Do you want to print the data? [y] yes; [n] no");
		reader.nextLine();
		String answer = reader.nextLine();
		
		answer = answer.toUpperCase();
		Answer choosedAnswer = Answer.valueOf(answer);
		
		switch (choosedAnswer) {
		case Y:
			new File("sales").mkdir();
			System.out.println("Choose a name for the txt file, please.");
//			reader.nextLine();
			String name = reader.nextLine();
			
			File soft = new File ("sales/" + name + ".txt");
//			List<SoftwareInfo> moreSoftwareSales = softwareService.getAllSoftwareSales();
			
			String formattedString = softwareByGame.toString()
				    .replace(",", "\n")  //remove the commas
				    .replace("[", "")  //remove the right bracket
				    .replace("]", "")  //remove the left bracket
				    .trim();   
			
			Writer writer = new Writer();
			writer.writerInData(soft, formattedString);
			break;
		case N:
			System.out.println("Ok ...");
			break;
		default:
			throw new IllegalArgumentException("Please, type y or n.");
		}
	}
	
}
