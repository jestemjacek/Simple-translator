/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author Jacek
 *
 */
public class Translator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        
        //AD 1
        String english = polish2English("Path", "kanapka");
        System.out.println(english);

        //AD 2
        String german = polishToGerman("Path","Path", "trzy");
        System.out.println(german);

        //AD 3
        String gerpol = generateGermanToPolishTranslationFile("Path", "Path");
        System.out.println(gerpol);           
        
    }
    
    //AD 1
    static String polish2English(String polish2EnglishDictionaryPath, String polishExpression) throws FileNotFoundException
    {
        
        File file1 = new File(polish2EnglishDictionaryPath);
        Scanner polang = new Scanner(file1);
        while(polang.hasNextLine())
        {
            String pol = polang.nextLine();
            String[] words = pol.split(";");
            if(polishExpression.equals(words[0]))
                return words[1];
        } 
        return null;
    }
    
    //AD 2
    static String polishToGerman(String polish2EnglishDictionaryPath, String english2GermanDictionaryPath, String polishExpression) throws FileNotFoundException
    {
        String englishExpression = polish2English(polish2EnglishDictionaryPath, polishExpression);
        String german = polish2English(english2GermanDictionaryPath, englishExpression);
        return german;
    }
    
    //AD 3
    static String generateGermanToPolishTranslationFile(String polish2EnglishDictionaryPath, String english2GermanDictionaryPath) throws FileNotFoundException
    {
        Map<String, String> pol2Eng = getDictionary(polish2EnglishDictionaryPath);
        Map<String, String> eng2Ger = getDictionary(english2GermanDictionaryPath);
        File gerToPolDict = new File("ger2Pol.txt");
        PrintWriter writer = new PrintWriter(gerToPolDict);
        for (Entry<String, String> entry : eng2Ger.entrySet()) 
        {
             String ger = entry.getValue();
             String eng = entry.getKey();
             String exc = "brak tłumaczenia";

             for (Entry<String, String> p2e : pol2Eng.entrySet()) 
             {
                 String engl = p2e.getValue();
                 String pol = p2e.getKey();
                 if(eng.equals(engl))
                        exc = pol;
             }
             writer.println(ger + ";" + exc);
        }
        writer.close();
        return gerToPolDict.getAbsolutePath();
    }
    
    static Map getDictionary(String dictionaryPath) throws FileNotFoundException
    {
        File file1 = new File(dictionaryPath);
        Scanner polang = new Scanner(file1);
        Map<String, String> dictionary = new HashMap<>();
        while(polang.hasNextLine())
        {
            String pol = polang.nextLine();
            String[] words = pol.split(";");
            dictionary.put(words[0], words[1]);
        } 
        return dictionary;
    }
    
    
    
}
