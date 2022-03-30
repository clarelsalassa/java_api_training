package fr.lernejo.navy_battle;

import java.util.ArrayList;

public class BattleShips {
    final ArrayList<BoardCells> positions;

    public BattleShips(int nb, int X, int Y) {
        positions = new ArrayList<>(nb);
        if (Y > 7)
            Y = 7;
        if (X > 8)
            X = 8;
        for (int i = 0; i <  2; i++) {
            savePositions(X, Y + i);
        }
        X++;
        for (int i = 0; i < nb - 2; i++) {
            savePositions(X, Y + i);
        }
    }

    public BoardCells savePositions(int X, int Y) {
        BoardCells pos = new BoardCells(X, Y);
        positions.add(pos);
        return pos;
    }

    public boolean isLiving() {
        return !(positions.size() == 0);
    }

    public State isTouched(BoardCells boardCells) {
        if (positions.contains(boardCells)) {
            positions.remove(boardCells);
            if (isLiving())
                return State.HIT;
            else
                return State.SUNK;
        }
        return State.MISS;
    }
}
