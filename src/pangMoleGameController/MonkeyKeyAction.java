package pangMoleGameController;

import pangMoleGameModel.Monkey;

public class MonkeyKeyAction {
    private Monkey monkey;
    private boolean isMoveLeft, isMoveRight;

    public MonkeyKeyAction(Monkey monkey) {
        this.monkey = monkey;
    }

    public void setMoveLeft(boolean moveLeft) {
        isMoveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        isMoveRight = moveRight;
    }

    public void action(){
        if (isMoveLeft) monkey.goLeft();
        else if (isMoveRight) monkey.goRight();
    }
}
