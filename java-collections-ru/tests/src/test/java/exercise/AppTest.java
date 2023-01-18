package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

    List<Integer> list;

    @BeforeEach
    void createList() {
        list = new ArrayList<>(5);
        this.list.add(0);
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
        this.list.add(4);
    }

    @Test
    void testTake() {
        // BEGIN
        List<Integer> expected = List.of(0, 1);
        List<Integer> actual = App.take(list, 2);
        Assertions.assertEquals(expected, actual);
        // END
    }
}
