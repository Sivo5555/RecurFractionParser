package sivo5555;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller
{
    @FXML
    TextField inputField;
    @FXML
    TextArea resultArea;

    FractionBuilder fractionBuilder = new FractionBuilder();

    public void onEnterBut()
    {
        try
        {
            String input = inputField.getText().trim();
            Fraction fraction = fractionBuilder.buildFraction(input);
            resultArea.clear();
            resultArea.appendText("Цепочка соответствует грамматике.\n");
            resultArea.appendText("Считанная цепная дробь:\n");
            resultArea.appendText(fraction.toString());
        }
        catch (BadCharException e)
        {
            resultArea.setText("Цепочка не соответствует грамматике.");
        }
        catch (StringIndexOutOfBoundsException e)
        {
            resultArea.setText("Пустая строка вместо цепочки.");
        }
    }
}
