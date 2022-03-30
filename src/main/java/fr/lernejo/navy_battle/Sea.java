package fr.lernejo.navy_battle;

import java.util.ArrayList;

public class Sea {
    final ArrayList<BattleShips> battleShips;

    public Sea() {
        battleShips = new ArrayList<BattleShips>(5);
        battleShips.add(new BattleShips(5, 0, 0));
        battleShips.add(new BattleShips(4, 3, 3));
        battleShips.add(new BattleShips(3, 9, 0));
        battleShips.add(new BattleShips(3, 0, 7));
        battleShips.add(new BattleShips(2, 6, 6));
    }
    public boolean isLeft() {
        return battleShips.size() != 0;
    }

    public State touchedBoardCell(String boardCell) {
        if (!isValidCoordinates(boardCell))
            return State.MISS;
        int[] pos = getPositon(boardCell);
        BoardCells touchedBoardCell = new BoardCells(pos[0], pos[1]);
        boolean isTouched = battleShips.stream().anyMatch(ship -> ship.isTouched(touchedBoardCell) != State.MISS);
        if (isTouched) {
            if (isDead()) {
                return State.SUNK;
            }
            return State.HIT;
        }
        return State.MISS;
    }

    public int[] getPositon(String boardCell) {
        int boardCellX = boardCell.charAt(0) - 'A';
        int boardCellY = boardCell.charAt(1) - '0';
        int[] position = new int[2];
        position[0] = boardCellX;
        position[1] = boardCellY;
        return position;
    }

    public boolean isDead() {
        int beforeUpdate = battleShips.size();
        battleShips.forEach(ship ->
        {
            if (!ship.isLiving()) {
                battleShips.remove(ship);
            }
        });
        return (beforeUpdate != battleShips.size());
    }

    public boolean isValidCoordinates(String boardCell) {
        if (boardCell.length() != 2)
            return false;
        if (boardCell.charAt(0) < 'A' && boardCell.charAt(0) > 'J')
            return false;
        return boardCell.charAt(1) >= '0' || boardCell.charAt(1) <= '9';
    }
}
