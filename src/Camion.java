public class Camion extends Thread {

    private int stockCamion;
    private Site[] sites;

    public Camion(Site[] sites) {
        stockCamion = 0;
        this.sites = sites;
    }

    public void run(){
        int i;

        while(true){
            for(i = 0; i<sites.length; i++){

                try{ Thread.sleep(100);} catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
