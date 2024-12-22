package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;

public class Task4 {

  private final PersonConverter personConverter;

  public Task4(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons) {
    // Создаем новый список для хранения результатов конвертации
    List<ApiPersonDto> apiPersonDtos = new ArrayList<>(persons.size());

    // Проходим по каждому объекту Person в исходном списке
    for (Person person : persons) {
      ApiPersonDto TransferredObject = personConverter.convert(person);
      apiPersonDtos.add(TransferredObject);
    }

    return apiPersonDtos;
  }
}
