package gameLogic;


import java.util.LinkedList;

/**
 * Find the nearest path from the initial position to player position in the board using Breadth First Search
 */
public class PlayerFinder {
    /**
     * Helper Class for Player Finder: Provide the functionality to go back to the previous position of the current position
     */
    private static class Node {
        public int[] position;
        public Node parent;

        /**
         * Non-Default Constructor
         *
         * @param position : x,y position
         * @param parent   : the incident node of the current node.
         */
        public Node(int[] position, Node parent) {
            this.position = position;
            this.parent = parent;
        }
    }

    /**
     * Breadth First Search Helper Function: Check whether the position is in the boundary and is a path.
     *
     * @param board A matrix that holds the map.
     * @param x     A latitudinal position on the map.
     * @param y     A longditudinal position on the map.
     * @return true if the position is bounded in the map and it is a path, vise versa.
     */
    private boolean isPath(int[][] board, int x, int y) {
        return ((x >= 1 && x < 26) && (y >= 1 && y < 26)) && board[x][y] == 1;
    }

    /**
     * Breadth First Search Algorithm that finds the shortest path to the player from current position.
     *
     * @param board               A matrix that holds the map.
     * @param initialPosition     The current position of the enemy.
     * @param destinationPosition The position of the player.
     * @return A Node class that holds the shortest path.
     */
    private Node BFS(int[][] board, int[] initialPosition, int[] destinationPosition) {
        boolean[][] visitedNode = new boolean[27][27];
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 27; j++) {
                visitedNode[i][j] = false;
            }
        }
        LinkedList<Node> queue = new LinkedList<>();
        visitedNode[initialPosition[0]][initialPosition[1]] = true;
        queue.add(new Node(initialPosition, null));
        Node current;
        while (queue.size() != 0) {
            current = queue.pop();
            if (isPath(board, current.position[0], current.position[1]) && (current.position[0] == destinationPosition[0] && current.position[1] == destinationPosition[1])) {
                return new Node(new int[]{current.position[0], current.position[1]}, current);
            }
            if (isPath(board, current.position[0] + 1, current.position[1]) && !visitedNode[current.position[0] + 1][current.position[1]]) {
                visitedNode[current.position[0] + 1][current.position[1]] = true;
                queue.add(new Node(new int[]{current.position[0] + 1, current.position[1]}, current));
            }
            if (isPath(board, current.position[0] - 1, current.position[1]) && !visitedNode[current.position[0] - 1][current.position[1]]) {
                visitedNode[current.position[0] - 1][current.position[1]] = true;
                queue.add(new Node(new int[]{current.position[0] - 1, current.position[1]}, current));
            }
            if (isPath(board, current.position[0], current.position[1] + 1) && !visitedNode[current.position[0]][current.position[1] + 1]) {
                visitedNode[current.position[0]][current.position[1] + 1] = true;
                queue.add(new Node(new int[]{current.position[0], current.position[1] + 1}, current));
            }
            if (isPath(board, current.position[0], current.position[1] - 1) && !visitedNode[current.position[0]][current.position[1] - 1]) {
                visitedNode[current.position[0]][current.position[1] - 1] = true;
                queue.add(new Node(new int[]{current.position[0], current.position[1] - 1}, current));
            }
        }
        return null;
    }

    /**
     * Provide the functionality to find the shortest path to the player from current position.
     *
     * @param board               A matrix that holds the map.
     * @param initialPosition     The current position of the enemy.
     * @param destinationPosition The position of the player.
     * @return an x, y position for the next step that should be taken for shortest path to player.
     */
    public int[] shortPathStep(int[][] board, int[] initialPosition, int[] destinationPosition) {
        Node shortestPath = BFS(board, initialPosition, destinationPosition);
        int[] current = new int[2];
        if (shortestPath != null) {
            while (shortestPath.parent.parent != null) {
                shortestPath = shortestPath.parent;
                current = shortestPath.position;
            }
        } else {
            throw new NullPointerException("ERROR: No shortest path found for enemy to go to player. Please check maze.");
        }
        return current;
    }
}
