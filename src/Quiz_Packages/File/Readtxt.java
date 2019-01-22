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




public class Readtxt {
    private String fileName = System.getProperty("user.dir") + "\\src\\Quiz_Packages\\new.txt";
    private FileWriter writer;
    private File file;
    private static Readtxt readFiles = null;


    private Readtxt(){}

    public static Readtxt getInstance(){
        if (readFiles == null){
            readFiles = new Readtxt();
        }
        return readFiles;
    }

    public void setFileName(String fileName){
        if (fileName == null) {
            throw new NullPointerException();
        }
        if (fileName == "" || !fileName.contains(".txt")) {
            throw new IllegalArgumentException();
        }
        this.fileName = fileName;
    }

    
    public void addNewQuestion(List<String> frage)  {
        if (frage == null) throw new NullPointerException();                    //bekomme eine Frage als Liste
        file = new File(fileName);                                              //erstelle neue Datei
        try {
            writer = new FileWriter(file ,true);
            for (int i = 0; i<frage.size(); i++) {
                writer.write(frage.get(i));
                writer.write(System.getProperty("line.separator"));             //schreibe sie in die Datei
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void addStats(List<GetStats> s, String stat) {                       //gleiches Prinzip wie bei der Liste
        try{                                                                    //lösche nur vorher die alten Stats
            PrintWriter pw = new PrintWriter(stat);
            pw.close();
        } catch (Exception e) {}

        if (s.isEmpty()) throw new NullPointerException();
        file = new File(stat);
        try{
            writer = new FileWriter(file, true);
            for (int i = 0; i<s.size(); i++) {
                writer.write(s.get(i).toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
        }

    }






  
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
            StringBuffer sb = new StringBuffer();
            String sep = System.getProperty("line.separator");

            while( (line=br.readLine()) != null )
                temp.add(line+sep) ;
        }
        catch(IOException ex) {
            System.out.println(ex);
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
