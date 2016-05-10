import java.util.*;
public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
		ChainingHash test = new ChainingHash(); //careful throws null pointer if calling findCount(a) at the end.
		String[] words = FileInput.readShakespeare();
		for(int i = 0; i < words.length; i++){
			test.insert(words[i]);
		}
        String a = test.getNextKey();
        System.out.println(a);
        System.out.println(test.findCount(a));
        for(int i = 0; i < words.length; i++){
            a = test.getNextKey();

			if(a!= null) {
                System.out.println(a);
                System.out.println(test.findCount(a));
			}

        }


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
