import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLOutput;
import java.util.*;
public class Test {
    public static final String TESTSTRING = "discord";

    public static void main(String[] args) {
        FileInput.init();
        QPHash hamlet = new QPHash();
        ChainingHash bacon = new ChainingHash();

        String[] hamletWords = FileInput.readShakespeare();
        String[] baconWords = FileInput.readBacon();
        
        for(int i = 0; i < hamletWords.length; i++){//adds to the QPHash
            hamlet.insert(hamletWords[i]);

        }

        for(int i = 0; i < baconWords.length; i++){ //adds to the chain
            bacon.insert(baconWords[i]);
        }

        double max = 0.0; //current max frequency difference.
        String greatestWord = null; //greatest word difference by frequency
        double squareErr = 0.0; //total square error.
        String hamletKey = hamlet.getNextKey();
   
        while(hamletKey != null){ //checks hamKey vs Bacon Key.
            double hamFreq = (double) hamlet.findCount(hamletKey)/ hamletWords.length;
            double baconFreq = (double) bacon.findCount(hamletKey)/baconWords.length;
            double difference = Math.abs(hamFreq - baconFreq);
            if(difference > max){ //difference greater than current max, reassigns greatest word
                greatestWord = hamletKey;
                max = difference;
            }
            squareErr += Math.pow(difference, 2);
            hamletKey = hamlet.getNextKey();
        }


        String baconKey = bacon.getNextKey();
        while(baconKey != null){ //checks bacon vs ham. Ignores any common words.
            double hamFreq =  (double) hamlet.findCount(baconKey)/hamletWords.length;
            double baconFreq = (double) bacon.findCount(baconKey)/baconWords.length;
            double difference = Math.abs(hamFreq - baconFreq);
            if(hamFreq == 0){ //found a word not common to both texts.
                squareErr += Math.pow(difference,2); //add to total square error.
            }
            if(difference > max){ // reassigning of greatest word if necassary. 
                greatestWord = baconKey;
                max = difference;
            }
            baconKey = bacon.getNextKey();
        }

        System.out.println("bacon number of collisions: " + bacon.numCollisions);
        System.out.println("ham load factor:            " + hamlet.loadFactor());
        System.out.println("bacon load factor:          " + bacon.loadFactor());
        System.out.println("total square error:         " +squareErr);
        System.out.println("greatest word:              " +greatestWord);
    }
}
