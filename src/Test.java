import java.util.*;
public class Test {
    public static final String TESTSTRING = "sure"; //doesnt work for doth?

    public static void main(String[] args) {
        FileInput.init();
		
        QPHash quad = new QPHash(); //careful throws null pointer if calling findCount(a) at the end.
        ChainingHash chain = new ChainingHash();
        String[] hamlet = FileInput.readShakespeare();
        String[] bacon = FileInput.readBacon();
        for(int i = 0; i < bacon.length; i++){
            quad.insert(bacon[i]);
        }

        int countTest = 0;
        for(int i = 0; i < bacon.length; i++){
            String a = quad.getNextKey();
            if(a!= null) {
                System.out.println(a);
                countTest++;
            }
        }
        System.out.println(countTest);

        //TODO consider finding a count of the null nodes to calculate load factor.
        //TODO Initialize the hash tables

        //TODO Use the FileInput functions to read the two files.
		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		
		
		//TODO Initialize necessary variables for calculating the square error
		// and most distant word.
		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
		
		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.

    }

}
