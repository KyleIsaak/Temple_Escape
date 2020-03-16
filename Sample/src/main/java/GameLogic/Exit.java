package GameLogic;

public class Exit {
    private int[] position;
    private boolean isUnlocked;

    public Exit(){ isUnlocked = false; }
    public Exit(int[] position){ this.position = position; }

    public int[] getPosition() { return position; }
    public boolean getIsUnlocked() { return isUnlocked; }

    public void setPosition(int[] position){ this.position = position; }
    public void setUnlocked(boolean isUnlocked) { this.isUnlocked = isUnlocked; }

}
