package Observer;
import Game;
// 피해량 출력
public class DmgRate {
    String player;
    int cell;
    int enemycell;

    //생성자
    public DmgRate(String p)  {

        player = p;
    }

    // 공격이 상대방에게 맞았다면
    public void attack(enemySubM enemy) {
        if ( enemycell > 0 ) {

            enemy.enemycell  = enemy.enemycell - 2;
            System.out.println("적 함대의 체력이 2만큼 닳았습니다");
            System.out.println("적 함대의 남은 체력 : " + enemycell);

            if (enemy.enemycell < 1) {
                System.out.println("적 함대가 쓰러졌습니다");

                if (enemy < 0) {
                    System.out.println("Victory!");
                }
            }
        }
    }
}
