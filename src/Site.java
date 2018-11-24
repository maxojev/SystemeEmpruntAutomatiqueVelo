class Site {

/* Constantes associ�es au site */

static final int stockInit = 6;
static final int stockMax = 10;
static final int borneSup = 8;
static final int borneInf = 2;
private int stockactuel;
private int numeroSite;


    public Site(int numeroSite) {
        this.numeroSite = numeroSite;
        this.stockactuel = stockInit;
    }

    public synchronized boolean empruntClient() throws InterruptedException {

        while(true) {
            if (stockactuel > 0) {
                stockactuel--;
                System.out.println("Emprunt r�ussi");
                System.out.println("Nouveau StockActuel Site: " + stockactuel);

                return true;
            }
            else if(stockactuel <= 0) {
                System.out.println("Client en attente d'un v�lo");
                wait();
            }
        }
    }

    public synchronized boolean depotClient() throws InterruptedException {

        while (true) {
            if (stockactuel < stockMax) {
                stockactuel++;
                System.out.println("D�p�t r�ussi");
                System.out.println("Nouveau StockActuel Site: " + stockactuel);

                return true;
            } else if(stockactuel >= stockMax) {
                System.out.println("Client en attente d'une place pour d�poser");
                wait();
            }
        }
    }

    public int getNumeroSite() {
        return numeroSite;
    }

    public int getStockactuel() {
        return stockactuel;
    }

    public int getBorneInf() {
        return borneInf;
    }

    public int getBorneSup() {
        return borneSup;
    }

    public int getStockInit() {
        return stockInit;
    }

    public void setStockactuel(int besoin) {
        this.stockactuel = besoin;
    }
}

