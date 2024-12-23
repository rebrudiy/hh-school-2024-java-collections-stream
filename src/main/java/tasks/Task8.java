package tasks;

import common.Person;
import common.PersonService;
import common.PersonWithResumes;
import common.Resume;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Task8 {

  private final PersonService personService;

  public Task8(PersonService personService) {
    this.personService = personService;
  }

  public Set<PersonWithResumes> enrichPersonsWithResumes(Collection<Person> persons) {
    Set<PersonWithResumes> enrichedPersons = new HashSet<>();

    Set<Integer> personIds = new HashSet<>();
    for (Person person : persons) {
      personIds.add(person.id());
    }

    // Получаем резюме всех персон с переданными id
    Set<Resume> allResumes = personService.findResumes(personIds);

    for (Person person : persons) {
      Set<Resume> personResumes = new HashSet<>();
      for (Resume resume : allResumes) {
        if (resume.personId().equals(person.id())) {
          personResumes.add(resume);
        }
      }
      enrichedPersons.add(new PersonWithResumes(person, personResumes));
    }

    return enrichedPersons;
  }
}
