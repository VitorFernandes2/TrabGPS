package gui.components.imageviews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewImage extends ImageView {

    public ViewImage(String url) {
        super();
        this.setImage(new Image(getClass().getResourceAsStream(url)));
    }
}
