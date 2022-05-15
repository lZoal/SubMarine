package Observer;
import Game;
// 피해량 출력
public class DmgRate {
    // SubM 과 enemySubM 클래스 호출
    SubM mysub = new SubM();
    enemySubM enemy = new enemySubM();

    // 임시 변수 생성(다른 클래스에서 호출받을 것들)
    int mycell;
    int enemycell;

    // 공격이 상대방에게 맞았다면
    public void attack() {

        if (enemycell > 0) {
            // 공격 성공했을때의 적 함선의 줄어든 체력 계산 및 남은 체력 출력
            enemy.enemycell = enemy.enemycell - 2;
            System.out.println("적 함대의 체력이 2만큼 닳았습니다");
            System.out.println("적 함대의 남은 체력 : " + enemycell);

            // 적 함대의 체력이 없다면
            if (enemy.enemycell < 1) {
                System.out.println("적 함대가 쓰러졌습니다");
                
                // 적 함대의 수가 없다면 승리 출력
                if (enemy.enemyNum < 1) {
                    System.out.println("Victory!");
                }
            }
        }
    }

    // 내가 공격을 당했다면
    public void attacked() {

        if (mycell > 0) {
            // 공격 당했을때의 줄어든 체력 계산 및 남은 체력 출력
            mysub.mycell = mysub.mycell - 1;
            System.out.println("내 함대의 체력이 1만큼 닳았습니다");
            System.out.println("내 함대의 남은 체력 : " + mycell);

            // 내 체력이 다 닳았다면 패배 출력
            if (mysub.mycell < 1) {
                System.out.println("Lose..");

                }
            }
        }
    }
}
