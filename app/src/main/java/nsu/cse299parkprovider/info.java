package nsu.cse299parkprovider;

/**
 * Created by jh_anik on 3/30/2018.
 */

public class info {


    String latno;
    String longno;



public  info(String latno, String longno){


    this.latno = latno;
    this.longno = longno;



}

    public String getLatno() {
        return latno;
    }

    public void setLatno(String latno) {
        this.latno = latno;
    }

    public String getLongno() {
        return longno;
    }

    public void setLongno(String longno) {
        this.longno = longno;
    }
}
