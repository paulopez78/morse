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

     private static void nextCode(int index, int offset, String word, String sentence) {
         boolean outOfBounds = index + offset > text.length();
         if (dictionary.contains(word)) {
             sentence = sentence + word + " ";
             word = "";
             if (outOfBounds) {
                 System.out.println(sentence.trim());
             }
         }

         if (outOfBounds || !anyWordStartsWith(word)) {
             return;
         }
         else {
             String code = text.substring(index, index + offset);
             if (codes.containsKey(code)) {
                 nextCode(index + offset, 1, word.concat(codes.get(code)), sentence);
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