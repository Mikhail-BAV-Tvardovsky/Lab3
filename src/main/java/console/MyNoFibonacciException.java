package console;

public class MyNoFibonacciException extends RuntimeException
{
    public MyNoFibonacciException(int rowIndex, int columnIndex)
    {
        super("В ячейке " + (rowIndex+1) + "," + (columnIndex+1) + " массива не число Фибоначчи в пределах тысячи.");
    }
}
