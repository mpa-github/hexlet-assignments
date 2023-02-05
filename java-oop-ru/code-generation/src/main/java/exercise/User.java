package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

// BEGIN
@Getter
@AllArgsConstructor
// END
@Value class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
