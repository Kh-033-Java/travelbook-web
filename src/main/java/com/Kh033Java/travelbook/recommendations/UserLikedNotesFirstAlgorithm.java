package com.Kh033Java.travelbook.recommendations;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("algorithm")
public class UserLikedNotesFirstAlgorithm {

    private final InitializeData initializeData;
    private Map<User, LinkedHashMap<User, Integer>> table = new LinkedHashMap<>();

    public UserLikedNotesFirstAlgorithm(InitializeData initializeData) {
        this.initializeData = initializeData;
    }

    public Map<User, Set<City>> getAlgorithm(String login) {
        Map<User, Set<City>> data = initializeData.initialize();
        return priorityTable(data, login);
    }

    private Map<User, Set<City>> priorityTable(Map<User, Set<City>> data, String login) {
        LinkedHashMap<User, Integer> mostSuitableUsers;
        List<City> suitableCities;
        for (Map.Entry<User, Set<City>> user : data.entrySet()) {
            mostSuitableUsers = new LinkedHashMap<>();
            for (Map.Entry<User, Set<City>> anotherUser : data.entrySet()) {
                suitableCities = new ArrayList<>();
                if (user.getKey().equals(anotherUser.getKey())) {
                    continue;
                }
                for (City city : user.getValue()) {
                    for (City anotherCity : anotherUser.getValue()) {
                        if (suitableCities.contains(city)) {
                            break;
                        }
                        if (city.getName().equals(anotherCity.getName())) {
                            suitableCities.add(city);
                        }
                    }
                }
                if (suitableCities.size() != 0) {
                    mostSuitableUsers.put(anotherUser.getKey(), suitableCities.size());

                }
            }
            LinkedHashMap<User, Integer> sorted =
                    mostSuitableUsers.entrySet().stream()
                            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            table.put(user.getKey(), sorted);
        }
        return predictions(data, login);
    }

    private Map<User, Set<City>> predictions(Map<User, Set<City>> users, String login) {
        Map<User, Set<City>> cleanPrediction = new LinkedHashMap<>();
        Set<City> allCities = initializeData.cities;
        Map<String, Integer> diff;
        Set<City> cities;

        for (Map.Entry<User, LinkedHashMap<User, Integer>> currentUser : table.entrySet()) {
            cities = new LinkedHashSet<>();
            diff = new LinkedHashMap<>();
            if (currentUser.getKey().getLogin().equals(login)) {
                for (Map.Entry<User, Integer> currentUserEntry : currentUser.getValue().entrySet()) {
                    Set<City> anotherCities = new HashSet<>();
                    for (Map.Entry<User, Set<City>> user : users.entrySet()) {
                        if (currentUserEntry.getKey().getLogin().equals(user.getKey().getLogin())) {
                            anotherCities.addAll(user.getValue());
                            break;
                        }
                    }
                    for (City city : anotherCities) {
                        int value = 1;
                        if (!diff.containsKey(city.getName())) {
                            diff.put(city.getName(), value);
                        } else {
                            diff.computeIfPresent(city.getName(), (k, v) -> v + 1);
                        }
                    }
                }
                Map<String, Integer> sorted = diff.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                for (Map.Entry<String, Integer> result : sorted.entrySet()) {
                    for (City city : allCities) {
                        if (result.getKey().equals(city.getName())) {
                            cities.add(city);
                        }
                    }
                }
                cleanPrediction.put(currentUser.getKey(), cities);
            }
        }
        return cleanPrediction;
    }
}

