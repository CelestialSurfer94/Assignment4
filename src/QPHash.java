public class QPHash {
	private static final int DEFAULT_SIZE = 10061;
    private String[] hashTable;
		public QPHash(){
			//TODO Implement a default constructor for QPHash
            //gonna try a map implementation for this.
            hashTable = new String[DEFAULT_SIZE];
		}
		
		public QPHash(int startSize){
            hashTable = new String[startSize];
			//TODO Implement a constructor that instantializes the hash array to startSize.
		}

		/**
		 * This function allows rudimentary iteration through the QPHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
		public String getNextKey(){
			//TODO returns the next key in the hash table.
			//You will need external tracking variables to account for this.
			return null;
		}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, a new location should be found using quadratic probing.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
		public void insert(String keyToAdd){
			//TODO Implement insert into the hash table.
			//If keyToAdd is already in the hash table, then increment its count.
		}
		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
		public int findCount(String keyToFind){
			//TODO Implement findCount such that it returns the number of times keyToFind
			// has been added to the data structure.
			return 0;
		}

		private int hash(String keyToHash){
			return -1;
			//EXTRA CREDIT: Implement your own String hash function here.
		}
}
