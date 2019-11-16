package utils;

/**
 A utility class to convert character to integer in 2D
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/


public class Converter {
    /** 
    * Convert char to int
    * @return The int
    */
    public static int charToInt(char c){
        // A -> 0
        // B -> 1
        //...
        return (int)c - 65;
    }
    
    /** 
    * Convert int to char
    * @return The char
    */
    public static char intToChar(int i){
        // 0 -> A
        // 1 -> B
        //...
        return (char)(i + 65);
    }
}