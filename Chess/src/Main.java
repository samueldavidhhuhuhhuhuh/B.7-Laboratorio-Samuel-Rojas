import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (args.length != 4) {
            printResult("Invalid", "Invalid", "Invalid", new ArrayList<>(), new ArrayList<>(), false);
            return;
        }

        String algorithmParam = args[0].split("=")[1];
        String typeParam = args[1].split("=")[1];
        String colorParam = args[2].split("=")[1];
        String quantityParam = args[3].split("=")[1];

        String algorithm = getAlgorithm(algorithmParam);
        String type = getType(typeParam);
        String color = getColor(colorParam);
        int quantity = getQuantity(quantityParam);

        if (algorithm.equals("Invalid") || type.equals("Invalid") || color.equals("Invalid") || quantity == -1) {
            printResult(algorithm, type, color, new ArrayList<>(), new ArrayList<>(), false);
            return;
        }

        List<Object> values = generateValues(type, quantity);

        System.out.println("Valores generados (sin ordenar): " + values);

        List<Object> sortedValues = new ArrayList<>(values);
        sortValues(sortedValues, algorithm);

        printResult(algorithm, type, color, values, sortedValues, true);
    }

    private static String getAlgorithm(String algorithmParam) {
        return switch (algorithmParam.toLowerCase()) {
            case "i" -> "Insertion sort";
            case "q" -> "Quick sort";
            default -> "Invalid";
        };
    }

    private static String getType(String typeParam) {
        return switch (typeParam.toLowerCase()) {
            case "c" -> "Character";
            case "n" -> "Numeric";
            default -> "Invalid";
        };
    }

    private static String getColor(String colorParam) {
        return switch (colorParam.toLowerCase()) {
            case "b" -> "Black";
            case "w" -> "White";
            default -> "Invalid";
        };
    }

    private static int getQuantity(String quantityParam) {
        try {
            int quantity = Integer.parseInt(quantityParam);
            return (quantity >= 0 && quantity <= 16) ? quantity : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static List<Object> generateValues(String type, int quantity) {
        List<Object> values = new ArrayList<>();
        if (type.equals("Character")) {
            char[] pieces = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'};
            for (int i = 0; i < quantity; i++) {
                values.add(pieces[i]);
            }
        } else if (type.equals("Numeric")) {
            for (int i = 1; i <= quantity; i++) {
                values.add(i);
            }
        }
        Collections.shuffle(values);
        return values;
    }

    private static void sortValues(List<Object> values, String algorithm) {
        if (algorithm.equals("Insertion sort")) {
            insertionSort(values);
        } else if (algorithm.equals("Quick sort")) {
            quickSort(values, 0, values.size() - 1);
        }
    }

    private static void insertionSort(List<Object> values) {
        for (int i = 1; i < values.size(); i++) {
            Object key = values.get(i);
            int j = i - 1;
            while (j >= 0 && values.get(j).toString().compareTo(key.toString()) > 0) {
                values.set(j + 1, values.get(j));
                j--;
            }
            values.set(j + 1, key);
        }
    }

    private static void quickSort(List<Object> values, int low, int high) {
        if (low < high) {
            int pi = partition(values, low, high);
            quickSort(values, low, pi - 1);
            quickSort(values, pi + 1, high);
        }
    }

    private static int partition(List<Object> values, int low, int high) {
        Object pivot = values.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (values.get(j).toString().compareTo(pivot.toString()) <= 0) {
                i++;
                Collections.swap(values, i, j);
            }
        }
        Collections.swap(values, i + 1, high);
        return i + 1;
    }

    private static void printResult(String algorithm, String type, String color, List<Object> unsortedValues, List<Object> sortedValues, boolean valid) {
        if (!valid) {
            System.out.println("Ordenamiento: Invalido");
            System.out.println("Tipo: Invalido");
            System.out.println("Color: Invalido");
            System.out.println("Valores: []");
            System.out.println("Valores Invalidos");
        } else {
            System.out.println("Ordenamiento: [" + algorithm + "]");
            System.out.println("Tipo: [" + type + "]");
            System.out.println("Color: [" + color + "]");
            System.out.println("Valores: " + unsortedValues);
            System.out.println("Ordenamiento: " + sortedValues);
        }
    }
}