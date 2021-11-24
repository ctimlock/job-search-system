package testing;

import java.io.IOException;
import java.util.Random;

public class UploadDocumentTest
{
    public static void main(String[] args)
    {
        String path = "";
        String[] fileTypes = {"pdf", "doc", "docx", "png", "jpg", "jpeg"};
        Random rand = new Random();
        String fileName = String.valueOf(rand.nextInt(99999));
        try
        {
            database.FileManager.selectFileAndMoveToJSStorage("resume", "Select your resume file.", fileName, fileTypes);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
