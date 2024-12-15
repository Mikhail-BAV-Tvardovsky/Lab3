package console;

public class MyArrayDataException extends RuntimeException
{
    public MyArrayDataException(int rowIndex, int columnIndex)
    {
        super("В ячейке " + (rowIndex+1) + "," + (columnIndex+1) + " массива есть некорректные данные.");
    }
}
