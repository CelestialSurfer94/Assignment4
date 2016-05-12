public class QPHash {
    private static final int DEFAULT_SIZE = 104717;
    private Node hashTable[];
    private int tableSize;
    private int curProbe;
    public QPHash(){
        this(DEFAULT_SIZE);
    }
		
    public QPHash(int startSize){
        curProbe = 0;
        tableSize = startSize;
        hashTable = new Node[startSize];
    }

    /**
     * This function allows rudimentary iteration through the QPHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey() {
        if (curProbe < tableSize - 1) { //might need to be strictly less than tablesize.
            curProbe++;
            Node TEST = hashTable[curProbe];
            if (TEST == null) {
                return getNextKey();
            } else {
                return hashTable[curProbe].keyword;
            }
        }
        curProbe = 0;
        return null;
    }
    /**
     * Adds the key to the hash table.
     * If there is a collision, a new location should be found using quadratic probing.
     * If the key is already in the hash table, it increments that key's counter.
     * @param keyToAdd : the key which will be added to the hash table
     */
    public void insert(String keyToAdd){
        int hashVal = hash(keyToAdd);
        if(hashTable[hashVal] == null) { //keyword not yet in table
            hashTable[hashVal] = new Node(keyToAdd);
        } else { //keyword exists in table already.
            hashTable[hashVal].count++;
        }
    }
    /**
     * Returns the number of times a key has been added to the hash table.
     * @param keyToFind : The key being searched for
     * @return returns the number of times that key has been added.
     */
    public int findCount(String keyToFind){
        int hashVal = hash(keyToFind);
        if(hashTable[hashVal].keyword != null) { //non null node exists.
            if (hashTable[hashVal].keyword.equals(keyToFind)) {
                return hashTable[hashVal].count;
            }
        }
        return 0;
    }

    private int hash(String keyToHash){
      int hashVal = 0;
        for(int i = 0; i < keyToHash.length(); i++){
            hashVal = 37 * hashVal + keyToHash.charAt(i);
        }
        hashVal %= tableSize;
        if(hashVal < 0 ){
            hashVal += tableSize;
        }
        hashVal = (hashVal + curProbe * curProbe) %tableSize;
        if(hashTable[hashVal]  != null){ //TODO MADE CHANGES HERE
            if(!hashTable[hashVal].keyword.equals(keyToHash)){
                curProbe++;
                hashVal = hash(keyToHash);
            }
        }
        curProbe = 0;
        return hashVal;
    }


    private class Node{
        public int count;
        public String keyword;

        public Node(String keyToAdd){
            keyword = keyToAdd;
            count = 1;
        }

        public Node(){
            keyword = null;
            count = 0;

        }
    }
}
