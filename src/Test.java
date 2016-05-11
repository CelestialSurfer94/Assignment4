import java.util.*;
public class Test {
    public static final String TESTSTRING = "doth";

	public static void main(String[] args) {
		FileInput.init();
		
		QPHash quad = new QPHash(); //careful throws null pointer if calling findCount(a) at the end.
        ChainingHash chain = new ChainingHash();
		String[] words = FileInput.readShakespeare();
        int testcount = 0;
		for(int i = 0; i < words.length; i++){
			chain.insert(words[i]);
            quad.insert(words[i]);
            if(words[i].equalsIgnoreCase(TESTSTRING)){
                testcount++;
            }

		}

		int count1 = chain.findCount(TESTSTRING); //this is obviously wrong.
        int count2 = quad.findCount(TESTSTRING);
        String a = chain.getNextKey();
        //String b = quad.getNextKey();

        System.out.println(testcount);
        System.out.println(count1);
        System.out.println(count2);
        /*
        System.out.println(b);
        System.out.println(quad.findCount(b));
        for(int i = 0; i < words.length; i++){
            a = chain.getNextKey();
            b = quad.getNextKey();
			if(b != null) {

                System.out.println(b);
                System.out.println(quad.findCount(b));

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
		*/
	}

}
