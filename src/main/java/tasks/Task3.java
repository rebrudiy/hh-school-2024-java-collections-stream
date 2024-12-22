package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    // Создаем список из переданной коллекции
    List<Person> sortedList = new ArrayList<>(persons);

    // Сортируем список
    sortedList.sort(
            Comparator.comparing(Person::secondName) // Сначала по фамилии
                    .thenComparing(Person::firstName) // Затем по имени
                    .thenComparing(Person::createdAt) // И по дате создания
    );

    return sortedList;
  }
}
