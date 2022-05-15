package Game;
import Observer.PrintGame;
import java.util.Scanner;
import java.util.ArrayList;
//게임의 기본적인 세팅 그리드 사이즈 및 잠수함 위치시키기
public class GameHelper {
    private static final String alphabet="abcdefghijklmnopqrstuvwxyz";
    private int girdLength;
    private int gridSize;

    private ArrayList<String> grid =new ArrayList<String>();
    GameHelper(){
        this(10);
    }
    GameHelper(int girdLength) {
        this.girdLength=girdLength;
        gridSize=girdLength*girdLength;
        SetGrid();
    }

    private void SetGrid(){ //그리드 배치
            int row=0;
            String temp;
            for(;row<girdLength;row++) {
                for(int i=1;i< girdLength+1;i++) {
                    temp=String.valueOf(alphabet.charAt(row));
                   temp = temp.concat(String.valueOf(i));
                    grid.add(temp);
                }
             }

    }
    public void printGrid(){ //그리드 출력
        int cnt=0;
        for(String i: grid) {
            cnt++;

            System.out.print(i + " ");
            if(cnt%10==0)
                System.out.println();
        }

    }

}
