package ir.ac.kntu.scenes;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RegionStyleManager {
    public static void setupFocusAndMouseEvents(Region region) {
        region.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setFocusedStyle(region);
            } else {
                setNormalStyle(region);
            }
        });

        region.setOnMouseEntered(e -> setOnMouseStyle(region));
        region.setOnMouseExited(e -> {
            if (region.isFocused()) {
                setFocusedStyle(region);
            } else {
                setNormalStyle(region);
            }
        });
    }

    public static void setFocusedStyle(Region region) {
        region.setBackground(new Background(new BackgroundFill(Color.DARKRED, new CornerRadii(50), Insets.EMPTY)));
        region.setBorder(new Border(
                new BorderStroke(Color.DARKRED, BorderStrokeStyle.SOLID, new CornerRadii(50), BorderWidths.DEFAULT)));
    }

    public static void setOnMouseStyle(Region region) {
        region.setBackground(new Background(
                new BackgroundFill(Color.DARKRED.darker().darker(), new CornerRadii(50), new Insets(0))));
        region.setBorder(new Border(
                new BorderStroke(Color.DARKRED.darker().darker(), BorderStrokeStyle.SOLID, new CornerRadii(50),
                        BorderWidths.DEFAULT)));
    }

    public static void setNormalStyle(Region region) {
        region.setBackground(Background.EMPTY);
        region.setBorder(new Border(
                new BorderStroke(Color.WHITE, BorderStrokeStyle.DASHED, new CornerRadii(50), BorderWidths.DEFAULT)));
    }
}
