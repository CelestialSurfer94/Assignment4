public class ChainingHash {
	private static final int DEFAULT_SIZE = 10061;
	private int tableSize;
	private Node[] hashTable;
    private int curKey;
    private Node tracker;
		public ChainingHash(){
            this(DEFAULT_SIZE);
		}
		
		public ChainingHash(int startSize){
			tableSize = startSize;
            curKey = 0;
            tracker = null; // might need to change later.
            hashTable = new Node[DEFAULT_SIZE];
			for(int i = 0; i < tableSize; i++){ //initializes nodes at each index.
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
            for (int i = curKey; i < tableSize; i++){
                Node curNode = hashTable[i];
                if(curNode.keyword != null){ // a non null node exists.
					if(curNode.next == null) { // a single node
						curKey = i;
						return curNode.keyword;
					} else{ //need to track the node of the linked list, so after returning I can resume iteration.
                        if(tracker == null){ //tracker not initialized.
                            tracker = curNode;
                            return tracker.keyword;
                        }
                        if(tracker != null){ // tracker initialized. Need to return next node.
                            tracker = tracker.next; //might get null pointer here. ^^
                            return tracker.keyword;
                        } // if this works like I want it will be null after it gets to the end of the list. Wont enter.
					}
                }
            }
            return null;
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
                hashTable[hashVal] = new Node(keyToAdd);
			} else if (curNode.keyword.equals(keyToAdd)) {//non empty linked list, no collision.
				curNode.count++;
			} else { //collision.
				Node temp = curNode;
				boolean added = false;
				while(temp != null){
					if(temp.keyword.equals(keyToAdd)){
						temp.count++;
						added = true;
					}
					temp = temp.next;
				}
				if(!added) {
					curNode.add(new Node(keyToAdd));
				}
			}
		}
		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
		public int findCount(String keyToFind){
			int hashVal = hash(keyToFind);
			Node curNode = hashTable[hashVal];
			Node temp = curNode;
			if(curNode.keyword.equals(keyToFind)){ // first node is the target node.
				return curNode.count;
			} else { //target node somewhere in the linked list.
				while (temp != null) {
					if (temp.keyword.equals(keyToFind)) {
						return temp.count;
					}
                    temp = temp.next;
				} //if not at first node and not in the linked list, doesn't exist.
				return 0;
			}
		}

		private int hash(String keyToHash){
			/*int hashVal = 0;
			for(int i = 0; i < keyToHash.length(); i++){
				hashVal = 37 * hashVal + keyToHash.charAt(i);
			}
			hashVal %= tableSize;
			if(hashVal < 0 ){
				hashVal += tableSize;
			}
			return hashVal;
			*/
            return 1;
		}

	public class Node {
		String keyword;
		int count;
		Node next;

		public Node(String keyword){
			this.keyword = keyword;
            next = null;
            if(this.keyword == null) {
                this.count = 0;
            } else {
                this.count = 1;
            }
		}

		public Node(){ //gonna fuck with this and see.
			this(null);
		}

        public void add(Node nodeToAdd){
            Node temp = this;
            while(temp.next!= null){
                temp = temp.next;
            }
            temp.next = nodeToAdd;
        }
	}
}
