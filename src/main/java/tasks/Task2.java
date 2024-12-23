package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    // Создаем ArrayList и заполняем
    List<Person> persons = new ArrayList<>(persons1.size() + persons2.size());

    persons.addAll(persons1);
    persons.addAll(persons2);

    // Сортируем по дате создания (createdAt)
    persons.sort(Comparator.comparing(Person::createdAt));

    // Возвращаем первые `limit` элементов
    return persons.subList(0, Math.min(limit, persons.size()));
  }
}
