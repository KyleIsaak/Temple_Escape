package gameLogic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {
    private boolean isPath(int[][] board, int x, int y) {
        return ((x >= 1 && x < 26) && (y >= 1 && y < 26)) && board[x][y] == 1;
    }

    private boolean BFS(int[][] board, int[] position) {
        boolean[][] visitedNode = new boolean[27][27];
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 27; j++) {
                visitedNode[i][j] = false;
            }
        }
        LinkedList<int[]> queue = new LinkedList<>();
        visitedNode[1][1] = true;
        queue.add(position);
        int[] current;
        while (queue.size() != 0) {
            current = queue.pop();
            if (isPath(board, current[0], current[1]) && (current[0] == position[0] && current[1] == position[1])) {
                return true;
            }
            if (isPath(board, current[0] + 1, current[1]) && !visitedNode[current[0] + 1][current[1]]) {
                visitedNode[current[0] + 1][current[1]] = true;
                queue.add(new int[]{current[0] + 1, current[1]});
            }
            if (isPath(board, current[0] - 1, current[1]) && !visitedNode[current[0] - 1][current[1]]) {
                visitedNode[current[0] - 1][current[1]] = true;
                queue.add(new int[]{current[0] - 1, current[1]});
            }
            if (isPath(board, current[0], current[1] + 1) && !visitedNode[current[0]][current[1] + 1]) {
                visitedNode[current[0]][current[1] + 1] = true;
                queue.add(new int[]{current[0], current[1] + 1});
            }
            if (isPath(board, current[0], current[1] - 1) && !visitedNode[current[0]][current[1] - 1]) {
                visitedNode[current[0]][current[1] - 1] = true;
                queue.add(new int[]{current[0], current[1] - 1});
            }
        }
        return false;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void ExitBoardPathwayTest(int level) {
        LevelGenerator testGenerator = new LevelGenerator(27, 27);
        switch (level) {
            case 1:
                testGenerator.setLevel(LevelGenerator.Difficulty.EASY);
                break;
            case 2:
                testGenerator.setLevel(LevelGenerator.Difficulty.MEDIUM);
                break;
            case 3:
                testGenerator.setLevel(LevelGenerator.Difficulty.HARD);
                break;
        }
        LocationRandomizerGenerator testLocationRandomizerGenerator = new LocationRandomizerGenerator(testGenerator.getBoard());
        testLocationRandomizerGenerator.randomizeExitPosition();
        assertTrue(BFS(testGenerator.getBoard(), testLocationRandomizerGenerator.getExit().getPosition()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void TrapBoardPathwayTest(int level) {
        LevelGenerator testGenerator = new LevelGenerator(27, 27);
        switch (level) {
            case 1:
                testGenerator.setLevel(LevelGenerator.Difficulty.EASY);
                break;
            case 2:
                testGenerator.setLevel(LevelGenerator.Difficulty.MEDIUM);
                break;
            case 3:
                testGenerator.setLevel(LevelGenerator.Difficulty.HARD);
                break;
        }
        LocationRandomizerGenerator testLocationRandomizerGenerator = new LocationRandomizerGenerator(testGenerator.getBoard());
        testLocationRandomizerGenerator.randomizeExitPosition();
        testLocationRandomizerGenerator.objectGenerator(level, "Trap");
        for (int i = 0; i < testLocationRandomizerGenerator.getTrapArrayManager().size(); i++) {
            assertTrue(BFS(testGenerator.getBoard(), testLocationRandomizerGenerator.getTrapArrayManager().get(i).getPosition()));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void RewardBoardPathwayTest(int level) {
        LevelGenerator testGenerator = new LevelGenerator(27, 27);
        switch (level) {
            case 1:
                testGenerator.setLevel(LevelGenerator.Difficulty.EASY);
                break;
            case 2:
                testGenerator.setLevel(LevelGenerator.Difficulty.MEDIUM);
                break;
            case 3:
                testGenerator.setLevel(LevelGenerator.Difficulty.HARD);
                break;
        }
        LocationRandomizerGenerator testLocationRandomizerGenerator = new LocationRandomizerGenerator(testGenerator.getBoard());
        testLocationRandomizerGenerator.randomizeExitPosition();
        testLocationRandomizerGenerator.objectGenerator(level, "Reward");
        for (int i = 0; i < testLocationRandomizerGenerator.getRewardArrayManager().size(); i++) {
            assertTrue(BFS(testGenerator.getBoard(), testLocationRandomizerGenerator.getRewardArrayManager().get(i).getPosition()));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void EnemyBoardPathwayTest(int level) {
        LevelGenerator testGenerator = new LevelGenerator(27, 27);
        switch (level) {
            case 1:
                testGenerator.setLevel(LevelGenerator.Difficulty.EASY);
                break;
            case 2:
                testGenerator.setLevel(LevelGenerator.Difficulty.MEDIUM);
                break;
            case 3:
                testGenerator.setLevel(LevelGenerator.Difficulty.HARD);
                break;
        }
        LocationRandomizerGenerator testLocationRandomizerGenerator = new LocationRandomizerGenerator(testGenerator.getBoard());
        testLocationRandomizerGenerator.randomizeExitPosition();
        testLocationRandomizerGenerator.objectGenerator(level, "Enemy");
        for (int i = 0; i < testLocationRandomizerGenerator.getEnemyArrayManager().size(); i++) {
            assertTrue(BFS(testGenerator.getBoard(), testLocationRandomizerGenerator.getEnemyArrayManager().get(i).getPosition()));
        }
    }
}
