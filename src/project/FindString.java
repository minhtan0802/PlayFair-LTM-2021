/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author minht
 */

public class FindString {
    static void find(String text, String keyword)
    {
        ArrayList<Integer> listIndex = new ArrayList<Integer>(); 
        String[] output=text.split(keyword);
        int index=0;
        if (text.indexOf(keyword)!=-1)
        {
            index=text.indexOf(keyword);
             listIndex.add(index);
              text=text.substring(index+keyword.length());
        }
        while(text.indexOf(keyword)!=-1)
        {
             index=index+text.indexOf(keyword)+keyword.length();
            listIndex.add(index);
           
            text=text.substring(text.indexOf(keyword)+keyword.length());
        }
       
        System.out.println("Vi tri:");
        for(int item : listIndex)
        {
            System.out.print(item+" ");
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Moi ban nhap text: ");
        String text=sc.nextLine();
        System.out.println("Moi ban nhap keyword");
        String keyword=sc.nextLine();
        
        find(text, keyword);
    }
}
