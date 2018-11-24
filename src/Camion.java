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
            for(i = 0; i<sites.length; i++) {

                System.out.println("============================= SITE" + i + "===============================");

                //Si je suis sur un site et que le stock actuel est supérieur à BorneSup (ou inférieur à BorneInf), je le ramène au stock initial
                if (sites[i].getStockactuel() > sites[i].getBorneSup()) {

                    stockCamion += (sites[i].getStockactuel() - sites[i].getBorneSup());
                    sites[i].setStockactuel(sites[i].getStockInit());
                    System.out.println("excédent enlevé par le camion");

                }

                if (sites[i].getStockactuel() < sites[i].getBorneInf()) {

                    if (stockCamion < (sites[i].getBorneInf() - sites[i].getStockactuel()) && stockCamion > 0) {

                        sites[i].setStockactuel(stockCamion + (sites[i].getBorneInf() - sites[i].getStockactuel()));
                        stockCamion = 0;

                        System.out.println("dépôt vélo par camion réussi (StockCamion insuffisant pour avoir un stockInit)");
                    } else if (stockCamion > (sites[i].getBorneInf() - sites[i].getStockactuel())) {

                        stockCamion -= (sites[i].getBorneInf() - sites[i].getStockactuel());
                        sites[i].setStockactuel(sites[i].getStockInit());

                        System.out.println("dépôt vélo par camion réussi (StockCamion suffisant)");
                    }
                }

                System.out.println("Nouveau stockcamion: " + stockCamion);
                System.out.println("stockActuel: " + sites[i].getStockactuel());

                //Déplacement du camion
                System.out.println("Déplacement du camion du site" + i + "au site" + i + 1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
