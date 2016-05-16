public class QPHash {
    public static final int DEFAULT_SIZE = 29221;
    public static final int HASH_CONSTANT = 11;
    private Node hashTable[];
    private int tableIndex;

    public QPHash(){
        this(DEFAULT_SIZE);
    }

    public QPHash(int startSize){
        tableIndex = 0;
        hashTable = new Node[startSize];
    }

    /**
     * This function allows rudimentary iteration through the QPHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey() {
        for (int i = tableIndex; i < hashTable.length; i++){
            if (hashTable[i] != null) {//non null node exists at that index;
                tableIndex = i + 1; //moves the index.
                return hashTable[i].keyword;
            }
        }
        tableIndex = 0;
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
        if(hashTable[hashVal] != null && hashTable[hashVal].keyword.equals(keyToFind)) {
            return hashTable[hashVal].count;
        }
        return 0; // does not exist in table, therefore count = 0.
    }

    /**
     * Hash function that returns the index in the array.
     * @param keyToHash
     * @return returns the hash value that the node key will be added to. Takes care of any
     * collisions that may occur by quadratic probing till it finds a suitable index in the hashTable.
     */
    private int hash(String keyToHash){
        int hashVal = 0;
        for(int i = 0; i < keyToHash.length(); i++){
            hashVal = HASH_CONSTANT * hashVal + keyToHash.charAt(i);
        }

        hashVal %= hashTable.length;
        if(hashVal < 0 ){ // adjusts hashVal to fit into indexes of the array.
            hashVal += hashTable.length;
        }

        if(hashTable[hashVal] == null) { // empty index in the table.
            return hashVal;
        }
        return reHash(keyToHash, hashVal); // need to probe if not empty.
    }

    /**
     *
     * @param keyToHash string to be rehashed by quadratic probing.
     * @param hashVal current hashVal.
     * @return returns new HashVal of first available index. If the keyToHash exists
     * in the hashTable already, just returns that HashVal.
     */
    private int reHash(String keyToHash, int hashVal){
        int numProbes = 1;
        while(hashTable[hashVal] != null){
            if (hashTable[hashVal].keyword.equals(keyToHash)){ //keyword already exists in table.
                return hashVal;
            }
            hashVal = (hashVal + numProbes * numProbes) % hashTable.length;
            numProbes++;
        }
        return hashVal;
    }
    
    private class Node{
        public int count;
        public String keyword;

        public Node(String keyToAdd){
            keyword = keyToAdd;
            count = 1;
        }
    }
}
