package GameLogic;
import GameLogic.*;
import UI.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest{

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    void numberOfTrapACreatedBasedOnLevel (int level) {
        Board board = new Board(level);
        int numberofTrapACreated = 0;
        for (int i = 0; i < board.getTrapArrayManager().size(); i++) {
            if (board.getTrapArrayManager().get(i).getType() == 'A') {
                numberofTrapACreated++;
            }
        }
        assertEquals(level * 2, numberofTrapACreated);
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    void numberOfTrapBCreatedBasedOnLevel (int level){
        Board board = new Board(level);
        int numberofTrapBCreated = 0;
        for (int i = 0; i < board.getTrapArrayManager().size(); i++){
            if (board.getTrapArrayManager().get(i).getType() == 'B'){
                numberofTrapBCreated++;
            }
        }
        assertEquals(level, numberofTrapBCreated);
    }

    @Test
    void trapLocationBounds (){
        Board board = new Board(3);
        boolean InBounds = true;
        for (int i = 0; i < board.getTrapArrayManager().size(); i++){
            int[] position = board.getTrapArrayManager().get(i).getPosition();
            if ((position[0] < 0 || position[0] >= 27) || (position[1] < 0 || position[1] >= 27)){
                InBounds = false;
            }
        }
        assertEquals(true, InBounds);
    }

     @Test
    void totalDamageDeductionCheck (){
        Board board = new Board(1);
        int intialScore = 500;
        for (int i = 0; i < 27; i++){
            for (int j = 0; j < 27; j++){
                if (board.isTrap(i,j)){
                    int trapIndex = board.trapFinder(i, j);
                    intialScore = intialScore - board.getTrapArrayManager().get(trapIndex).getDamage();
                }
            }
        }
        assertEquals(0,intialScore);
    }
}

