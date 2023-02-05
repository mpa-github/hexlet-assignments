package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> nullFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(address);
                    if (value == null) {
                        nullFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return nullFields;
    }
}
// END
