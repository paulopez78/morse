 package morse;

 import java.io.IOException;
 import java.net.URISyntaxException;
 import java.nio.charset.Charset;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.*;

 public class MorseDecoder {
     private final static Map<String,String> codes = createCodes();
     private static String text = ".--..-..-.-.-----.-----....--...-.-.-..-....--.-......----.";
     private static List<String> dictionary;

     public static void main(String[] args) throws IOException, URISyntaxException {
         dictionary = readDictionary("/dictionary.txt");
         nextCode(0, 1, "", "");
     }

     /* Recursive function generating all possible sentences for a morse encoded text.
     The algorithm uses backtracking and cuts the generated tree as soon as possible
     checking the words dictionary for every letter found.*/
     private static void nextCode(int index, int offset, String word, String sentence) {
         boolean outOfBounds = index + offset > text.length();

         //check if valid word and add to the sentence, reset the word for a new search
         if (dictionary.contains(word)) {
             sentence = sentence + word + " ";
             word = "";
             // A valid sentence can be printed
             if (outOfBounds) {
                 System.out.println(sentence.trim());
             }
         }

         // recursion exit condition checking every letter for cutting the recursion tree asap.
         if (!outOfBounds && anyWordStartsWith(word)) {
             String code = text.substring(index, index + offset);
             if (codes.containsKey(code)) {
                 // go to next index with the new word.
                 nextCode(index + offset, 1, word.concat(codes.get(code)), sentence);

                 // go to next offset with the original word.
                 nextCode(index, offset + 1, word, sentence);
             }
         }
     }

    private static boolean anyWordStartsWith(String prefix)
    {
        return dictionary.stream().anyMatch(s -> s.startsWith(prefix));
    }

    private static List<String> readDictionary(String fileName) throws IOException, URISyntaxException {
        Path p = Paths.get(MorseDecoder.class.getResource(fileName).toURI());
        return Files.readAllLines(p, Charset.defaultCharset());
    }

    private static Map<String,String> createCodes()
    {
        Map<String,String> codes = new HashMap<>();
        codes.put(".-","a");
        codes.put("-...","b");
        codes.put("-.-.","c");
        codes.put("-..","d");
        codes.put(".","e");
        codes.put("..-.","f");
        codes.put("--.","g");
        codes.put("....","h");
        codes.put("..","i");
        codes.put(".---","j");
        codes.put("-.-","k");
        codes.put(".-..","l");
        codes.put("--","m");
        codes.put("-.","n");
        codes.put("---","o");
        codes.put(".--.","p");
        codes.put("--.-","q");
        codes.put(".-.","r");
        codes.put("...","s");
        codes.put("-","t");
        codes.put("..-","u");
        codes.put("...-","v");
        codes.put(".--","w");
        codes.put("-..-","x");
        codes.put("-.--","y");
        codes.put("--..","z");
        return codes;
    }
}