package testing;

import database.FileManager;
import utilities.UserIO;
import java.io.IOException;

public class FileOpeningTest
{
    public static void main(String[] args)
    {
        UserIO.displayTitleAndBody("Directory Selection", "Please enter the name of the required directory.");

        String dirSelection = UserIO.getInput();

        UserIO.clearScreen();

        String[] fileList = FileManager.listFilesFromJSDirectory(dirSelection);

        UserIO.displayTitle("Files inside \"" + dirSelection + "\"");

        for (String name: fileList)
        {
            UserIO.displayBody(name);
        }

        Boolean fileFound = false;

        do {
            UserIO.displayBody("Please enter the selected file name.");

            String fileName = UserIO.getInput();

            if (FileManager.checkFileExists(dirSelection, fileName))
            {
                UserIO.displayBody("Opening file...");

                try
                {
                    FileManager.openFileFromJS(dirSelection, fileName);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                fileFound = true;
            } else
            {
                UserIO.displayBody("File not found, please try again.");
            }
        } while (fileFound == false);
    }
}
