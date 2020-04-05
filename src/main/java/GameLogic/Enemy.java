package GameLogic;

/**
 * Represents the enemy that chases the player in the game.
 */
public class Enemy {
    private int[] position;

    /**
     * Default Constructor of Enemy Class
     */
    public Enemy() { this.position = new int[]{0, 0}; }
    /**
     * Non default constructor of Enemy Class
     * @param position A x,y position on the Map
     */
    public Enemy(int[] position) { this.position = position; }

    /**
     * Get the current position of the enemy
     * @return this enemy object's current position
     */
    public int[] getPosition() { return this.position; }

    /**
     * Move the enemy one step from the current direction in a certain direction
     * @param direction An x,y position in the map
     */
    public void move(int[] direction) {
        this.position[0] += direction[0];
        this.position[1] += direction[1];
    }

    /**
     * Set the position of this enemy class
     * @param position A x,y position in the map for the enemy's current position to set as.
     */
    public void setPosition(int[] position) {
        this.position = position;
    }

    /**
     * Provide the enemy with the functionality to chase the player.
     * @param position An x,y current position of the player
     * @return a new x,y position for this enemy class to move to.
     */
    public int[] chaseThePlayer(int[] position) {
        int[] chase;
        int step = 1;
        chase = new int[]{0,0};
        double distanceX= position[0]-this.position[0];//enemy - player location;
        double distanceY= position[1]-this.position[1];//enemy - player location;
        if( Math.abs(distanceY) > Math.abs(distanceX))//distance Y> distance X
        {
            if(this.position[1]<position[1]){
                chase[0] = 0;
                chase[1] = step;
            }
            else if(this.position[1]>position[1]){
                chase[0] = 0;
                chase[1] = - step;
            }
            else{
                chase[0]=0;
                chase[1]=0;
            }
        }
        else{
            if(this.position[0]<position[0]){
                chase[0] = step;
                chase[1] = 0;
            }
            else if(this.position[0]>position[0]){
                chase[0] = -step;
                chase[1] =  0;
            }
            else {
                chase[0] = 0;
                chase[1] = 0;
            }
        }
        return chase;
    }
}
