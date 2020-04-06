package GameLogic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {
    private boolean isPath (int[][] board, int x, int y){
        return (x >= 1 && x <= 25) && (y >= 1 && y <= 25) && board[x][y] == 1;
    }
    private boolean BFS (int[][] board, int[] position){
        System.out.println(position[0] +", " + position[1]);
        for (int y = 0; y < 27; y++) {
            for (int x = 0; x < 27; x++) {
                System.out.print(board[x][y]);
            }
            System.out.println();
        }
        boolean[][] visitedNode = new boolean[27][27];
        for (int i = 0 ; i < 27; i ++){
            for (int j = 0; j < 27; j++){
                visitedNode[i][j] = false;
            }
        }
        LinkedList<int[]> queue = new LinkedList<>();
        visitedNode[1][1] = true;
        queue.add(position);
        int[] current;
        while (queue.size() != 0){
            current = queue.poll();
            if (isPath(board, current[0], current[1]) && (current[0] == position[0] && current[1] == position[1])){
                return true;
            }
            if (isPath(board, current[0] + 1, current[1]) && !visitedNode[current[0] + 1][current[1]]){
                visitedNode[current[0] + 1][current[1]] = true;
                queue.add(new int[] {current[0] + 1, current[1]});
            }
            if (isPath(board, current[0] - 1, current[1]) && !visitedNode[current[0] - 1][current[1]]){
                visitedNode[current[0] - 1][current[1]] = true;
                queue.add(new int[] {current[0] - 1, current[1]});
            }
            if (isPath(board, current[0], current[1] + 1) && !visitedNode[current[0]][current[1] + 1]){
                visitedNode[current[0]][current[1] + 1] = true;
                queue.add(new int[] {current[0], current[1] + 1});
            }
            if (isPath(board, current[0], current[1] - 1) && !visitedNode[current[0]][current[1] - 1]){
                visitedNode[current[0]][current[1] - 1] = true;
                queue.add(new int[] {current[0], current[1] - 1});
            }
        }
        return false;
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2 ,3})
    public void ExitBoardPathwayTest(int level){
        LevelGenerator testGenerator = new LevelGenerator(27, 27);
        switch (level){
            case 1 :
                testGenerator.setLevel(LevelGenerator.Difficulty.EASY);
                break;
            case 2 :
                testGenerator.setLevel(LevelGenerator.Difficulty.MEDIUM);
                break;
            case 3 :
                testGenerator.setLevel(LevelGenerator.Difficulty.HARD);
                break;
        }
        LocationRandomizerGenerator testLocationRandomizerGenerator = new LocationRandomizerGenerator(testGenerator.getBoard());
        testLocationRandomizerGenerator.randomizeExitPosition();
        testLocationRandomizerGenerator.getExit().setPosition(new int[]{25,22});
        assertTrue(BFS(testGenerator.getBoard(), testLocationRandomizerGenerator.getExit().getPosition()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2 ,3})
    public void TrapBoardPathwayTest(int level){
        LevelGenerator testGenerator = new LevelGenerator(27, 27);
        switch (level){
            case 1 :
                testGenerator.setLevel(LevelGenerator.Difficulty.EASY);
                break;
            case 2 :
                testGenerator.setLevel(LevelGenerator.Difficulty.MEDIUM);
                break;
            case 3 :
                testGenerator.setLevel(LevelGenerator.Difficulty.HARD);
                break;
        }
        LocationRandomizerGenerator testLocationRandomizerGenerator = new LocationRandomizerGenerator(testGenerator.getBoard());
        testLocationRandomizerGenerator.randomizeExitPosition();
        testLocationRandomizerGenerator.objectGenerator(level,"Trap");
        for (int i = 0; i < testLocationRandomizerGenerator.getTrapArrayManager().size(); i++){
            assertTrue(BFS(testGenerator.getBoard(), testLocationRandomizerGenerator.getTrapArrayManager().get(i).getPosition()));
        }
    }
}
