package Observer;

import Game.GameHelper;
//게임판 출력 및 현재상태 보고 출력
public class GameInfo  {
    public void update() {
        System.out.print(" ");
        for(int i=1;i<= GameHelper.getGrid();i++) {
            System.out.print(i+" ");
        }
    }

    }


