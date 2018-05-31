/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1020.simplesearchengine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;
//import se.kth.id1020.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author yuchen
 */
public class TinySearchEngine implements TinySearchEngineBase
{

    storeWord current = new storeWord();
    ArrayList<storeWord> wordList = new ArrayList<storeWord>();
    int counter = 0;
    
    //Map <storeWord, Double> map = new HashMap <storeWord,Double>();
    Map <Word, Double> map = new HashMap <Word, Double>();
    //List <Map.Entry <String, Double>> key = new ArrayList <Map.Entry <String, Double>> ();
    
    
    public void insert(Word insertWord, Attributes attr) 
    {
        counter++;
        
        current = new storeWord();
        BinarySearch.search(current, insertWord, attr, wordList, 0, wordList.size());
                
        if (counter == 1161191)
        {
            for (int i = 0; i < 5000; i++)
                System.out.println(wordList.get(i).word.word  + " " + wordList.get(i).attrList);
        }
        
        
        /*
        //Map <String, Double> map = new HashMap <String, Double>();
        double i = 0;
        current = new storeWord();
        current.word = insertWord;
        current.attrList.add(attr);
        current.docList.add(attr.document);
        map.put(insertWord, i);
        i++;
        
        //System.out.println("map.size = " + map.size());
        //System.out.println(counter);
        
        //if (counter == 1161191)
        //{
            //List <Map.Entry <storeWord, Double>> key = new ArrayList <Map.Entry <storeWord, Double>> (map.entrySet() );
            List <Map.Entry <Word, Double>> key = new ArrayList <Map.Entry <Word, Double>> (map.entrySet() );
            //Collections.sort(key, new Comparator <Map.Entry <storeWord, Double>>() 
            Collections.sort(key, new Comparator <Map.Entry <Word, Double>> ()
                    {
                /*
                public int compare(Map.Entry<storeWord, Double> o1, Map.Entry<storeWord, Double> o2)
                {
                    //System.out.println(o1.getKey().word.word + " " + o2.getKey().word.word)  ;  
                    //String id1 = o1.getKey().word.word;
                    //String id2 = o2.getKey().word.word;
                    return o1.getKey().compareTo(o2.getKey());
                }
                */            
                
                /*
                public int compare(Map.Entry<Word, Double> o1, Map.Entry <Word, Double> o2)
                {
                    return o1.getKey().word.compareTo(o2.getKey().word);
                }
*/
            /*    
            }
            );
            */
            //System.out.println("counter = " + counter);
            //current.attrList = 
            //if (counter == 1161191)
            //{
              //  System.out.println("end");
                //for (int k = 0; k < key.size(); k++)
                //{
                //String id = key.get(k).getKey().word.word;
                //System.out.println(key.get(k).getKey().word);
            //}
            //}
        //}
        
        
    }

    public LinkedList<Document> search(String query) 
    {
        String[] queries = query.split(" ");
        LinkedList<Document> resultDoc = new LinkedList<Document>();
        LinkedList<Attributes> resultAttr = new LinkedList<Attributes>();
        LinkedList<countOccurrence> resultCount = new LinkedList<countOccurrence>();
        LinkedList<Attributes> duplicateAttr = new LinkedList<Attributes>();
        
        boolean asc = false;
        if (queries[queries.length - 1].equals("asc"))
            asc = true;
        
        int k = 0;
        while(k < queries.length)
        {
            if (queries[k].equals("orderby"))
            {
                k++;
                continue;
            }
            else if (queries[k].equals("popularity"))
            {
                BubbleSortQueries.sort(asc, resultDoc);
                // final result first push and then pop, to get final result in reverse order, linkedList api
                break;
            }
            else if (queries[k].equals("occurrence"))
            {
                BubbleSortQueries.sort(asc, resultAttr, resultDoc);
                for (int m = 0; m < resultAttr.size(); m++)
                    System.out.println(resultDoc.get(m) + " " + resultAttr.get(m));
                break;
            }
            else if (queries[k].equals("count"))
            {
                for (int i = 0; i < duplicateAttr.size(); i++)
                {   
                    countOccurrence co = new countOccurrence();
                    co.count = 0;
                    //System.out.println("duplicateAttr = " + duplicateAttr.get(i).document.name);
                    co.doc = duplicateAttr.get(i).document;
                    for (int j = 0; j < duplicateAttr.size(); j++)
                        if (duplicateAttr.get(i).document == duplicateAttr.get(j).document)
                            co.count ++; 
                    //System.out.println(co.doc.name + " contains " + resultCount.contains(co));
                    //if (!resultCount.contains(co))
                        resultCount.add(co); 
                }
                for (int m = 0; m < resultCount.size(); m++)
                    for (int n = 0; n < resultCount.size();n++)
                        if (resultCount.get(m).count != 1 && resultCount.get(m).doc.name == resultCount.get(n).doc.name)
                            resultCount.remove(resultCount.get(n));
                BubbleSortQueries.sortCount(asc, resultCount, resultDoc);
                for (int m = 0; m <resultCount.size(); m++)
                    System.out.println(resultCount.get(m).doc.name + " " + resultCount.get(m).count);
                break;
            }
            else
            {
                int index = BinarySearch.search(queries[k], wordList, 0, wordList.size());
                if (index == 0)
                {
                    System.out.println("No match for word \"" + queries[k] + "\" in documents");   
                    LinkedList<Document> noSuchWord = new LinkedList<Document>();
                }
                else
                {
                    for (int m = 0; m < wordList.get(index).docList.size(); m++)
                    {
                        if (!resultDoc.contains(wordList.get(index).docList.get(m)))
                        {
                            resultDoc.add(wordList.get(index).docList.get(m));
                            resultAttr.add(wordList.get(index).attrList.get(m));
                        }
                        if (!duplicateAttr.contains(wordList.get(index).attrList.get(m)))
                           duplicateAttr.add(wordList.get(index).attrList.get(m));  
                    }
                }
            }
            k++;
        }      
        return resultDoc;   
    }    
}

/* 
        String property = queries[indexSort + 1];
        if (queries.length - 1 > indexSort)
        {
            if (property.equals("count"))
            {}
            else if (property.equals("popularity"))
                BubbleSortQueries.sort(resultDoc);
            else if (property.equals("occurrence"))
                BubbleSortQueries.sort(resultAttr, resultDoc);
        }
        
        indexSort++;
        
        String direction = queries[indexSort];
        if (queries.length - 1 > indexSort)
        {
        if (direction.equals("asc"))
        {
            
        }
        else if (direction.equals("desc"))
        {
            
        }
        }
        */
        //BubbleSortQueries.sort(resultDoc);
        //BubbleSortQueries.sort(resultAttr, resultDoc);
       /*for (int m = 0; m < duplicateAttr.size();m++)
           System.out.println(duplicateAttr.get(m).document.name + " " + duplicateAttr.get(m).occurrence);
       System.out.println("duplicateAttr size = " + duplicateAttr.size());*/ 
