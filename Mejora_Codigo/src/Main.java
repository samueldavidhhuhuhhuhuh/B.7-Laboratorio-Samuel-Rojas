import java.util.InputMismatchException;
import java.util.Scanner;

// Interface for geometric shapes
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

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.println("---------- Calculate area and perimeter of shapes -----------");

        while (option != 7) {
            printMenu();

            try {
                option = Integer.parseInt(sc.nextLine());
                Shape shape = null;

                switch (option) {
                    case 1:
                        shape = new Rectangle(getInput(sc, "length"), getInput(sc, "width"));
                        break;
                    case 2:
                        shape = new Circle(getInput(sc, "radius"));
                        break;
                    case 3:
                        shape = new Triangle(getInput(sc, "base"), getInput(sc, "height"));
                        break;
                    case 4:
                        shape = new Square(getInput(sc, "side"));
                        break;
                    case 5:
                        shape = new Sphere(getInput(sc, "radius"));
                        break;
                    case 6:
                        shape = new Cube(getInput(sc, "side"));
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        continue;
                    default:
                        System.out.println("Invalid option. Please select between 1 and 7.");
                        continue;
                }

                if (shape != null) {
                    System.out.printf("Area: %.2f\n", shape.calculateArea());
                    System.out.printf("Perimeter: %.2f\n", shape.calculatePerimeter());
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n1. Rectangle");
        System.out.println("2. Circle");
        System.out.println("3. Triangle");
        System.out.println("4. Square");
        System.out.println("5. Sphere");
        System.out.println("6. Cube");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    private static double getInput(Scanner sc, String dimension) {
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
