package tasks;

import common.Area;
import common.Person;

import java.util.*;

public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Set<String> PersonsRegions = new HashSet<>();

    Map<Integer, String> areaMap = new HashMap<>();
    for (Area area : areas) {
      areaMap.put(area.getId(), area.getName());
    }

    for (Person person : persons) {
      Set<Integer> regionIds = personAreaIds.get(person.id());
      if (regionIds != null) {
        for (Integer regionId : regionIds) {
          String RegionName = areaMap.get(regionId);
          if (RegionName != null) {
            PersonsRegions.add(person.firstName() + " - " + RegionName);
          }
        }
      }
    }

    return PersonsRegions;
  }
}
