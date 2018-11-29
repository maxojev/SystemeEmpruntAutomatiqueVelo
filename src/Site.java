class Site {

/* Constantes associées au site */

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
            System.out.println("Client en attente d'un vélo");
            wait();
        }

        stockactuel--;
        System.out.println("Emprunt réussi");
        System.out.println("Nouveau StockActuel Site: " + stockactuel);

        return true;

    }

    public synchronized boolean depotClient() throws InterruptedException {


        while(stockactuel >= stockMax  || isOnsite) {
            System.out.println("Client en attente d'une place pour déposer");
            wait();
        }


        stockactuel++;
        System.out.println("Dépôt réussi");
        System.out.println("Nouveau StockActuel Site: " + stockactuel);

        return true;
        }


    public synchronized void equilibrage(){

        isOnsite = true;

        if (stockactuel > borneSup) {
            stockCamion += (stockactuel - stockInit);
            stockactuel = stockInit;
            System.out.println("excédent enlevé par le camion");
        }


        if (stockactuel < borneInf) {

            if (stockCamion < (borneInf - stockactuel) && stockCamion > 0) {

                stockactuel += stockCamion;
                stockCamion = 0;

                System.out.println("dépôt vélo par camion réussi (StockCamion insuffisant pour avoir un stockInit)");
            } else if (stockCamion > (borneInf - stockactuel)) {

                stockCamion -= (stockInit - stockactuel);
                stockactuel = stockInit;

                System.out.println("dépôt vélo par camion réussi (StockCamion suffisant)");
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

