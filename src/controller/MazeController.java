package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MazeController implements Initializable {

    @FXML
    private CheckBox drawCheckbox;
    @FXML
    private Pane canvas;

    private int[][] board;
    private final int size = 20;
    private final int paneDimensionSize = 600;
    private final int tileSize = (int) (paneDimensionSize / size);
    private List<Integer> path;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        path = new ArrayList<>();
        board = new int[size][size];
        fillBoard();
        draw();
        drawCheckbox.setDisable(true);
        drawCheckbox.setSelected(true);
        System.out.println(Arrays.deepToString(board));
    }

    private void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 1;
            }
        }

        board[1][(int) (size / 2)] = 2;
        board[size - 2][(int) (size / 2)] = 9;
    }

    private void draw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle r = new Rectangle(600 / size * i, 600 / size * j, 600 / size, 600 / size);
                if (board[i][j] == 1) {
                    r.setFill(Color.BLACK);
                    r.setStroke(Color.WHITE);
                } else if (board[i][j] == 3) {
                    r.setFill(Color.LAWNGREEN);
                    r.setStroke(Color.GREEN);
                } else {
                    r.setFill(Color.WHITE);
                    r.setStroke(Color.BLACK);
                }
                canvas.getChildren().add(r);
            }
        }
    }

    @FXML
    private void cllearMaze(ActionEvent event) {
        fillBoard();
        path.removeAll(path);
        drawCheckbox.setSelected(true);
        draw();
    }

    @FXML
    private void clearPath(ActionEvent event) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 3 || board[i][j] == 4) {
                    board[i][j] = 0;
                }
            }
        }
        board[1][(int) (size / 2)] = 2;
        board[size - 2][(int) (size / 2)] = 9;
        drawCheckbox.setSelected(true);
        path.removeAll(path);
        draw();
    }

    @FXML
    private void findPath(ActionEvent event) {
        DepthFirst.searchPath(board, size / 2, 1, path);
        for (int i = 0; i < path.size(); i = i + 2) {
            board[path.get(i + 1)][path.get(i)] = 3;
        }
        if (path.isEmpty()) {
            clearPath(event);
        } else {
            path.removeAll(path);
            drawCheckbox.setSelected(false);
        }
        draw();

    }

    @FXML
    private void drawOnCanvas(MouseEvent event) {
        int x = (int) (event.getX() / tileSize);
        int y = (int) (event.getY() / tileSize);

        if (drawCheckbox.isSelected() && x > 0 && x < size - 1 && y > 0 && y < size - 1) {

            if (board[x][y] == 0) {
                board[x][y] = 1;
            } else if (board[x][y] == 1) {
                board[x][y] = 0;
            }
            Rectangle r = new Rectangle(600 / size * x, 600 / size * y, 600 / size, 600 / size);
            if (board[x][y] == 1) {
                r.setFill(Color.BLACK);
                r.setStroke(Color.WHITE);
            } else if (board[x][y] == 3) {
                r.setFill(Color.LAWNGREEN);
                r.setStroke(Color.GREEN);
            } else {
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
            }
            canvas.getChildren().add(r);
            path.removeAll(path);
        }
    }

}
