package Game;
import Observer.PrintGame;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

//게임의 기본적인 세팅 그리드 사이즈 및 잠수함 위치시키기
public class GameHelper {
    private class Grid {
        int grid;
        String pos;
        boolean isUse = false;
        SubM sub=new SubM();
        Grid(int g,String p){
            grid=g;
            pos=p;
        }
        Grid() {
            grid=0;
            pos="null";
        }

    }

    private static final String alphabet="abcdefghijklmnopqrstuvwxyz";
    private static int gridLength;
    private int gridSize;
    private int SubCount =0;
    private Random rand=new Random(10);
    private ArrayList<Grid> grid =new ArrayList<Grid>(); //ArrayList를 통한 논리적 배열만들기
    GameHelper(){
        this(10);
    }
    GameHelper(int gridLength) {
        this.gridLength=gridLength;
        gridSize=gridLength*gridLength;
        SetGrid();

    }
    public static int getGrid(){ //그리드 사이즈를 얻어오는 메소드
        return gridLength;
    }





    private void SetGrid(){ //그리드 배치
            int row=0,cnt=0;
            String temp;
            for(row=0;row<gridLength;row++) {
                for (int i = 0; i < gridLength; i++) {
                    temp = String.valueOf(alphabet.charAt(row));
                    temp = temp.concat(String.valueOf(i));
                    grid.add(new Grid(cnt++,temp));
                }
            }
    }

    public void PlaceSubMarine(SubM marine) { //잠수함 배치
        int attempt= marine.getSize(),rand;
        int select;
        rand=(int)((Math.random()*10000)%(gridSize-1));
        System.out.println("랜덤수 지정 " + rand);
            if(grid.get(rand).isUse ==false) {
                System.out.println("미사용중확인");
                grid.get(rand).isUse=true;
                grid.get(rand).sub.setName(marine.getName());
                grid.get(rand).sub.setHeader();
                while(attempt-- !=0) {
                    select=(int)((Math.random()*10000)%3); //0상 1하 2좌 3우
                    System.out.println("선택값 " + select);
                    while(true) {
                        System.out.println("재선택됨" + select);
                        if(select ==0 && (rand <gridLength || grid.get(rand-gridLength).isUse) ) {
                            select=(int)((Math.random()*10000)%4);
                        }
                        else if(select ==1 && (rand >gridSize-gridLength || grid.get(rand+gridLength).isUse) ) {
                            select=(int)((Math.random()*10000)%4);
                        }
                        else if(select ==2 && (rand%gridLength ==0 || grid.get(rand-1).isUse) ) {
                            select=(int)((Math.random()*10000)%4);
                        }
                        else if(select ==3 && (rand%(gridLength-1) ==gridSize || grid.get(rand+1).isUse) ) {
                            select=(int)((Math.random()*10000)%4);
                        }
                        else {
                            switch(select) {
                                case 0:
                                    rand-=gridLength;
                                    grid.get(rand).isUse=true;
                                    grid.get(rand).sub.setName(marine.getName());
                                    break;
                                case 1:
                                    rand+=gridLength;
                                    grid.get(rand).isUse=true;
                                    grid.get(rand).sub.setName(marine.getName());
                                    break;
                                case 2:
                                    rand-=1;
                                    grid.get(rand).isUse=true;
                                    grid.get(rand).sub.setName(marine.getName());

                                    break;
                                case 3:
                                    rand+=1;
                                    grid.get(rand).isUse=true;
                                    grid.get(rand).sub.setName(marine.getName());
                                    break;
                            }
                            break;
                        }
                    }
                }


            }
            else{
                PlaceSubMarine(marine);
        }
    }
    public void printGrid(){ //그리드 출력
        int cnt=0;
        for(Grid i : grid) {
            cnt++;
            if(i.isUse==false) {
                System.out.printf("%3s ", i.pos);
            }
            else if(i.sub.getHeader()){
                System.out.printf("%3c ",'o');
            }
            else {
                System.out.printf("%3c ",'x');
            }
            if(cnt%gridLength==0)
                System.out.println();
        }

    }
}
