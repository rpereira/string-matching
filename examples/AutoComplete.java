import java.awt.*;
import java.awt.event.*;

public class AutoComplete {

  private Frame mainFrame;
  private Label headerLabel;
  private TextField input;

  public AutoComplete() {
    initGUI();
  }

  private void initGUI() {

    mainFrame = new Frame("AutoComplete example using TrieST class");
    mainFrame.setSize(600, 400);
    mainFrame.setLayout(new GridLayout(3, 1));
    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent){
        System.exit(0);
      }
    });

    headerLabel = new Label("Type some word that is present in data/words.txt");
    headerLabel.setAlignment(Label.LEFT);

    input = new TextField();

    mainFrame.add(headerLabel);
    mainFrame.add(input);

    mainFrame.setVisible(true);
  }

  public static void main(String[] args) {
    AutoComplete app = new AutoComplete();
  }
}
