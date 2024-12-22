package tasks;

import common.Person;
import common.PersonService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    // Получаем Set<Person> из сервиса
    Set<Person> persons = personService.findPersons(personIds);

    // мы бежим по set-у и преобразуем его в map это занимает O(n), где n - кол-во пользователей
    Map<Integer, Person> personMap = persons.stream()
            .collect(Collectors.toMap(Person::id, person -> person));

    // мы бежим по personIds и по id находим пользователя после этого конвертируем в лист и возвращаем O(n)
    return personIds.stream()
            .map(personMap::get)
            .collect(Collectors.toList());
  }
}
//Итоговая асимптотика O(n) + O(n) = O(n)