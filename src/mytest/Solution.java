package mytest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)  throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
        //алфавит
        String abc = "R";
        char[] abcArray = abc.toCharArray();
 
 
 
        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < abcArray.length; i++)
        {
            alphabet.add(abcArray[i]);
        }
 
        //ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }
 
// ?
 
        int count[]  = new int [abcArray.length];
 
        String text = "";
 
        for (String s : list) text += s;
        char[] textArray = text.toCharArray();
        char c;
 
        for (int i=0;i<abcArray.length;i++)
        {
            c = abcArray[i];
            for (int j=0;j<textArray.length;j++)
            {
                if (textArray[j] == c) ++count[i];
            }
        }
        for (int i=0;i<abcArray.length;i++) System.out.println(abcArray[i] + " " + count[i]);
 
 
    }
 
}

