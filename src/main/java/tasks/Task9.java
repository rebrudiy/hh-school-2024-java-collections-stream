package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Далее вы увидите код, который специально написан максимально плохо.
Постарайтесь без ругани привести его в надлежащий вид
P.S. Код в целом рабочий (не везде), комментарии оставлены чтобы вам проще понять чего же хотел автор
P.P.S Здесь ваши правки необходимо прокомментировать (можно в коде, можно в PR на Github)
 */
public class Task9 {

  // Костыль, эластик всегда выдает в топе "фальшивую персону".
  // Конвертируем начиная со второй
  //Тут я использовал skip вместо remove, потому что remove модифицирует исходный текст
  public List<String> getNames(List<Person> persons) {
    if (persons.isEmpty()) {
      return Collections.emptyList();
    }
    return persons.stream()
            .skip(1) // Пропускаем первую персону
            .map(Person::firstName)
            .collect(Collectors.toList());
  }


  // Зачем-то нужны различные имена этих же персон (без учета фальшивой разумеется)
  // Ничего не сказано про порядок поэтому просто переведем список фамилий в hash_list
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }


  // Тут фронтовая логика, делаем за них работу - склеиваем ФИО
  //Так как сначало нам нужно secondName потом firstName, потом middleName (так же нужно учесть что какие-то поля могут быть равны null)
  public String convertPersonToString(Person person) {
    List<String> fullName = new ArrayList<>();
    if (person.secondName() != null) fullName.add(person.secondName());
    if (person.firstName() != null) fullName.add(person.firstName());
    if (person.middleName() != null) fullName.add(person.middleName());
    return String.join(" ", fullName);
  }

  // словарь id персоны -> ее имя
  // тут непонятно зачем нужен if, ибо его отсутствие на логику работы никак. И еще у нас для каждой персоны нужна ячейка в map(если нет кучи персон с id null)
  // Поэтому выделим в словарь сразу persons.size() памяти
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap<>(persons.size());
    for (Person person : persons) {
        map.put(person.id(), convertPersonToString(person));
    }
    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  //тут просто оптимизируем с точки зрения асимптотики теперь вместо квадратичной асимптотики у нас линейная
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> set1 = new HashSet<>(persons1);
    for(Person person : persons2) {
      if (!set1.contains(person)) {
        return true;
      }
    }
    return false;
  }

  // Посчитать число четных чисел
  //удаляем ненужные переменные и сразу возращаем результат
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }

  // Загадка - объясните почему assert тут всегда верен
  // Пояснение в чем соль - мы перетасовали числа, обернули в HashSet, а toString() у него вернул их в сортированном порядке
  // может быть тут дело в реализации hash функции так у нас в качестве ключа используется натуральное число похоже выполняется свойство
  // a < b -> hash(a) < hash(b) Видимо поэтому каждый раз у нас в итоге получается отсортированная строка из set
  void listVsSet() {
    List<Integer> integers = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());
    List<Integer> snapshot = new ArrayList<>(integers);
    Collections.shuffle(integers);
    Set<Integer> set = new HashSet<>(integers);
    assert snapshot.toString().equals(set.toString());
  }
}
