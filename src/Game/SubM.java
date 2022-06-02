package Game;
//각 잠수함의 부모객체

public class SubM {
    private String name; //이름
    private int cell,size,move,pos;
    private static final String body = "■";
    private boolean Header=false;
    SubM() {
        size=3;
        move=4;
        cell=(size*2)+4;
    }







    public void setPos(int pos){this.pos=pos; }
    public int getPos(){return pos;}
    public boolean getHeader(){return Header;}
    public void setHeader(){Header=true;}
    public int getSize(){return size;}
    public void setName (String n) { name = n; }
    public String getName() { return name; }

}
