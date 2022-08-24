package APILoad;


import Service.HistoryService;
import Service.WifiService;

public class CRUDTest {
    public static void main(String[] args) throws Exception{
        HistoryService hs = new HistoryService();
        hs.register(1.0001,1.0001);
        //hs.withdraw("1");
    }
}
