import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main extends JFrame{
	public static final Dimension SIZE = new Dimension(640, 480);
	private JTabbedPane root = new JTabbedPane();
	public ArrayList<JaxEditor> editors;
	
	public Main(){
		JMenuBar bar = new JMenuBar();
		JMenu m_file = new JMenu("File");
		JMenu m_edit = new JMenu("Edit");
		JMenu m_run = new JMenu("Run");
		JMenu m_about = new JMenu("About");
		
		//File Tab
		JMenuItem m_file_new = new JMenuItem("New");
		JMenuItem m_file_open = new JMenuItem("Open");
		JMenuItem m_file_save = new JMenuItem("Save");
		JMenuItem m_file_saveAs = new JMenuItem("Save As");
		JMenuItem m_file_exit = new JMenuItem("Exit");
		
		m_file.add(m_file_new);
		m_file.add(m_file_open);
		m_file.add(m_file_save);
		m_file.add(m_file_saveAs);
		m_file.add(m_file_exit);
		
		//Run Tab
		JMenuItem m_run_new = new JMenuItem("New");
		
		//ToolBar
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_run);
		bar.add(m_about);
		
		editors = new ArrayList<JaxEditor>();
		this.add(root);
		this.setJMenuBar(bar);
		
		addEditor();
	}
    
	public void addEditor(){
		editors.add(new JaxEditor(LanguageRules.JAVA));
		root.addTab("new", new JScrollPane(editors.get(editors.size() -1)));
	}
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		Main m = new Main();
		m.setMinimumSize(SIZE);
		m.setSize(SIZE);
    
		m.pack();
		m.setLocationRelativeTo(null);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		m.setVisible(true);
	}
}
