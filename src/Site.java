class Site {

/* Constantes associ�es au site */

    static final int stockInit = 6;
    static final int stockMax = 10;
    static final int borneSup = 8;
    static final int borneInf = 2;
    private int stockactuel = stockInit;
    private int numeroSite;
    private int stockCamion = 30;
    private boolean isOnsite;


    public Site(int numeroSite) {
        this.numeroSite = numeroSite;
        this.stockactuel = stockInit;
    }

    public synchronized boolean empruntClient() throws InterruptedException {

        while(stockactuel == 0 || isOnsite) {
            System.out.println("Client en attente d'un v�lo");
            wait();
        }

        stockactuel--;
        System.out.println("Emprunt r�ussi");
        System.out.println("Nouveau StockActuel Site: " + stockactuel);

        return true;

    }

    public synchronized boolean depotClient() throws InterruptedException {


        while(stockactuel >= stockMax  || isOnsite) {
            System.out.println("Client en attente d'une place pour d�poser");
            wait();
        }


        stockactuel++;
        System.out.println("D�p�t r�ussi");
        System.out.println("Nouveau StockActuel Site: " + stockactuel);

        return true;
        }


    public synchronized void equilibrage(){

        isOnsite = true;

        if (stockactuel > borneSup) {
            stockCamion += (stockactuel - stockInit);
            stockactuel = stockInit;
            System.out.println("exc�dent enlev� par le camion");
        }


        if (stockactuel < borneInf) {

            if (stockCamion < (borneInf - stockactuel) && stockCamion > 0) {

                stockactuel += stockCamion;
                stockCamion = 0;

                System.out.println("d�p�t v�lo par camion r�ussi (StockCamion insuffisant pour avoir un stockInit)");
            } else if (stockCamion > (borneInf - stockactuel)) {

                stockCamion -= (stockInit - stockactuel);
                stockactuel = stockInit;

                System.out.println("d�p�t v�lo par camion r�ussi (StockCamion suffisant)");
            }
        }

        System.out.println("Nouveau stockcamion: " + stockCamion);
        System.out.println("stockActuel: " + stockactuel);

        isOnsite = false;
    }

    public int getNumeroSite() {
        return numeroSite;
    }

}

