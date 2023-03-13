package Lockers;

public class FileMain {
public static void main(String[] args) {
		
        // Create "main" folder if not present in current folder structure
		FileOperations.createMainFolderIfNotPresent("Files");
		Options.printWelcomeScreen("TekSystems", "Mukundhan V ");
		
		Options.handleWelcomeScreenInput();
	}
}

