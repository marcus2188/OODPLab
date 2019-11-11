package utils;

public class Converter {
    
    public static int charToInt(char c){
        // A -> 0
        // B -> 1
        //...
        return (int)c - 65;
    }

    public static char intToChar(int i){
        // 0 -> A
        // 1 -> B
        //...
        return (char)(i + 65);
    }
}