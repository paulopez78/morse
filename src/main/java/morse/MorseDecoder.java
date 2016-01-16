package morse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseDecoder {

    //private static String text = ".--..-..-.-.-----.-----....--...-.-.-..-....--.-......----.";
    private static String text = "....---.-...-";

    private static int MAX_LENGTH = 4;
    private static Map<String,String> codes;
    private static List<String> dictionary;
    public static void main(String[] args) throws IOException
    {
        codes = createCodes();
        dictionary = readDictionary("/users/pau/morse/src/main/resources/dictionary.txt");
        generateItem(0,1,"");
    }

    private static boolean generateItem(int index, int length, String parentItem){
        boolean outOfBounds = index+length > text.length();
        boolean maxLength = length > MAX_LENGTH;

        if (index == 0)
        {
            parentItem = "";
        }

        if (!outOfBounds && !maxLength)
        {
            String item = text.substring(index, index + length);
            if (codes.containsKey(item))
            {
                String decodedItem = codes.get(item).toLowerCase();
                //System.out.println(String.format("\ri:%s, l:%s, item:%s",index,length, decodedItem));

                if (!generateItem(index + length, 1, parentItem.concat(decodedItem)))
                {
                    return generateItem(index,length + 1, parentItem);
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            System.out.println(parentItem);
            return false;
        }
    }

    private static boolean anyWordStartsWith(String path)
    {
        return dictionary.stream().anyMatch(s -> s.startsWith(path));
    }

    private static List<String> readDictionary(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
    }

    private static Map<String,String> createCodes()
    {
        Map<String,String> codes = new HashMap<>();
        codes.put(".-","A");
        codes.put("-...","B");
        codes.put("-.-.","C");
        codes.put("-..","D");
        codes.put(".","E");
        codes.put("..-.","F");
        codes.put("--.","G");
        codes.put("....","H");
        codes.put("..","I");
        codes.put(".---","J");
        codes.put("-.-","K");
        codes.put(".-..","L");
        codes.put("--","M");
        codes.put("-.","N");
        codes.put("---","O");
        codes.put(".--.","P");
        codes.put("--.-","Q");
        codes.put(".-.","R");
        codes.put("...","S");
        codes.put("-","T");
        codes.put("..-","U");
        codes.put("...-","V");
        codes.put(".--","W");
        codes.put("-..-","X");
        codes.put("-.--","Y");
        codes.put("--..","Z");
        return codes;
    }
}