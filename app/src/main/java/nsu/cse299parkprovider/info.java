package nsu.cse299parkprovider;

/**
 * Created by jh_anik on 3/30/2018.
 */

public class info {


    String latno;
    String longno;
    boolean available;

public info(){

}

public info(String latno, String longno, boolean available){


    this.latno = latno;
    this.longno = longno;
    this.available= available;



}

    public String getLatno() {
        return latno;
    }

    public void setLatno(String latno) {
        this.latno = latno;
    }

    public void setLongno(String longno) {
        this.longno = longno;
    }
    public String getLongno() {
        return longno;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


}
