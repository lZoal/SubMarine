package Observer;
import java.util.ArrayList;
abstract public class PrintGame {
    private ArrayList<Observation> observers = new ArrayList<Observation>();

    public void attach(Observation o) {
        observers.add(o);
    }

    public void dettach(Observation o){
        observers.remove(o);
    }

    public void notifyObservers(){
        for (Observation o: observers)
            o.update();
    }
}
