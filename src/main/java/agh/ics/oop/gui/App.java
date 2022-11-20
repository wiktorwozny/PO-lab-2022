package agh.ics.oop.gui;
import javafx.application.Application;
import agh.ics.oop.*;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private AbstractWorldMap map;

    @Override
    public void init() throws Exception {
        super.init();

        Parameters parameters = getParameters();

        List<String> argsList = parameters.getRaw();
        String[] args = new String[argsList.size()];
        args = argsList.toArray(args);

        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,1), new Vector2d(3, 1) };
            IEngine engine = new SimulationEngine(directions, this.map, positions);
            engine.run();

            System.out.println(this.map);

        }
        catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        this.map = new GrassField(10);

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        int cellWidth = 30;
        int cellHeight = 30;

        int minY = this.map.lowerLeftDraw().y;
        int minX = this.map.lowerLeftDraw().x;
        int maxY = this.map.upperRightDraw().y;
        int maxX = this.map.upperRightDraw().x;

        System.out.println("minX: " + minX + " minY: " + minY + " maxX: " + maxX + " maxY: " +maxY);

        Label xyLabel = new Label("y\\x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
        gridPane.add(xyLabel, 0, 0, 1, 1);

        for (int i = minY; i <= maxY; i++) {
            Label label = new Label(Integer.toString(i));
            gridPane.add(label, 0, maxY - i + 1, 1, 1);
            gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = minX; i <= maxX; i++) {
            Label label = new Label(Integer.toString(i));
            gridPane.add(label, i - minX + 1, 0, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Vector2d position = new Vector2d(x, y);
                if (!this.map.isOccupied(position)) {
                    continue;
                }

                Object worldMapElement = this.map.objectAt(position);
                Label label = new Label(worldMapElement.toString());
                GridPane.setHalignment(label, HPos.CENTER);
                gridPane.add(label, position.x - minX + 1, maxY - (position.y - minY) + 1, 1, 1);
            }
        }

        Scene scene = new Scene(gridPane, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
