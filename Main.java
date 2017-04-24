import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
	public static final Dimension SIZE = new Dimension(640, 480);
	public JaxEditor editor;
	public Main(){
		editor = new JaxEditor(LanguageRules.JAVA);
		this.add(new JScrollPane(editor));
	}
  
	public static void main(String[] args){
		Main m = new Main();
		m.setMinimumSize(SIZE);
		m.setSize(SIZE);
    
		m.pack();
		m.setLocationRelativeTo(null);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		m.setVisible(true);
	}
}
