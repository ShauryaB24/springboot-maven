package com.shaurya.rest.webservices.restful_web_services.user;

import ch.qos.logback.core.model.processor.AppenderRefDependencyAnalyser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static int userCount = 0;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(++userCount, "Shaurya", LocalDate.now().minusYears(23).plusDays(1)));
        users.add(new User(++userCount, "Sahil", LocalDate.now().minusYears(27).minusDays(2)));
        users.add(new User(++userCount, "Satish", LocalDate.now().minusYears(59).plusDays(3)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        users.removeIf(user -> user.getId() == id);

    }


}
