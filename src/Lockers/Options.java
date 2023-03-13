package Lockers;

import java.util.List;
import java.util.Scanner;

public class Options {
	
	public static void printWelcomeScreen(String appName, String developerName)
	{
		String companyDetails = String.format("Welcome to %s.com.\n" + "This application was developed by %s.\n", appName, developerName);
		String appFunction = "You can use this application to :-\n"+ "\nPlease be careful to ensure the correct filename is provided for searching or deleting files.\n";
		System.out.println(companyDetails);
		System.out.println(appFunction);
	}

	public static void displayMenu() {
		String menu = "\n Select any option number from below and press Enter\n"+ "1) Retrieve all files inside \"Files\" folder\n" +"2) Display menu for File operations\n"+"3) Exit program\n";
		System.out.println(menu);
	}

	public static void displayFileMenuOptions() {
		String fileMenu = "\n Select any option number from below and press Enter\n"+ "1) Add a file to \"Files\" folder\n"+"2) Delete a file from \"Files\" folder\n"+ "3) Search for a file from \"Files\" folder\n" +"4) Show Previous Menu\n";
		System.out.println(fileMenu);
	}
	
	public static void handleWelcomeScreenInput() {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				displayMenu();
				int input = sc.nextInt();

				switch (input) {
				case 1:
					FileOperations.displayAllFiles("Files");
					break;
				case 2:
					handleFileMenuOptions();
					break;
				case 3:
					System.out.println("Program exited successfully.");
					running = false;
					sc.close();
					System.exit(0);
					break;
				default:
					System.out.println("Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleWelcomeScreenInput();
			} 
		} while (running == true);
	}
	
	public static void handleFileMenuOptions() {
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				displayFileMenuOptions();
				FileOperations.createMainFolderIfNotPresent("Files");
				
				int input = sc.nextInt();
				switch (input) {
				case 1:
					// File Add
					System.out.println("Enter the name of the file to be added to the \"Files\" folder");
					String fileToAdd = sc.next();
					
					FileOperations.createFile(fileToAdd, sc);
					
					break;
				case 2:
					// File/Folder delete
					System.out.println("Enter the name of the file to be deleted from \"Files\" folder");
					String fileToDelete = sc.next();
					
					FileOperations.createMainFolderIfNotPresent("Files");
					List<String> filesToDelete = FileOperations.displayFileLocations(fileToDelete, "Files");
					
					String deletionPrompt = "\nSelect index of which file to delete?"
							+ "\n(Enter 0 if you want to delete all elements)";
					System.out.println(deletionPrompt);
				
					int idx = sc.nextInt();
					
					if (idx != 0) {
						FileOperations.deleteFileRecursively(filesToDelete.get(idx - 1));
					} else {
						
						// If idx == 0, delete all files displayed for the name
						for (String path : filesToDelete) {
							FileOperations.deleteFileRecursively(path);
						}
					}
					

					break;
				case 3:
					// File/Folder Search
					System.out.println("Enter the name of the file to be searched from \"Files\" folder");
					String fileName = sc.next();
					
					FileOperations.createMainFolderIfNotPresent("Files");
					FileOperations.displayFileLocations(fileName, "Files");

					
					break;
				case 4:
					// Go to Previous menu
					return;
				default:
					System.out.println("Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println(e.getClass().getName());
				handleFileMenuOptions();
			}
		} while (running == true);
	}
}
