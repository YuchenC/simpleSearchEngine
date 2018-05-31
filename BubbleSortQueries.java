
package se.kth.id1020.simplesearchengine;

import java.util.LinkedList;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;

public class BubbleSortQueries 
{
    // popularity
    public static void sort (boolean asc, LinkedList<Document> result)
    {
        int r = result.size() - 1;
        boolean swapped = true;
        while (r >= 0 && swapped)
        {
            swapped = false;
            for (int i = 0; i < r; i++)
            {
                if (result.get(i).popularity > result.get(i + 1).popularity)
                {
                    swapped = true;
                    swap(result, i);
                }  
            }
            r--;    
        }
        direction(result, asc);
    }
  
    public static void swap(LinkedList<Document> result, int i)
    {
        Document temp;
        temp = result.get(i);
        result.set(i, result.get(i+1));
        result.set(i+1, temp);
    }
    
    // occurrence
    public static void sort (boolean asc, LinkedList<Attributes> resultAttr, LinkedList<Document> result)
    {
        int r = result.size() - 1;
        boolean swapped = true;
        while (r >= 0 && swapped)
        {
            swapped = false;
            for (int i = 0; i < r; i++)
            if (resultAttr.get(i).occurrence > resultAttr.get(i + 1).occurrence)
            {
                swapped = true;
                swap(resultAttr, result, i);
            } 
            r--;    
        }
        direction(resultAttr, result, asc);
    }
    
    public static void swap(LinkedList<Attributes> resultAttr, LinkedList<Document> result, int i)
    {
        Attributes tempAttr;
        tempAttr = resultAttr.get(i);
        resultAttr.set(i, resultAttr.get(i+1));
        resultAttr.set(i+1, tempAttr);
        
        Document tempDoc;
        tempDoc = result.get(i);
        result.set(i, result.get(i+1));
        result.set(i+1, tempDoc);
    }
    
    // count 
    public static void sortCount (boolean asc, LinkedList<countOccurrence> resultCount, LinkedList<Document> result)
    {
        int r = result.size() - 1;
        boolean swapped = true;
        while (r >= 0 && swapped)
        {
            swapped = false;
            for (int i = 0; i < r; i++)
            if (resultCount.get(i).count > resultCount.get(i + 1).count)
            {
                swapped = true;
                swapCount(resultCount, result, i);
            }
            r--;  
        }
        direction(result, asc);
    }
    
    public static void swapCount (LinkedList<countOccurrence> resultCount, LinkedList<Document> result, int i)
    {
        countOccurrence tempOccurrence;
        tempOccurrence = resultCount.get(i);
        resultCount.set(i, resultCount.get(i+1));
        resultCount.set(i+1, tempOccurrence);
        
        Document tempDoc;
        tempDoc = result.get(i);
        result.set(i, result.get(i+1));
        result.set(i+1, tempDoc);
    }
    
    public static void direction (LinkedList<Document> result, boolean asc)
    {
        if (asc)
            return;
        else
        {
           LinkedList<Document> reverseResult = new LinkedList<Document>();
           for (int i = 0; i < result.size(); i++)
               reverseResult.add(result.get(i));
           result.clear();
           for (int i = reverseResult.size() - 1; i >= 0; i--)
               result.add(reverseResult.get(i));       
        }
    }
    
    public static void direction (LinkedList<Attributes> resultAttr, LinkedList<Document> result, boolean asc)
    {
        if (asc)
            return;
        else
        {
           LinkedList<Document> reverseDoc = new LinkedList<Document>();
           LinkedList<Attributes> reverseAttr = new LinkedList<Attributes>();
           for (int i = 0; i < result.size(); i++)
           {
               reverseDoc.add(result.get(i));
               reverseAttr.add(resultAttr.get(i));
           }
           result.clear();
           resultAttr.clear();
           for (int i = reverseDoc.size() - 1; i >= 0; i--)
           {
               result.add(reverseDoc.get(i));     
               resultAttr.add(reverseAttr.get(i));
           }
        }
    }
}
