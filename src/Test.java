import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLOutput;
import java.util.*;
public class Test {
    public static final String TESTSTRING = "doth"; //doesnt work for doth?

    public static void main(String[] args) {
        FileInput.init();
        QPHash hamlet = new QPHash(); //careful throws null pointer if calling findCount(a) at the end.
        ChainingHash bacon = new ChainingHash();
        ChainingHash hamletC = new ChainingHash();
        QPHash baconQ = new QPHash();

        String[] hamletWords = FileInput.readShakespeare();
        String[] baconWords = FileInput.readBacon();
        for(int i = 0; i < hamletWords.length; i++){
            hamlet.insert(hamletWords[i]);
            hamletC.insert(hamletWords[i]);

        }

        for(int i = 0; i < baconWords.length; i++){ //adds to the chain
            bacon.insert(baconWords[i]);
            baconQ.insert(baconWords[i]);
        }

        double max = 0.0;
        String greatestWord = null;
        String hamletKey = hamlet.getNextKey();
        double squareErr = 0.0;
        while(hamletKey != null){ //checks hamKey vs Bacon Key.
            double hamFreq = (double) hamlet.findCount(hamletKey)/ hamletWords.length;
            double baconFreq = (double) bacon.findCount(hamletKey)/baconWords.length;
            double difference = Math.abs(hamFreq - baconFreq);
            if(difference > max){
                greatestWord = hamletKey;
                max = difference;
            }
            squareErr += Math.pow(difference, 2);
            hamletKey = hamlet.getNextKey();

        }
        System.out.println(squareErr);

        String baconKey = bacon.getNextKey();
        while(baconKey != null){
            double hamFreq =  (double) hamlet.findCount(baconKey)/hamletWords.length;
            double baconFreq = (double) bacon.findCount(baconKey)/baconWords.length;
            double difference = Math.abs(hamFreq - baconFreq);
            if(hamFreq == 0){
                squareErr += Math.pow(difference,2);
            }
            if(difference > max){
                greatestWord = baconKey;
                max = difference;
            }
            baconKey = bacon.getNextKey();
        }

        System.out.println(squareErr);
    }

}
