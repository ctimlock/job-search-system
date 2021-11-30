package testing;

import database.FileManager;
import utilities.UserIO;

import java.io.IOException;

public class fileReaderTest
{
    public static void main(String[] args)
    {
        String[] fileTypes = {"pdf", "docx", "doc", "txt"};
        String filePath = FileManager.selectFilePath("Select first item", fileTypes);
        if (filePath != null)
        {
            UserIO.displayTitleAndBody("File Read Test", "Reading content...");

            try
            {
                String textA = FileManager.readFileToString(filePath);
                UserIO.displayBody(textA);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }
}
