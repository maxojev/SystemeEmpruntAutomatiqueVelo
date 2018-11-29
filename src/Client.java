public class Client extends Thread {
    private Site siteDepart;
    private Site siteArrive;
    private int rangClient;


    public Client(int rangClient, Site siteDepart, Site siteArrive) {
        this.rangClient = rangClient;
        this.siteDepart = siteDepart;
        this.siteArrive = siteArrive;
    }

    public void run(){

            try {
                siteDepart.empruntClient();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int temps = (siteArrive.getNumeroSite()-siteDepart.getNumeroSite())*100;
            try{ Thread.sleep(temps<0? temps*(-1):temps);} catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                siteArrive.depotClient();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
