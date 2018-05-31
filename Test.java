/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1020.simplesearchengine;

import java.util.ArrayList;
import se.kth.id1020.Driver;

/**
 *
 * @author yuchen
 */
public class Test 
{
    public static void main (String[] args) throws Exception 
    {
        TinySearchEngine test = new TinySearchEngine();
        Driver.run(test);
        // abc < ac, -1
        /*
        String word1 = "abc";
        String word2 = "ac";
        System.out.println(word1.compareTo(word2));*/
        
    }
    
}
