package exercise;

// BEGIN
public class App {

    public static void printSquare(Circle circle) {
        try {
            double square = circle.getSquare();
            System.out.println(Math.round(square) + "\nВычисление окончено");
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь\nВычисление окончено");
        }
    }
}
// END
