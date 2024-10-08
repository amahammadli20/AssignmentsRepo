public class Player {
    // numPlayers variable refers to the number of players
    public int numPlayers;

    // head points to the initial node 
    private SeLinkList head;
    private int currentIndex; // Track the current index for nodes

    // constructor method - modified
    public Player(int teamPlayersCnt) {
        this.numPlayers = teamPlayersCnt;
        this.head = null; // we set our initial node as null
        this.currentIndex = 0; // Initialize index
    }

    // addPlayer() method to add 
    public void addPlayer(int a, int b, int c) {
        // defining our next node with the current index
        SeLinkList nextNode = new SeLinkList(a, b, c, currentIndex);
        nextNode.next = head;
        nextNode.prev = null;
        if (head != null) { // it means it is not a starting point and its prev is not null
            head.prev = nextNode;
        }
        // updating head by assigning nextNode's value to the initial node
        head = nextNode;
        currentIndex++;
    }

    // initializeList() method which takes array of players and pass each player's data to addPlayer() method as an argument
    public void initializeList(PlayerData[] playersArr) {
        for (int i = 0; i < playersArr.length; i++) {
            addPlayer(playersArr[i].a, playersArr[i].b, playersArr[i].c);
        }

        System.out.println("Total number of participants in the list is " + playersArr.length);
    }

    // Method to find the node with the maximum weight
    public SeLinkList findMaxWeight() {
        SeLinkList presentNode = head;
        SeLinkList nodeWithMaxWeight = null; // node with the highest weight
        int maxWeight = Integer.MIN_VALUE; // we define maxWeight in which we can compare and store the max weight value later
        
        while (presentNode != null) {
            int totalWeight = presentNode.a + presentNode.b + presentNode.c;
            if (totalWeight > maxWeight) { // if total weight is higher than our max weight, we update our max weight, node
                maxWeight = totalWeight;
                nodeWithMaxWeight = presentNode;
            }
            presentNode = presentNode.next; 
        }

        if (nodeWithMaxWeight != null) {
            // printing max weight after looping whole array and finding the highest weight
            System.out.println("Max weight player >> Node (index: " + nodeWithMaxWeight.ind + ", its values: " + nodeWithMaxWeight.a + ", " + nodeWithMaxWeight.b + ", " + nodeWithMaxWeight.c + ") and its weight: " + maxWeight);
        }

        return nodeWithMaxWeight;
    }

    // Method to find the node with the minimum weight
    public SeLinkList findMinWeight() {
        SeLinkList presentNode = head;
        SeLinkList nodeWithMinWeight = null; // node with the lowest weight
        int minWeight = Integer.MAX_VALUE; // we define minWeight in which we can compare and store the min weight value later

        while (presentNode != null) {
            int totalWeight = presentNode.a + presentNode.b + presentNode.c;
            if (totalWeight < minWeight) { // if total weight is lower than our min weight, we update our min weight, node
                minWeight = totalWeight;
                nodeWithMinWeight = presentNode;
            }
            presentNode = presentNode.next; 
        }

        if (nodeWithMinWeight != null) {
            System.out.println("Min weight player >> Node (index: " + nodeWithMinWeight.ind + ", its values: " + nodeWithMinWeight.a + ", " + nodeWithMinWeight.b + ", " + nodeWithMinWeight.c + ")");
        }

        return nodeWithMinWeight;
    }

    // Method to find both min and max weights
    public void findMinMaxWeight() {
        SeLinkList maxNode = findMaxWeight();
        SeLinkList minNode = findMinWeight();

        if (maxNode != null) {
            System.out.println("Max weight: " + (maxNode.a + maxNode.b + maxNode.c));
        }
        if (minNode != null) {
            System.out.println("Min weight: " + (minNode.a + minNode.b + minNode.c));
        }
    }

    // removeNode() method to remove nodes based on the node reference
    public void removeNode(SeLinkList node) {
        if (node == null) return;

        // linking nodes based on conditions
        if (node.prev != null) { 
            node.prev.next = node.next; 
        } else {
            head = node.next; // if the head to be deleted
        }

        if (node.next != null) {
            node.next.prev = node.prev; 
        }

        System.out.println("Deleted values of the node: " + node.a + ", " + node.b + ", " + node.c);
    }

    // additional removeMinMaxPlayers() method to delete min and max nodes from the list
    public void removeMinMaxPlayers() {
        SeLinkList maxNode = findMaxWeight();
        SeLinkList minNode = findMinWeight();

        if (maxNode != null) {
            System.out.println("Deleting max node - its values: " + maxNode.a + ", " + maxNode.b + ", " + maxNode.c);
            removeNode(maxNode);
        }
        
        if (minNode != null) {
            System.out.println("Deleting min node - its values: " + minNode.a + ", " + minNode.b + ", " + minNode.c);
            removeNode(minNode);
        }
    }

    public void displayRemainingPlayers() {
        SeLinkList presentNode = head;
        int playerSequence = 1;

        System.out.println("Final remaining players after removing min and max nodes:");
        while (presentNode != null) {
            System.out.println("Number " + playerSequence + " Player: " + presentNode.a + " " + presentNode.b + " " + presentNode.c);
            presentNode = presentNode.next;
            playerSequence++;
        }
    }

    public static void main(String[] args) {
        // creating an object from the Player class
        Player teamPlayers = new Player(11);

        // printing the number of players in the team
        System.out.println("Number of players in the team: " + teamPlayers.numPlayers);

        // creating an object from PlayerData class
        PlayerData playerRecords = new PlayerData();

        // getting players' info array by calling getMyData() method
        PlayerData[] playerRecordsArr = playerRecords.getMyData();

        // printing each object data of the array
        for (int i = 0; i < playerRecordsArr.length; i++) {
            PlayerData eachPlayerObj = playerRecordsArr[i];
            System.out.println("Player number " + (i + 1) + ": " + eachPlayerObj.a + " " + eachPlayerObj.b + " " + eachPlayerObj.c);
        }

        // -------------------------------------------------------------------------------------
        // TASK 7 - new added parts
        // in every addPlayer() call, it maintains one node which is linked to another
        teamPlayers.addPlayer(5, 15, 25);
        teamPlayers.addPlayer(7, 17, 27);

        // printing each player node in order
        SeLinkList presentNode = teamPlayers.head; // making our present node head and beginning from this point
        for (int playerOrder = 1; presentNode != null; playerOrder++) {
            System.out.println("Number " + playerOrder + " Player: " + presentNode.a + " " + presentNode.b + " " + presentNode.c);
            presentNode = presentNode.next; // updating our current position in node
        }

        // -------------------------------------------------------------------------------------
        // Task 8 - added part
        teamPlayers.initializeList(playerRecordsArr);
        // -------------------------------------------------------------------------------------
        // Task 9 - added part
        teamPlayers.findMaxWeight();
        // -------------------------------------------------------------------------------------
        // Task 12 - added part
        teamPlayers.removeNode(teamPlayers.head); // deleting the first node
        // -------------------------------------------------------------------------------------
        // Task 13 - added part
        teamPlayers.findMinWeight();
        // -------------------------------------------------------------------------------------
        // Task 14 - added part
        teamPlayers.findMinMaxWeight();
        // -------------------------------------------------------------------------------------
        // Task 16 - added part - removing players with min and max weight
        teamPlayers.removeMinMaxPlayers();
        // displaying the remaining players after deletion
        teamPlayers.displayRemainingPlayers();
        // -------------------------------------------------------------------------------------
        System.out.println("[Done!]");
        System.out.println("[Aytaj Mahammadli - Git Assignment]");
    }
}
