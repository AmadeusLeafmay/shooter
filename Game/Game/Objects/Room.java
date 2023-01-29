package Game.Objects;

public class Room {
    private int size;
    private boolean chest;
    private boolean enemy;
    private boolean leftDoor;
    private boolean rightDoor;
    public Room(){
        generateRoom();
    }
    private void generateRoom(){
        size = (int)(1 + Math.random()*3);
        if(Math.random() < 0.1f){
            chest = true;
        }
        if(Math.random() < 0.2f){
            enemy = true;
        }
        if(Math.random() < 0.3f){
            leftDoor = true;
        }
        if(Math.random() < 0.3f){
            rightDoor = true;
        }
    }
    public int getSize(){
        return size;
    }
    public boolean getChest(){
        return chest;
    }
    public boolean getEnemy(){
        return enemy;
    }
    public boolean getLeftDoor(){
        return leftDoor;
    }
    public boolean getRightDoor(){
        return rightDoor;
    }
}

