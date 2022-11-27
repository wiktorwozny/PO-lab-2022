package agh.ics.oop.gui;
import javafx.application.Application;
import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

public class App extends Application implements IMapRefreshObserver {

    private AbstractWorldMap map;
    private ThreadedSimulationEngine engine;
    private Thread engineThread;
    private GridPane mapGrid;

    @Override
    public void init() throws Exception {

        super.init();

        try {
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,1), new Vector2d(3, 1) };

            this.engine = new ThreadedSimulationEngine(map, positions, 300);
            this.engine.addObserver(this);
        }
        catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        this.mapGrid = new GridPane();
        mapGrid.setGridLinesVisible(true);
        mapGrid.setHgap(0);
        mapGrid.setVgap(0);

        VBox root = new VBox();
        TextField textField = new TextField();
        Button button = new Button("START");
        button.setOnAction(event -> {

            String[] moves = textField.getCharacters().toString().split(" ");
            MoveDirection[] directions = new OptionsParser().parse(moves);

            this.engine.setMoves(directions);
            this.engineThread = new Thread(this.engine);
            this.engineThread.start();
        });

        root.getChildren().addAll(textField, button);
        root.getChildren().add(mapGrid);
        renderGrid(mapGrid);

        Scene scene = new Scene(root, 700, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void renderGrid(GridPane gridPane) throws FileNotFoundException {

        int minY = this.map.lowerLeftDraw().y;
        int minX = this.map.lowerLeftDraw().x;
        int maxY = this.map.upperRightDraw().y;
        int maxX = this.map.upperRightDraw().x;

        int cellWidth = 50;
        int cellHeight = 50;

        Label xyLabel = new Label("y\\x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
        gridPane.add(xyLabel, 0, 0, 1, 1);
        gridPane.setGridLinesVisible(true);

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

                IMapElement worldMapElement = (IMapElement) this.map.objectAt(position);
                GuiElementBox element = new GuiElementBox(worldMapElement);
                VBox graphicalElement = element.getGraphicalElement();
                GridPane.setHalignment(graphicalElement, HPos.CENTER);
                gridPane.add(graphicalElement, position.x - minX + 1, maxY - position.y + 1, 1, 1);
            }
        }
    }

    @Override
    public void refresh() {
        Platform.runLater(() -> {
            this.mapGrid.getChildren().clear();
            try {
                this.renderGrid(this.mapGrid);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
