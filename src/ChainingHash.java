public class ChainingHash {
    public static final int DEFAULT_SIZE = 48299;
    private int tableSize;
    private Node[] hashTable;
    private int curIndex;
    private Node tracker;

    public ChainingHash(){
        this(DEFAULT_SIZE);
    }
		
    public ChainingHash(int startSize){
        tableSize = startSize;
        curIndex = 0;
        tracker = null;
        hashTable = new Node[tableSize];
    }

    /**
     * This function allows rudimentary iteration through the ChainingHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey(){
        for (int i = curIndex; i < tableSize; i++) {
            Node curNode = hashTable[i];
            if (curNode != null) { // a non null node exists at current node.
                if (curNode.next == null) { // a single node
                    curIndex = i + 1;
                    return curNode.keyword;
                } else { //multiple nodes... linked list.
                    if (tracker == null) { //tracker not initialized
                        tracker = curNode;
                        curIndex = i;
                        return tracker.keyword;
                    } else { // tracker initialized. Need to return next node in the list.
                        if (tracker.next == null) { // tracker is on last node in the list.
                            tracker = null;
                            curIndex = i + 1;
                        } else { // more nodes in the linked list exist.
                            tracker = tracker.next;
                            return tracker.keyword;
                        }
                    }
                }
            }
        }
        curIndex = 0;
        tracker = null;
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
        if(curNode == null){ //empty linked list, no collision MADE CHANGE HERE WAS CURNODE.KEYWORD******
            hashTable[hashVal] = new Node(keyToAdd);
        } else if (curNode.keyword.equals(keyToAdd)) {//non empty linked list, no collision.
            curNode.count++;
        } else { //collision. Somewhere in the linked list.
            Node temp = curNode;
            boolean added = false;
            while(temp.next != null){ //iterates through linked list.
                if(temp.next.keyword.equals(keyToAdd)){ //found the target node in the list.
                    temp.next.count++;
                    added = true;
                }
                temp = temp.next;
            }
            if(!added){ // not in linked list, so append to the end of list.
                temp.next = new Node(keyToAdd);
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
        if(curNode  != null) {
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
        // no result return 0
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
