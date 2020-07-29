
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class DisplayText {
    public String getUserInput(String text) throws IOException {
        toFile(text);
        return getResult(getWords(text));
    }

//check if exist or create file
//get input to file
    public void toFile(String text) {
        String textIn = text;

        try {
            File file = new File("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\input.txt");

            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\input.txt", true));

                file.createNewFile();
                writer.write(textIn);
                writer.close();
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\input.txt", true));

                //Add new line to file
                writer.newLine();
                writer.write(textIn);
                writer.close();
            }
        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

//split to words
    public String[] getWords(String text) {
        String textIn = text;
        textIn = textIn.toLowerCase();
        String[] words = textIn.split("\\W+");
        return words;
    }

    //check if exist or create
    //get output to file
    public String getResult(String[] words) {
        TreeMap<String, Integer> lettermap = new TreeMap<String, Integer>();
        HashMap<String, String> wordmap = new HashMap<String, String>();

        String letter;
        String word;
        String tempWord;
        int n = 0;

//get last letter
        for (int i = 0; i < words.length; i++) {
            letter = String.valueOf(words[i].charAt(words[i].length() - 1));

            if (words[i].matches(".*\\d.*") == false) {
                if (lettermap.containsKey(letter)) {
                    lettermap.put(letter, n++);
                } else {
                    lettermap.putIfAbsent(letter, 1);
                }
            }

            //words -> hashmap
            if (words[i].matches(".*\\d.*") == false) {
                tempWord = " " + words[i] + " ";

                if (wordmap.containsKey(letter)) {
                    word = wordmap.get(letter);
                    word = new StringBuilder().append(word).append(tempWord).toString();
                    wordmap.put(letter, word);
                } else {
                    word = words[i];
                    wordmap.put(letter, word);
                }
            }
        }
        
        
        String result="";
        String tempResult="";
        try {            
            File file = new File("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\output.txt");
            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\output.txt", true));
                file.createNewFile();
                for (Map.Entry<String, Integer> entry : lettermap.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue() + " " + wordmap.get(entry.getKey()) + ",");
                    tempResult=entry.getKey() + " " + entry.getValue() + " " + wordmap.get(entry.getKey()) + ", \n" ;
                    result = new StringBuilder().append(result).append(tempResult).toString();
                }
                writer.close();
            } else {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\output.txt", true));
                writer.newLine();   //Add new line
                for (Map.Entry<String, Integer> entry : lettermap.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue() + " " + wordmap.get(entry.getKey()) + ",");
                    tempResult=entry.getKey() + " " + entry.getValue() + " " + wordmap.get(entry.getKey()) + ", \n";
                    result = new StringBuilder().append(result).append(tempResult).toString();
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            
        }return result;
    }
    
            public String getPreviousInput() throws IOException{
        String content = "";
 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\input.txt") ) );
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        return content;
        }
            public String getPreviousOutput() throws IOException{
        String content = "";
 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get("C:\\Documents\\NetBeansProjects\\WebApplication\\src\\main\\java\\output.txt") ) );
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        return content;
        }
}
