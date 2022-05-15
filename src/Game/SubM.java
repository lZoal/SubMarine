package Game;
//각 잠수함의 부모객체

public class SubM {
    private String name; //이름
    private int cell,size,move;
    private static final String body = "■";
    SubM() {
        size=3;
        move=4;
        cell=(size*2)+4;
    }










    public void setName (String n) { name = n; }
    public String getName() { return name; }

}
