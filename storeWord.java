package se.kth.id1020.simplesearchengine;

import java.util.LinkedList;
import java.util.List;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;


public class storeWord 
{
       Word word;
       LinkedList<Document> docList = new LinkedList<Document>();
       LinkedList<Attributes> attrList = new LinkedList<Attributes>();

    int compareTo(storeWord key) 
    {
        return this.word.word.compareTo(key.word.word);
    }

    
}
