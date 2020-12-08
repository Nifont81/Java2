package Work3.Sets;
/*
1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
    - Найти список слов, из которых состоит текст (дубликаты не считать);
    - Посчитать сколько раз встречается каждое слово (использовать HashMap);
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] worlds = {"Мороз", "солнце", "день", "чудесный", "дремлешь",
                "друг", "прелестный", "день", "Пора", "красавица",
                "проснись", "солнце", "друг", "проснись", "солнце",
                "негой", "взоры", "день", "Навстречу", "красавица",
                "северной", "солнце", "Звездою", "севера", "явись!"};

        System.out.println("[Исходный массив]\n"+Arrays.toString(worlds)+"\n");

        // Создаем список неповторяющихся слов, сохраняя их порядок;
        LinkedHashSet<String> lhSet = new LinkedHashSet<>();
        Collections.addAll(lhSet, worlds);
        System.out.println("[LinkedHashSet]\n"+lhSet);
//        for (String word : lhSet) {
//            System.out.println(word);
//        }

        // Считаем сколько раз встречается каждое слово, сохраняя порядок;
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (String word : worlds) {
            Integer numberWord = map.get(word);
            if (numberWord == null) map.put(word, 1);
            else map.put(word, ++numberWord);
            //map.put(word, map.getOrDefault(word,1));
        }
        //map.put(word, map.getOrDefault(word,1));

        // Выводим на экран:
        System.out.println("\n[LinkedHashMap]");
        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String,Integer> entry = iter.next();
            System.out.println(entry.getKey()+" - "+entry.getValue()+"р. ");
        }
    }
}

/* Результаты работы программы ------------------------------------------------------------------------------

[Исходный массив]
[Мороз, солнце, день, чудесный, дремлешь, друг, прелестный, день, Пора, красавица, проснись, солнце,
 друг, проснись, солнце, негой, взоры, день, Навстречу, красавица, северной, солнце, Звездою, севера, явись!]

[LinkedHashSet]
[Мороз, солнце, день, чудесный, дремлешь, друг, прелестный, Пора, красавица, проснись, негой, взоры,
 Навстречу, северной, Звездою, севера, явись!]

[LinkedHashMap]
Мороз - 1р.
солнце - 4р.
день - 3р.
чудесный - 1р.
дремлешь - 1р.
друг - 2р.
прелестный - 1р.
Пора - 1р.
красавица - 2р.
проснись - 2р.
негой - 1р.
взоры - 1р.
Навстречу - 1р.
северной - 1р.
Звездою - 1р.
севера - 1р.
явись! - 1р.

--------------------------------------------------------------------------------------------------------------
 */