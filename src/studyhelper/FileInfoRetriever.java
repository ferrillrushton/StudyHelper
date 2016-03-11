/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studyhelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileInfoRetriever {
    
    private String fileName;
    private Scanner inFile;
    private PrintWriter outFile;
    private File fileDeleter;
    
    public FileInfoRetriever(String file){
        fileName = file;
        inFile = null;
        outFile = null;
    }
    
    public String getFileInfo(){
        String info = "";
        if (!openInFile()){
            return "ERROR";
        }
        while (inFile.hasNextLine())
            info += inFile.nextLine();
        inFile.close();
        return info;
    }
    
    private boolean openInFile(){
        try{
            inFile = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }
    
    private boolean openAppendOutFile(){
        try{
            outFile = new PrintWriter(new FileOutputStream(fileName, true));
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }
    
    private boolean openOutFile(){
        try{
            outFile = new PrintWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }
    
    private void deleteFile(){
        fileDeleter = new File(fileName);
        try{
            fileDeleter.delete();
        }catch (Exception e){
            //Do nothing
        }
    }
    
    public boolean addToFile(String info){
        if (!openAppendOutFile())
            return false;
        
        outFile.println(info);
        outFile.close();
        
        return true;
    }
    
    public boolean writeFile(String info){
        if (!openOutFile())
            return false;
        outFile.println(info);
        outFile.close();
        
        return true;
    }
    
}//End of class FileInfoRetriever
