import java.util.*;
public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
		//TODO Initialize the hash tables
		ChainingHash test = new ChainingHash();
		String[] words = {"fuck","you","fuck"};
		for(int i = 0; i < words.length; i++){
			test.insert(words[i]);
		}
        String a = test.getNextKey();
        while(a!=null){
            System.out.println(a);
            a = test.getNextKey();
        }

        System.out.println(Arrays.toString(words));

		
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
