package app.ring04w.com.asynctask;

/**
 * Created by ring04w on 15/7/24.
 */
public class NetOperator {
    public void operate(){
        try {
            Thread.sleep(8*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
