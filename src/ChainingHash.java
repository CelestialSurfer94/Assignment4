public class ChainingHash {
    public static final int DEFAULT_SIZE = 29221;
    public static final int HASH_CONSTANT = 11;
    private Node[] hashTable;
    private int tableIndex; // current index in the table.
    private Node listIndex; // current index of the list within that index.

    public ChainingHash(){
        this(DEFAULT_SIZE);
    }
		
    public ChainingHash(int startSize){
        tableIndex = 0;
        listIndex = null;
        hashTable = new Node[startSize];
    }

    /**
     * This function allows rudimentary iteration through the ChainingHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey(){
        for(int i = tableIndex; i < hashTable.length; i++) {
            if(hashTable[i] != null) {
                tableIndex = i;
                if (hashTable[tableIndex].next == null) { // a single node
                    return hashTable[tableIndex++].keyword;
                }
                if (listIndex == null) { // need to return first node in the list.
                    listIndex = hashTable[tableIndex];
                    return listIndex.keyword;
                }
                if (listIndex.next == null){ // finished iterating through the list.
                    listIndex = null;
                    tableIndex++;
                } else { //need to keep iterating, returning every value along the way. 
                    listIndex = listIndex.next;
                    return listIndex.keyword;
                }
            }
        }
        tableIndex = 0; // resets tableIndex for another full traversal.
        return null; // returns null when entire traversal complete.
    }

    /**
     * Adds the key to the hash table.
     * If there is a collision, it should be dealt with by chaining the keys together.
     * If the key is already in the hash table, it increments that key's counter.
     * @param keyToAdd : the key which will be added to the hash table
     */
    public void insert(String keyToAdd){
        int hashVal = hash(keyToAdd);
        Node curNode = hashTable[hashVal];
        boolean added = false;
        if(curNode == null){ //no collision
            hashTable[hashVal] = new Node(keyToAdd);
        } else{ //collision
            while(curNode != null){ // iterates entire list.
                if(curNode.keyword.equals(keyToAdd)){ //if in list add to its count.
                    added = true;
                    curNode.count++;
                } // not at current index, keep traversing.
                listIndex = curNode;
                curNode = curNode.next;
            }
            if(!added){ //needs to be added to end of the linked list since not in the list.
                listIndex.next = new Node(keyToAdd);
            }
        }
        listIndex = null; // resets listIndex.
    }

    /**
     * Returns the number of times a key has been added to the hash table.
     * @param keyToFind : The key being searched for
     * @return returns the number of times that key has been added.
     */
    public int findCount(String keyToFind){
        int hashVal = hash(keyToFind);
        Node curNode = hashTable[hashVal];
        if(curNode != null) {
            if (curNode.keyword.equals(keyToFind)) { // first node is the target node.
                return curNode.count;
            } else { //target possibly in the linked list.
                while (curNode != null) { // iterates through the list.
                    if (curNode.keyword.equals(keyToFind)) { //found the node.
                        return curNode.count;
                    }
                    curNode = curNode.next;
                }
            }
        }
        return 0; // all other cases failed means not in list, count therefore 0.
    }

    /**
     * Hash function that returns the index in the array.
     * @param keyToHash
     * @return returns the hash value that the node key will be added to. Takes care of any
     * collisions by chaining the nodes together, forming a linked list out of the collision nodes.
     */
    private int hash(String keyToHash){
        int hashVal = 0;
        for(int i = 0; i < keyToHash.length(); i++){
            hashVal = HASH_CONSTANT * hashVal + keyToHash.charAt(i);
        }
        hashVal %= hashTable.length;
        if(hashVal < 0 ){
            hashVal += hashTable.length;
        }
        return hashVal;
    }

    private class Node {
        public String keyword;
        public int count;
        public Node next;

        public Node(String keyword){
            this.keyword = keyword;
            next = null;
            count = 1;
        }
    }
}
