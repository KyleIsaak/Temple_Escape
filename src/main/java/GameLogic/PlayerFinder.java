package GameLogic;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerFinder {
    private static class Node{
        public int[] position;
        public Node parent;
        public Node (int[] position, Node parent) {
            this.position = position;
            this.parent = parent;
        }
    }

    private boolean isPath (int[][] board, int x, int y){
        return ((x >= 1 && x < 26) && (y >= 1 && y < 26)) && board[x][y] == 1;
    }

    public Node BFS (int[][] board, int[] initialPosition, int[] destinationPosition){
        boolean[][] visitedNode = new boolean[27][27];
        for (int i = 0 ; i < 27; i ++){
            for (int j = 0; j < 27; j++){
                visitedNode[i][j] = false;
            }
        }
        LinkedList<Node> queue = new LinkedList<>();
        visitedNode[initialPosition[0]][initialPosition[1]] = true;
        queue.add(new Node (initialPosition, null));
        Node current;
        while (queue.size() != 0){
            current = queue.pop();
            if (isPath(board, current.position[0], current.position[1]) && (current.position[0] == destinationPosition[0] && current.position[1] == destinationPosition[1])){
                return new Node (new int[]{current.position[0], current.position[1]}, current);
            }
            if (isPath(board, current.position[0] + 1, current.position[1]) && !visitedNode[current.position[0] + 1][current.position[1]]){
                visitedNode[current.position[0] + 1][current.position[1]] = true;
                queue.add(new Node(new int[]{current.position[0] + 1, current.position[1]}, current));
            }
            if (isPath(board, current.position[0] - 1, current.position[1]) && !visitedNode[current.position[0] - 1][current.position[1]]){
                visitedNode[current.position[0] - 1][current.position[1]] = true;
                queue.add(new Node(new int[]{current.position[0] - 1, current.position[1]}, current));
            }
            if (isPath(board, current.position[0], current.position[1] + 1) && !visitedNode[current.position[0]][current.position[1] + 1]){
                visitedNode[current.position[0]][current.position[1] + 1] = true;
                queue.add(new Node(new int[]{current.position[0], current.position[1] + 1}, current));
            }
            if (isPath(board, current.position[0], current.position[1] - 1) && !visitedNode[current.position[0]][current.position[1] - 1]){
                visitedNode[current.position[0]][current.position[1] - 1] = true;
                queue.add(new Node(new int[]{current.position[0], current.position[1] - 1}, current));
            }
        }
        return null;
    }
    public int[] shortPathStep (int[][] board, int[] initialPosition, int[] destinationPosition){
        Node shortestPath = BFS (board, initialPosition, destinationPosition);
        int[] current = new int[2];
        while (shortestPath != null){
            shortestPath = shortestPath.parent;
            current = shortestPath.position;
        }
        return current;
    }
}
