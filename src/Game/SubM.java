package Game;
//각 잠수함의 부모객체

public class SubM {
    private String name=null; //이름
    private int cell,size,move;
    private static final String body = "■";
    private boolean Header=false;
    private SubM next;
    SubM() {
        size=3;
        move=4;
        cell=3;
    }





    public int getCell() {return cell;}
    public void setCell(int cell) {this.cell=cell;}
    public int getMove(){return move;}
    public void setNext(SubM next){this.next=next; }
    public SubM getNext(){return next;}
    public boolean getHeader(){return Header;}
    public void setHeader(){Header=true;}
    public void reHeader(){Header=false;}
    public int getSize(){return size;}
    public void setName (String n) { name = n; }
    public String getName() { return name; }

}
