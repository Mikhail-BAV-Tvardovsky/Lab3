package console;

public class MyVowelsException extends RuntimeException
{
    public MyVowelsException(int rowIndex, int columnIndex, char vowel)
    {
        super("В ячейке " + (rowIndex+1) + "," + (columnIndex+1) + " массива находится гласная буква '" + vowel + "'.");
    }
}