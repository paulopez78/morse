 package morse;

 import java.io.IOException;
 import java.nio.charset.Charset;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.*;

 public class MorseDecoder {
     private static String text = ".--..-..-.-.-----.-----....--...-.-.-..-....--.-......----.";
     private static Map<String,String> codes;
     private static List<String> dictionary;

     public static void main(String[] args) throws IOException {
         codes = createCodes();
         dictionary = readDictionary("/users/pau/morse/src/main/resources/dictionary.txt");
         generateItem(0, 1, "", "");
     }

    private static void generateItem(int index, int length, String word, String sentence) {
        if (dictionary.contains(word)) {
            sentence = sentence + " " + word;
            word = "";
        }

        boolean outOfBounds = index + length > text.length();
        if (outOfBounds || !anyWordStartsWith(word)) {
            if (outOfBounds)
            {
                System.out.println(sentence);
            }
            return;
        }
        else
        {
            String code = text.substring(index, index + length);
            if (codes.containsKey(code)) {
                generateItem(index + length, 1, word.concat(codes.get(code)), sentence);
                generateItem(index, length + 1, word, sentence);
            }
        }
    }

    private static boolean anyWordStartsWith(String prefix)
    {
        return dictionary.stream().anyMatch(s -> s.startsWith(prefix));
    }

    private static List<String> readDictionary(String fileName) throws IOException
    {
        return Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
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