class Site {

/* Constantes associées au site */

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

    public synchronized boolean empruntClient(){

        if (stockactuel > 0){
            stockactuel--;
            return true;
        }

        return false;
    }

    public synchronized boolean depotClient(){
        if (stockactuel < stockMax){
            stockactuel++;
            return true;
        }
        return false;
    }

    public int getNumeroSite() {
        return numeroSite;
    }
}

