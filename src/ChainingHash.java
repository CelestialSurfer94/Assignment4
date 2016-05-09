public class ChainingHash {
	private static final int DEFAULT_SIZE = 10061;
	private int tableSize;
	private Node[] hashTable;
		public ChainingHash(){
			//TODO Implement a default constructor for ChainingHash
			//TOKALVIN! This implementation uses a list. Count would then be the
			//the size of the list.
			this(DEFAULT_SIZE);
		}
		
		public ChainingHash(int startSize){
			//TODO Implement a constructor that instantializes the hash array to startSize.
			tableSize = startSize;

			for(int i = 0; i < tableSize; i++){ //adds empty linked list to each index of table.
				hashTable[i] = new Node();
			}
		}

		/**
		 * This function allows rudimentary iteration through the ChainingHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
		public String getNextKey(){
			//TODO returns the next key in the hash table.
			//You will need external tracking variables to account for this.
		}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, it should be dealt with by chaining the keys together.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
		public void insert(String keyToAdd){
			int hashVal = hash(keyToAdd); // index of the array to add to linked list at that index.
			Node curNode = hashTable[hashVal];
			if(curNode.keyword == null){ //empty linked list, no collision
				curNode = new Node(keyToAdd);
			} else if (curNode.keyword == keyToAdd) {//non empty linked list, no collision.
				curNode.count++;
			} else { //collision.
				Node temp = curNode.next;
				boolean added = false;
				while(temp != null){
					if(temp.keyword.equals(keyToAdd)){
						temp.count++;
						added = true;
					}
					temp = temp.next;
				}
				if(added == false) {
					temp = new Node(keyToAdd);
				}
			}
		}
		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
		public int findCount(String keyToFind){
			//TODO Implement findCount such that it returns the number of times keyToFind
			int hashVal = hash(keyToFind);
			Node curNode = hasTable[hashVal];
			Node temp = curNode;
			if(curNode.keyword == keyToFind){
				return curNode.count;
			} else {
				while (temp != null) {
					if (temp.keyword == keyToFind) {
						return temp.count;
					}
				}
				return 0;
			}
		}

		private int hash(String keyToHash){
			int hashVal = 0;
			for(int i = 0; i < keyToHash.length(); i++){
				hashVal = 37 * hashVal + key.charAt(i);
			}
			hashVal %= tableSize;
			if(hashVal < 0 ){
				hashVal += tableSize;
			}
			return hashVal;
		}

	public class Node {
		String keyword;
		int count;
		Node next;

		public Node(String keyword){
			this.keyword = keyword;
			this.count = 1;
			next = null;
		}
	}
}
