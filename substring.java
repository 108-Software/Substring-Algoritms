import java.util.HashMap;

public class substring {

    static int NO_OF_CHARS = 256;

    public static void RB(String pat, String txt, int q){       //метод алгоритма Рабина Карпа

        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < M-1; i++)
            h = (h*NO_OF_CHARS)%q;

        for (i = 0; i < M; i++) {
            p = (NO_OF_CHARS*p + pat.charAt(i))%q;
            t = (NO_OF_CHARS*t + txt.charAt(i))%q;
        }

        for (i = 0; i <= N - M; i++) {
            if ( p == t ) {
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }
                if (j == M)
                    System.out.println("Паттерн возникает при сдвиге " + i + "\n");
            }

            if ( i < N-M ) {
                t = (NO_OF_CHARS*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;


                if (t < 0)
                    t = (t + q);
            }
        }
    }

    static int max (int a, int b) { return (a > b)? a: b; }     //блок реализации алгоритма Бойера-Мура

    static void badCharHeuristic( char []str, int size, int badchar[]) {


        for (int i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;

        for (int i = 0; i < size; i++)
            badchar[(int) str[i]] = i;

    }

    static void search( char txt[],  char pat[]) {
        int m = pat.length;
        int n = txt.length;

        int badchar[] = new int[NO_OF_CHARS];


        badCharHeuristic(pat, m, badchar);

        int s = 0;
        while(s <= (n - m)) {
            int j = m-1;


            while(j >= 0 && pat[j] == txt[s+j])
                j--;


            if (j < 0) {
                System.out.println("Паттерн возникает при сдвиге " + s);

                s += (s+m < n)? m-badchar[txt[s+m]] : 1;

            }

            else
                s += max(1, j - badchar[txt[s+j]]);
        }
    }                                                                   //конец блока алгоритма Бойера-Мура

    public static void KMP(String text, String pattern){        // Функция для реализации алгоритма Kнута-Морриса-Пратта

        if (pattern == null || pattern.length() == 0)
        {
            System.out.println("Паттерн возникает при сдвиге 0");
            return;
        }

        if (text == null || pattern.length() > text.length())
        {
            System.out.println("Паттерн не найден");
            return;
        }
 
        char[] chars = pattern.toCharArray();

        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++)
        {
            int j = next[i + 1];
 
            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }
 
            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }
 
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j))
            {
                if (++j == pattern.length()) {
                    System.out.println("Паттерн возникает при сдвиге " + (i - j + 1) + "\n");
                }
            }
            else if (j > 0)
            {
                j = next[j];
                i--;
            }
        }
    }
}