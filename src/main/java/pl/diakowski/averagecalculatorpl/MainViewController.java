package pl.diakowski.averagecalculatorpl;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class MainViewController {

    @FXML
    private Button addRowButton;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button calculateButton;

    @FXML
    private TableColumn<Grade, Double> gradeColumn;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuClose;

    @FXML
    private Menu menuEdit;

    @FXML
    private MenuItem menuEditAddRow;

    @FXML
    private MenuItem menuEditRedo;

    @FXML
    private MenuItem menuEditRemoveRow;

    @FXML
    private MenuItem menuEditRetry;

    @FXML
    private Menu menuFile;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem menuHelpHelp;

    @FXML
    private Button removeRowButton;

    @FXML
    private TableView<Grade> table;

    @FXML
    private VBox vBox;

    @FXML
    private TableColumn<Grade, Double> weightColumn;


    public void initialize() {
        ObservableList<Grade> items = table.getItems();
        table.setEditable(true);
        table.setPlaceholder(new Label("Nie dodano żadnego wiersza. Dodaj go, używając przycisku lub za pomocą menu"));
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gradeColumn.setEditable(true);
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        weightColumn.setEditable(true);
        removeRow(items, table);
        save();

        calculate(items);
        addRow(items);
        closeProgram();
        //removeRow();

    }

    private void save() {
        gradeColumn.setOnEditCommit(event -> {
            Grade grade = event.getRowValue();
            grade.setGrade(event.getNewValue());

        });
        weightColumn.setOnEditCommit(event -> {
            Grade grade = event.getRowValue();
            grade.setWeight(event.getNewValue());
        });
    }

    private void addRow(ObservableList<Grade> items) {
        addRowButton.setOnMouseClicked(event -> {
            Grade grade = new Grade();
            items.add(grade);
            table.scrollTo(grade);
            table.getSelectionModel().select(grade);
            table.edit(table.getItems().size() - 1, table.getColumns().get(0));
        });
    }

    private void removeRow(ObservableList<Grade> items, TableView<Grade> grades) {
        removeRowButton.setOnMouseClicked(event -> {
            Grade selectedItem = grades.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                    grades.getItems().remove(selectedItem);
                }
        });
    }

    private void calculate(ObservableList<Grade> grades) {

        calculateButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wynik");
            if (Double.isNaN(weightedMean(grades, grades.size())))
                alert.setContentText("Nie wypełniono wszystkich kolumn.");
            else
                alert.setContentText(String.format("Wynik obliczeń to: %.2f", weightedMean(grades, grades.size())));
            alert.showAndWait();
        });
    }

    private void closeProgram() {
        menuClose.setOnAction(event -> {
            Stage ownerWindow = (Stage) menuClose.getParentPopup().getOwnerWindow();
            ownerWindow.close();
        });
    }

    private double weightedMean(ObservableList<Grade> grades, int n) {
        double sum = 0;
        double numWeight = 0;


        for (int i = 0; i < n; i++) {
            if (grades.get(i).getGrade() == null || grades.get(i).getWeight() == null) {
                continue;
            }
            numWeight = numWeight + grades.get(i).getGrade()*grades.get(i).getWeight();
            sum = sum + grades.get(i).getWeight();
        }

        return (numWeight) / sum;
    }
}
