package console;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[][] fibonacciArray = {
                {"1", "3", "1", "1"},
                {"0.3", "13L", "13f", "1"},
                {"2", "2.5", "3", "1"},
                {"1", "987", "0.0", "986"},
        };

        try {
            Result result = getSum(fibonacciArray);
            // Проверяем, было ли исключение MyVowelsException
            if (!result.vowelExceptionOccurred) {
                System.out.println("Сумма элементов массива: " + result.sum);
            }
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Новый класс для результата метода getSum
    public static class Result {
        public double sum;
        public boolean vowelExceptionOccurred;

        public Result(double sum, boolean vowelExceptionOccurred) {
            this.sum = sum;
            this.vowelExceptionOccurred = vowelExceptionOccurred;
        }
    }

    public static Result getSum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException();
            }
        }

        double sum = 0.0;
        List<Exception> otherExceptions = new ArrayList<>();
        boolean vowelExceptionOccurred = false; // Флаг для отслеживания MyVowelsException

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                String value = array[i][j];

                if (isVowel(value)) {
                    // Устанавливаем флаг при обнаружении гласной и добавляем исключение
                    vowelExceptionOccurred = true;
                    otherExceptions.add(new MyVowelsException(i, j, value.charAt(0)));
                    continue; // Пропускаем гласные буквы
                }

                try {
                    // Регулярное выражение для проверки чисел с суффиксами f и L
                    if (!value.matches("\\d+(\\.\\d+)?[fL]?")) {
                        throw new MyArrayDataException(i, j);
                    }

                    // Убираем суффиксы f и L
                    value = value.replaceAll("[fL]", "");

                    // Парсим строку в число
                    double number = Double.parseDouble(value);

                    sum += number;

                    // Если число не целое, выбрасываем исключение MyNoFibonacciException
                    if (number % 1 != 0) {
                        otherExceptions.add(new MyNoFibonacciException(i, j));
                    } else {
                        // Проверяем целые числа на принадлежность к числам Фибоначчи
                        if (number > 1000 || !isFibonacci((int) number)) {
                            otherExceptions.add(new MyNoFibonacciException(i, j));
                        }
                    }
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        // Обрабатываем все собранные исключения
        for (Exception e : otherExceptions) {
            e.printStackTrace();
        }

        // Возвращаем сумму и флаг
        return new Result(sum, vowelExceptionOccurred);
    }

    public static boolean isFibonacci(int number) {
        int n = 5 * number * number;
        return isSquareNumber(n + 4) || isSquareNumber(n - 4);
    }

    public static boolean isSquareNumber(int number) {
        double sqrt = Math.sqrt(number);
        int ceil = (int) Math.ceil(sqrt);
        int floor = (int) sqrt;

        return ceil * ceil == floor * floor;
    }

    public static boolean isVowel(String value) {
        if (value.length() == 1) {
            char ch = value.toLowerCase().charAt(0);
            return "аеёиоуыэюяaeyuio".indexOf(ch) != -1;
        }
        return false;
    }
}
