package third.task.translite;

public class TransliteratorTest {
    public static void main(String[] args) {
        Transliterator transliterator = new TransliteratorImpl();
        String test1 = transliterator
                .transliterate("А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я");
        System.out.println(test1);
        String test2 = transliterator
                .transliterate("HELLO! ПРИВЕТ! Go, boy!");
        System.out.println(test2);
        String test3 = transliterator
                .transliterate("HELLO ЩЕТИКОВ ИВАН петрович!");
        System.out.println(test3);


    }
}
