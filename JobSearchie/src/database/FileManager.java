package database;

import utilities.UserIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author Charlie Timlock
 * @version 2.0
 * This class handles file transfer for the Job Searchie system, and allows for uploading and downloading files from the
 * Job Searchie file storage system.
 */

public abstract class FileManager
{
    private static final String FILE_ROOT_DIRECTORY = "JobSearchie/database/fileStorage/";


    /**
     * This method checks whether the requested file exists within the directory provided.
     * @param directory The directory to be accessed within the JS file storage.
     * @param fileName The file name, including the extension.
     * @return Returns true when the file exists.
     */
    public static Boolean checkFileExists(String directory, String fileName)
    {
        File target = new File(FILE_ROOT_DIRECTORY + directory + "/" + fileName);
        return target.exists();
    }

    /**
     * This method returns an array of all files within a given directory in the JobSearchie fileStorage.
     * @param directory The selected directory.
     * @return The names of all files inside the given directory, as an array of Strings.
     */
    public static String[] listFilesFromJSDirectory(String directory)
    {
        File folder = new File(FILE_ROOT_DIRECTORY + directory + "/");
        return folder.list();
    }

    /**
     * This method will copy a file from a given path into a specified directory in the JobSearchie fileStorage directory.
     * @param directory The selected directory.
     * @param srcPath The path of the target file.
     * @param newFileName The selected name for the new file, excluding the file extension.
     */
    public static void moveFileToJSStorage(String directory, String srcPath, String newFileName) throws IOException
    {
        if (srcPath != null && directory != null)
        {
            File src = new File(srcPath);
            int index = src.getName().lastIndexOf('.');
            newFileName += src.getName().substring(index);
            File dest = new File(FILE_ROOT_DIRECTORY + directory + "/" + newFileName);
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * This method locates a file withing a JobSearchie file storage directory, and opens it if it is available.
     * @param directory The directory to be accessed within the JS file storage.
     * @param fileName The file name, including the extension.
     * @throws IOException Will throw if the file is not found.
     */
    public static void openFileFromJS(String directory, String fileName) throws IOException
    {
        File f = new File(FILE_ROOT_DIRECTORY + directory + "/" + fileName);
        if (f.exists())
        {
            Desktop.getDesktop().open(f);
        }
    }

    /**
     * This method will request that the user selects a file from their local environment, and will then copy that file
     * into a specified directory in the JobSearchie fileStorage directory.
     * @param directory The selected directory.
     * @param dialogueTitle The dialogue box's title.
     * @param newFileName The selected name for the new file, excluding the file extension.
     * @param fileTypes A whitelist of selectable file types. Any file type not in this list will not appear inside the selection window.
     * @throws IOException Will throw if the file is not found.
     */
    public static void selectFileAndMoveToJSStorage(String directory, String dialogueTitle, String newFileName, String... fileTypes) throws IOException
    {
            FileManager.moveFileToJSStorage(directory, FileManager.selectFilePath(dialogueTitle, fileTypes), newFileName);
    }

    /**
     * This method requests the user to select a file in their local environment, and returns the path.
     * Example: selectFilePath("Select a File", {"txt", "pdf"})
     * @param dialogueTitle The dialogue box's title.
     * @param fileTypes A whitelist of selectable file types. Any file type not in this list will not appear inside the selection window.
     * @return The selected file's location, as a String.
     */
    public static String selectFilePath(String dialogueTitle, String[] fileTypes)
    {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle(dialogueTitle);
        String description = "." + String.join(", .", fileTypes);
        FileNameExtensionFilter restrict = new FileNameExtensionFilter(description, fileTypes);
        fileChooser.addChoosableFileFilter(restrict);
        int save = fileChooser.showOpenDialog(null);
        if (save == JFileChooser.APPROVE_OPTION)
        {
            // set the label to the path of the selected file
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        // if the user cancelled the operation
        else
        {
            return null;
        }
    }
}
