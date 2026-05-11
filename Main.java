package TemplateMathode;

public class Main {
    public static void main(String[] args) {

        PreparoLanche classico     = new LancheClassico();
        PreparoLanche vegetariano  = new LancheVegetariano();
        PreparoLanche gourmet      = new LancheGourmet();

        classico.prepararLanche();
        vegetariano.prepararLanche();
        gourmet.prepararLanche();
    }
}