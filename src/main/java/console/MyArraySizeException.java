package console;

public class MyArraySizeException extends RuntimeException
{
    public MyArraySizeException()
    {
        super("Неправильный размер массива.");
    }
}
