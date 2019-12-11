package com.Kh033Java.travelbook.recommendations;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.repository.CityRepository;
import com.Kh033Java.travelbook.repository.NoteRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service("initialize")
public class InitializeData {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    Set <City> cities;

    public InitializeData(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Map<User, Set<City>> initialize(){
        Map<User, Set<City>> data = new HashMap<>();
        Set<User> users = userRepository.findUsersWhoLikedNotes();

        for(User user: users){
            cities = noteRepository.findCitiesByLikedNotes(user.getLogin());
            if(cities != null) {
                data.put(user, cities);
            }
        }
        return data;
    }
}
