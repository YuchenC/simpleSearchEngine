
package se.kth.id1020.simplesearchengine;

import java.util.ArrayList;
import java.util.ArrayList;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;


public class BinarySearch 
{
    
    public static void search(storeWord current, Word insertWord, Attributes attr, ArrayList<storeWord> wordList, int lo, int hi) 
    {
        if (hi <= lo)
        {
            current.word = insertWord;
            current.attrList.add(attr);
            current.docList.add(attr.document);
           // System.out.println(insertWord.word + " " + attr.occurrence);
            wordList.add(hi, current);
            //return;
        }
        
        // already exists in the list
        int mid = lo + (hi - lo) / 2;
        int cmp = wordList.get(mid).word.word.compareTo(insertWord.word);
        // insertWord should be put before mid
        if (cmp > 0) search(current, insertWord, attr, wordList, lo, mid);
        // insertWord should be put after mid
        else if (cmp < 0) search(current, insertWord, attr, wordList, mid+1, hi);
        else 
        {
            //if (wordList.get(mid).docList.getLast() != attr.document)
            wordList.get(mid).docList.add(attr.document);
            wordList.get(mid).attrList.add(attr);
            //return;
        }  
    }  
    
    public static int search(String insertWord, ArrayList<storeWord> wordList, int lo, int hi) 
    {
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        int cmp = wordList.get(mid).word.word.compareTo(insertWord);
        if      (cmp > 0) return search(insertWord, wordList, lo, mid);
        else if (cmp < 0) return search(insertWord, wordList, mid+1, hi);
        else              return mid;
    }
}
