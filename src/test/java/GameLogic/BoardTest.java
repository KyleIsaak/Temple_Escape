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
    void numberOfTrapACreatedBasedOnLevel (int level){
        Board board = new Board(level);
        int numberofTrapACreated = 0;
        for (int i = 0; i < board.getTrapArrayManager().size(); i++){
            if (board.getTrapArrayManager().get(i).getType() == 'A'){
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


}

