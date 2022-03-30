package fr.lernejo.navy_battle;

public class BoardCells {
    final int x;
    final int y;
    public BoardCells(int x , int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object boardCell) {
        if (boardCell instanceof BoardCells && ((BoardCells) boardCell).x == this.x && ((BoardCells) boardCell).y == this.y) {
            return true;
        }
        return false;
    }
}
