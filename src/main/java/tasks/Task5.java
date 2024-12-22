package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task5 {

  private final PersonConverter personConverter;

  public Task5(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {

    List<ApiPersonDto> apiPersonDtos = new ArrayList<>(persons.size());

    // Проходим по каждой персоне в списке
    for (Person person : persons) {

      Integer areaId = personAreaIds.get(person.id());

      ApiPersonDto apiPersonDto;
      if (areaId != null) {
        apiPersonDto = personConverter.convert(person, areaId);
      } else {
        apiPersonDto = personConverter.convert(person, null);
      }

      apiPersonDtos.add(apiPersonDto);
    }

    return apiPersonDtos;
  }
}
