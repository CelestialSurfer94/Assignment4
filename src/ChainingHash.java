public class ChainingHash {
    public static final int DEFAULT_SIZE = 29221;
    private Node[] hashTable;
    private int curIndex;
    private Node listIndex;
    public int numCollisions;

    public ChainingHash(){
        this(DEFAULT_SIZE);
    }
		
    public ChainingHash(int startSize){
        curIndex = 0;
        listIndex = null;
        numCollisions = 0;
        hashTable = new Node[startSize];
    }

    /**
     * This function allows rudimentary iteration through the ChainingHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey(){
        for(int i = curIndex; i < hashTable.length; i++) {
            if(hashTable[i] != null) {
                curIndex = i;
                if (hashTable[curIndex].next == null) { // a single node
                    return hashTable[curIndex++].keyword;
                }
                if (listIndex == null) { // need to return first node in the list.
                    listIndex = hashTable[curIndex];
                    return listIndex.keyword;
                }
                if (listIndex.next == null){ // finished iterating through the list.
                    listIndex = null;
                    curIndex++;
                } else {
                    listIndex = listIndex.next;
                    return listIndex.keyword;
                }
            }
        }
        curIndex = 0;
        return null;
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
            while(curNode != null){
                if(curNode.keyword.equals(keyToAdd)){
                    added = true;
                    curNode.count++;
                }
                listIndex = curNode;
                curNode = curNode.next;
            }
            if(!added){ //needs to be added to end of the linked list
                listIndex.next = new Node(keyToAdd);
                numCollisions++;
            }
        }
        listIndex = null;
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
            } else { //target node somewhere in the linked list.
                while (curNode != null) {
                    if (curNode.keyword.equals(keyToFind)) {
                        return curNode.count;
                    }
                    curNode = curNode.next;
                }
            }
        }
        return 0;
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
            hashVal = 11 * hashVal + keyToHash.charAt(i);
        }
        hashVal %= hashTable.length;
        if(hashVal < 0 ){
            hashVal += hashTable.length;
        }
        return hashVal;
    }

    public double loadFactor(){
        int count = 0;
        for(int i = 0; i < hashTable.length; i++){
            if(hashTable[i] != null){
                count++;
            }
        }
        return (double) count/hashTable.length;
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
