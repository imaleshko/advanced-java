package org.maleshko;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    record User(String id, String name, String role) {
    }

    static void main() {
        List<String> userNames = List.of("Іван", "Марія", "Олександр", "Олена", "Іван");

        List<String> resultUpper1 = userNames.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(resultUpper1);

        List<String> resultUpper2 = userNames.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println(resultUpper2);

        Set<String> resultUpperSet = userNames.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        System.out.println(resultUpperSet);

        List<User> users = List.of(
                new User("1", "Іван", "Admin"),
                new User("2", "Марія", "Guest"),
                new User("3", "Олександр", "Guest"),
                new User("4", "Олена", "Guest"),
                new User("5", "Іван", "Admin")
        );

        Map<String, String> resultMap = users.stream()
                .collect(Collectors.toMap(User::id, User::name));

        System.out.println(resultMap);

        Map<String, List<User>> resultRolesMap = users.stream()
                .collect(Collectors.groupingBy(User::role));

        System.out.println(resultRolesMap);

        Map<String, Long> resultRolesMapCount = users.stream()
                .collect(Collectors.groupingBy(User::role, Collectors.counting()));

        System.out.println(resultRolesMapCount);

        Map<String, Map<String, String>> resultRolesMapIdName = users.stream()
                .collect(Collectors.groupingBy(User::role, Collectors.toMap(User::id, User::name)));

        System.out.println(resultRolesMapIdName);

        String resultString = users.stream()
                .map(User::name)
                .collect(Collectors.joining(", "));

        System.out.println(resultString);
    }
}
