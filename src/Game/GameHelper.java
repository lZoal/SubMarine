package Game;

import java.util.*;

//게임의 기본적인 세팅 그리드 사이즈 및 잠수함 위치시키기
public class GameHelper {
    private static class Grid {
        int grid;
        String pos;
        boolean isUse = false;
        SubM sub=new SubM();
        Grid(int g,String p){
            grid=g;
            pos=p;
        }
        Grid next;
        public void Use(){isUse= !isUse;}
        public void setNext(Grid temp) { next= temp;}
        public Grid getNext() {return next;}

    }

    private static final String alphabet="abcdefghijklmnopqrstuvwxyz";
    private static int gridLength;
    private final int gridSize;
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
                    grid.add(new Grid(cnt++, temp));
                }
            }
    }

    public void PlaceSubMarine(SubM marine) { //잠수함 배치
        int attempt= marine.getSize(),rand;
        int select;
        rand=(int)((Math.random()*10000)%(gridSize-1));
        System.out.println("랜덤수 지정 " + rand);
            if(!grid.get(rand).isUse) {
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
                            switch (select) {
                                case 0 -> {
                                    grid.get(rand).setNext(grid.get(rand-gridLength));
                                    rand -= gridLength;
                                    grid.get(rand).isUse = true;
                                    grid.get(rand).sub.setName(marine.getName());
                                }
                                case 1 -> {
                                    grid.get(rand).setNext(grid.get(rand+gridLength));
                                    rand += gridLength;
                                    grid.get(rand).isUse = true;
                                    grid.get(rand).sub.setName(marine.getName());
                                }
                                case 2 -> {
                                    grid.get(rand).setNext(grid.get(rand-1));
                                    rand -= 1;
                                    grid.get(rand).isUse = true;
                                    grid.get(rand).sub.setName(marine.getName());
                                }
                                case 3 -> {
                                    grid.get(rand).setNext(grid.get(rand+1));
                                    rand += 1;
                                    grid.get(rand).isUse = true;
                                    grid.get(rand).sub.setName(marine.getName());
                                }
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
    public void MoveSubMarine(SubM marine){
        Scanner sc=new Scanner(System.in);
        char[] move =new char[marine.getMove()+1];
        System.out.println("이동키 입력 [w: 위, s: 아래, a: 좌, d: 우]");
        move=sc.next().toCharArray();
        Grid Head=new Grid(0,null);
        for (int i=0;i<gridSize;i++) { //객체 헤더찾기
            if(grid.get(i).sub.getName()== marine.getName() && grid.get(i).sub.getHeader()) {
                Head=grid.get(i);
                break;
            }
        }
        Grid temp=new Grid(0,null);
        int up,index;
        for(int i=0;i< move.length;i++) {
            temp=Head;
            while(true) {
                if(temp.getNext()!=null) //꼬리부분 찾기
                {
                    temp= temp.getNext();

                }

                else
                    break;
            }
            switch (move[i]) {
                case 'w': //상
                    if(Head.grid-gridLength <0 || grid.get(Head.grid-gridLength).isUse) {
                        System.out.println("상 이동 실패");
                    }
                    else {
                        up= Head.grid-gridLength; //헤더부분 좌표
                        index=temp.grid; //꼬리부분 좌표
                        Grid tSub= new Grid(grid.get(index).grid,grid.get(index).pos);
                        tSub.isUse=false;
                        grid.get(up).sub.setCell(grid.get(index).sub.getCell());
                        grid.set(index,tSub);
                        grid.get(index).setNext(null);
                        grid.get(index).sub.setName(null);
                        grid.get(up).Use(); //이동부분 상태 설정
                        grid.get(Head.grid).sub.reHeader(); //기존 헤드 상태 제거
                        grid.get(up).sub.setName(Head.sub.getName()); //이동부분 이름지정
                        grid.get(up).sub.setHeader(); // 이동부분 헤더로 지정
                        grid.get(up).setNext(Head); //이동된 헤더의 다음객체연결을 이전 헤더로지정
                        while(true) {
                            if(Head.getNext().getNext()==null) {
                                Head.setNext(null);
                                break;
                            }
                            else
                                Head=Head.getNext();
                        }
                        Head=grid.get(up); //헤더 변수 를 새로운헤더로 지정
                    }
                    break;
                case 's': //하
                    if(Head.grid+gridLength >gridSize || grid.get(Head.grid+gridLength).isUse) {
                        System.out.println("하 이동 실패");
                    }
                    else {


                        up= Head.grid+gridLength;
                        index=temp.grid; //꼬리부분 좌표
                        Grid tSub= new Grid(grid.get(index).grid,grid.get(index).pos);
                        tSub.isUse=false;
                        grid.get(up).sub.setCell(grid.get(index).sub.getCell());
                        grid.set(index,tSub);
                        grid.get(index).setNext(null);
                        grid.get(index).sub.setName(null);
                        grid.get(up).Use(); //이동부분 상태 설정
                        grid.get(Head.grid).sub.reHeader(); //기존 헤드 상태 제거
                        grid.get(up).sub.setName(Head.sub.getName()); //이동부분 이름지정
                        grid.get(up).sub.setHeader(); // 이동부분 헤더로 지정
                        grid.get(up).setNext(Head); //이동된 헤더의 다음객체연결을 이전 헤더로지정
                        while(true) {
                            if(Head.getNext().getNext()==null) {
                                Head.setNext(null);
                                break;
                            }
                            else
                                Head=Head.getNext();
                        }
                        Head=grid.get(up); //헤더 변수 를 새로운헤더로 지정
                    }
                    break;

                case 'd': //우
                    if((Head.grid+1)%(gridLength-1)==0|| grid.get(Head.grid+1).isUse) {
                        System.out.println("우 이동 실패");
                    }
                    else {

                        up= Head.grid+1;
                        index=temp.grid; //꼬리부분 좌표
                        Grid tSub= new Grid(grid.get(index).grid,grid.get(index).pos);
                        tSub.isUse=false;
                        grid.get(up).sub.setCell(grid.get(index).sub.getCell());
                        grid.set(index,tSub);
                        grid.get(index).setNext(null);
                        grid.get(index).sub.setName(null);
                        grid.get(up).Use(); //이동부분 상태 설정
                        grid.get(Head.grid).sub.reHeader(); //기존 헤드 상태 제거
                        grid.get(up).sub.setName(Head.sub.getName()); //이동부분 이름지정
                        grid.get(up).sub.setHeader(); // 이동부분 헤더로 지정
                        grid.get(up).setNext(Head); //이동된 헤더의 다음객체연결을 이전 헤더로지정
                        while(true) {
                            if(Head.getNext().getNext()==null) {
                                Head.setNext(null);
                                break;
                            }
                            else
                                Head=Head.getNext();
                        }
                        Head=grid.get(up); //헤더 변수 를 새로운헤더로 지정
                    }
                    break;
                case 'a': //좌
                    if(((Head.grid-1)%(gridLength))==0|| grid.get(Head.grid-1).isUse) {
                        System.out.println("좌 이동 실패");
                    }
                    else {

                        up= Head.grid-1;
                        index=temp.grid; //꼬리부분 좌표
                        Grid tSub= new Grid(grid.get(index).grid,grid.get(index).pos);
                        tSub.isUse=false;
                        grid.get(up).sub.setCell(grid.get(index).sub.getCell());
                        grid.set(index,tSub);
                        grid.get(index).setNext(null);
                        grid.get(index).sub.setName(null);
                        grid.get(up).Use(); //이동부분 상태 설정
                        grid.get(Head.grid).sub.reHeader(); //기존 헤드 상태 제거
                        grid.get(up).sub.setName(Head.sub.getName()); //이동부분 이름지정
                        grid.get(up).sub.setHeader(); // 이동부분 헤더로 지정
                        grid.get(up).setNext(Head); //이동된 헤더의 다음객체연결을 이전 헤더로지정
                        while(true) {
                            if(Head.getNext().getNext()==null) {
                                Head.setNext(null);
                                break;
                            }
                            else
                                Head=Head.getNext();
                        }
                        Head=grid.get(up); //헤더 변수 를 새로운헤더로 지정
                    }
                    break;
                default:
                    continue;

            }
        }
    }
    public void GridAttack(SubM marine) {//잠수함 공격
        String atkPos;
        Scanner sc= new Scanner(System.in);
        System.out.println("공격할 좌표를 입력하세요. ex) e7 ");
        int row=0;
        int col=0;
        atkPos=sc.next();
        String temp;
        while(alphabet.charAt(row)!=atkPos.charAt(0)) row++;
        col=(int)atkPos.charAt(1)-48;
        System.out.println(row +" "+ col);
        for(int i=0;i<gridLength;i++) { //행 공격
            temp=atkPos.charAt(0)+String.valueOf(i);
            if(grid.get((row*gridLength)+i).isUse) {
                grid.get((row*gridLength)+i).sub.setCell(grid.get(row*gridLength+i).sub.getCell()-1);
                System.out.printf("%s에 객체 발견 및 공격 수행  남은 체력: %d\n",temp,grid.get((row*gridLength)+i).sub.getCell());
            }
        }
        row=0;

        for(int i=0;i<gridLength;i++) { //열 공격

            if(grid.get((i*gridLength) + col).isUse) {
                grid.get((i*gridLength)+col).sub.setCell(grid.get((i*gridLength)+col).sub.getCell()-1);
                System.out.printf("%s에 객체 발견 및 공격 수행  남은 체력: %d\n",grid.get(i*gridLength +col).pos,grid.get((i*gridLength)+col).sub.getCell());
            }
        }
    }
    public void printGrid(){ //그리드 출력
        int cnt=0;
        for(Grid i : grid) {
            cnt++;
            if(i.isUse && !i.sub.getHeader()) {
                System.out.printf("%3c ", 'x');
            }
            else if(i.sub.getHeader()){
                System.out.printf("%3c ",'o');
            }
            else {
                System.out.printf("%3s ",i.pos);
            }
            if(cnt%gridLength==0)
                System.out.println();

        }
        System.out.printf("\n객체에 저장된값 [ ");
        for(Grid i : grid) {
            if(i.isUse)
                System.out.printf(" %s ",i.pos);
        }
        System.out.printf("]");

    }
}
