package third.task.translite;

import java.util.HashMap;

public class TransliteratorImpl implements Transliterator {
    private HashMap<String,String> map = new HashMap<>();
    public TransliteratorImpl() {
        map.put("А", "A");
        map.put("Б", "B");
        map.put("В", "V");
        map.put("Г", "G");
        map.put("Д", "D");
        map.put("Е", "E");
        map.put("Ё", "E");
        map.put("Ж", "ZH");
        map.put("З", "Z");
        map.put("И", "I");
        map.put("Й", "I");
        map.put("К", "K");
        map.put("Л", "L");
        map.put("М", "M");
        map.put("Н", "N");
        map.put("О", "O");
        map.put("П", "P");
        map.put("Р", "R");
        map.put("С", "S");
        map.put("Т", "T");
        map.put("У", "U");
        map.put("Ф", "F");
        map.put("Х", "KH");
        map.put("Ц", "TS");
        map.put("Ч", "CH");
        map.put("Ш", "SH");
        map.put("Щ", "SHCH");
        map.put("Ы", "Y");
        map.put("Ь", "");
        map.put("Ъ", "IE");
        map.put("Э", "E");
        map.put("Ю", "IU");
        map.put("Я", "IA");
    }
    @Override
    public String transliterate(String source) {
        String[] transliter = source.split("");
        String result = "";
        for (String s : transliter) {
            if (map.containsKey(s)) {
                result += map.get(s);
            } else {
                result += s;
            }
        }
        return result;
    }
}
