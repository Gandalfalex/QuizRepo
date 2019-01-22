package Quiz_Packages.File;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ICH
 */



// Wir lesen eine Textdatei ein und befüllen die Listen
import Quiz_Packages.Play.GetStats;

import java.io.*;
import java.util.*;


/**
 * This class reads and writes textfiles
 */
public class Readtxt {
    private String fileName = System.getProperty("user.dir") + "\\src\\Quiz_Packages\\new.txt";
    private FileWriter writer;
    private File file;
    private static Readtxt readFiles = null;

    /**
     * private Constructor, Singleton Object
     */
    private Readtxt(){}

    /**
     * static Method that returns one instance of this class
     * @return single Method or creates new one if null
     */
    public static Readtxt getInstance(){
        if (readFiles == null){
            readFiles = new Readtxt();
        }
        return readFiles;
    }

    /**
     *
     * @param fileName sets the Path to the file
     */
    public void setFileName(String fileName){
        if (fileName == null) {
            throw new NullPointerException();
        }
        if (fileName.equals("") || !fileName.contains(".txt")) {
            throw new IllegalArgumentException();
        }
        this.fileName = fileName;
    }

    /**
     *
     * @param frage, ArrayList that contains all Information about the Question
     */
    public void addNewQuestion(List<String> frage)  {
        if (frage == null) throw new NullPointerException();                    //bekomme eine Frage als Liste
        file = new File(fileName);                                              //erstelle neue Datei
        try {
            writer = new FileWriter(file ,true);
            for (String string: frage) {
                writer.write(string);
                writer.write(System.getProperty("line.separator"));             //schreibe sie in die Datei
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param stats List of all achieved stats
     * @param statPath the location of the StatsFile
     */
    public void addStats(List<GetStats> stats, String statPath) {                       //gleiches Prinzip wie bei der Liste
        try{                                                                    //lösche nur vorher die alten Stats
            PrintWriter pw = new PrintWriter(statPath);
            pw.close();
        } catch (Exception e) {}

        if (statPath.isEmpty()) throw new NullPointerException();
        file = new File(statPath);
        try{
            writer = new FileWriter(file, true);
            for (GetStats statObject: stats) {
                writer.write(statObject.toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
        }

    }


    /**
     *
     * @param filePath the Path to the File
     * @return A List of Strings. They will get converted into Frage_Objects later
     */
    public ArrayList<String> readFile(String filePath) {                                    //lesen aus einer Datei, rückgabe als Liste String

        ArrayList<String> temp = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;

        try
        {
            File file = new File(filePath) ;
            fr = new FileReader(file);
            br = new BufferedReader(fr) ;

            String line ;
            StringBuffer stringBuffer = new StringBuffer();
            String sep = System.getProperty("line.separator");

            while( (line=br.readLine()) != null )
                temp.add(line+sep) ;
        }
        catch(IOException ex) {
            System.out.println("cant read");
        }
        finally {
            try {
                if(br!=null) br.close();
                if(fr!=null) fr.close();
            }
            catch(Exception ex) {}
        }
        
    return temp;
    }
    
    
    
}
