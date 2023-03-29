package com.example.bataillefx;

import javafx.scene.image.ImageView;

public class PlacerBateauFx {
    HelloController hc = new HelloController();

    public ImageView[][] initTableauBateau(){
        ImageView[][] bateaux = {{hc.porteAvions1, hc.porteAvions2}, {hc.croiseur1, hc.croiseur2},
                {hc.contreTorpilleur1, hc.contreTorpilleur2}, {hc.sousMarin1, hc.sousMarin2}, {hc.torpilleur1, hc.torpilleur2}};
        return bateaux;
    }
}
