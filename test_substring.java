
public class test_substring{
public static void main(String[] args) {

    String text = "ABAAABCD";
    String templ = "ABC";

    System.out.println("Алгоритм Кнута-Морриса-Пратта");
    substring.KMP(text, templ);
    System.out.println("Алгоритм Рабина-Карпа");
    substring.RB(templ, text, 101);

    char txt[] = "ABAAABCD".toCharArray();
    char pat[] = "ABC".toCharArray();
    System.out.println("Алгоритм Бойера-Мура");
    substring.search(txt, pat);

    }
}