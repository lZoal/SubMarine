package Game;
import Observer.GameInfo;
//게임의 메인함수 ; 다른객체를 불러와 실행 GameBust 클래스 이용
public class Launcher {
    public static void main(String[] args){
//        GameBust game= new GameBust();
//        game.SetUpGame();
//        game.StartPlaying();
        GameHelper game = new GameHelper(10);
        SubM test= new SubM(),com1=new SubM(),com2=new SubM();
        test.setName("test");
        game.PlaceSubMarine(test);

        game.printGrid();
        game.GridAttack(test);
        game.MoveSubMarine(test);
        game.printGrid();
        game.MoveSubMarine(test);
        game.GridAttack(test);
        game.printGrid();
    }
}
