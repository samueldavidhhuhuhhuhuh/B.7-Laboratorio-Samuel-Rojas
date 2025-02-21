import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface y clases de figuras (sin cambios)
interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

class Rectangle implements Shape {
    private double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Triangle implements Shape {
    private double base, height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public double calculatePerimeter() {
        // Assuming an isosceles triangle for perimeter calculation
        double side = Math.sqrt(Math.pow(height, 2) + Math.pow(base / 2, 2));
        return 2 * side + base;
    }
}

class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }
}

class Sphere implements Shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double calculatePerimeter() {
        // Spheres don't have a perimeter, returning circumference for reference
        return 2 * Math.PI * radius;
    }
}

class Cube implements Shape {
    private double side;

    public Cube(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return 6 * side * side;
    }

    @Override
    public double calculatePerimeter() {
        // Considering the total edge length as "perimeter"
        return 12 * side;
    }
}

// Clase utilitaria para entrada de datos
class InputHandler {
    public static double getInput(Scanner sc, String dimension) {
        System.out.printf("Insert %s: ", dimension);
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine());
                if (value <= 0) {
                    System.out.print("Value must be positive. Try again: ");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}

// Clase que define los parámetros necesarios por figura
class DimensionHelper {
    public static List<String> getDimensions(int option) {
        switch (option) {
            case 1: return List.of("length", "width");
            case 2: return List.of("radius");
            case 3: return List.of("base", "height");
            case 4: return List.of("side");
            case 5: return List.of("radius");
            case 6: return List.of("side");
            default: throw new IllegalArgumentException("Invalid option");
        }
    }
}

// Factory para crear instancias de Shape
class ShapeFactory {
    public static Shape createShape(int option, List<Double> params) {
        switch (option) {
            case 1: return new Rectangle(params.get(0), params.get(1));
            case 2: return new Circle(params.get(0));
            case 3: return new Triangle(params.get(0), params.get(1));
            case 4: return new Square(params.get(0));
            case 5: return new Sphere(params.get(0));
            case 6: return new Cube(params.get(0));
            default: throw new IllegalArgumentException("Invalid option");
        }
    }
}

// Manejo del menú
class MenuHandler {
    public static void printMenu() {
        System.out.println("\n1. Rectangle");
        System.out.println("2. Circle");
        System.out.println("3. Triangle");
        System.out.println("4. Square");
        System.out.println("5. Sphere");
        System.out.println("6. Cube");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }
}

// Clase principal con flujo refactorizado
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------- Calculate area and perimeter of shapes -----------");
        int option;

        do {
            MenuHandler.printMenu();
            try {
                option = Integer.parseInt(sc.nextLine());
                if (option == 7) break;

                if (option < 1 || option > 6) {
                    System.out.println("Invalid option. Please select between 1 and 7.");
                    continue;
                }

                List<String> dimensions = DimensionHelper.getDimensions(option);
                List<Double> params = new ArrayList<>();
                for (String dim : dimensions) {
                    params.add(InputHandler.getInput(sc, dim));
                }

                Shape shape = ShapeFactory.createShape(option, params);
                System.out.printf("Area: %.2f\n", shape.calculateArea());
                System.out.printf("Perimeter: %.2f\n", shape.calculatePerimeter());

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        System.out.println("Exiting...");
        sc.close();
    }
}