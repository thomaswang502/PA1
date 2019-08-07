
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
A class for displaying the model as a column of textfields in a frame.
*/
public class TextFrame extends JFrame implements ChangeListener {
	private DataModel datamodel;
	private JTextField[] fieldList;
	
	public TextFrame(DataModel d) {
		datamodel = d;
		
		final Container contentPane = this.getContentPane();
		setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); 
		
		ArrayList<Integer> a = datamodel.getData();
		fieldList = new JTextField[a.size()]; 
		
		// A listener for action events in the text fields
		ActionListener l = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JTextField c = (JTextField)e.getSource(); 
				
				int i = 0;
	            int count = fieldList.length;
	            while (i < count && fieldList[i] != c)
	               i++;
				try
				{
				int number = Integer.parseInt(c.getText()); 
				datamodel.update(i, number); //Update number in model
				}
				catch (Exception exc)
	            {
	               c.setText("Error.  No update");
	            }
			}
		};
		
		for (int i = 0; i < a.size(); i++) {
			JTextField textField = new JTextField(a.get(i).toString(), 10); 
			textField.addActionListener(l);
			add(textField); //Add to JFrame
			fieldList[i] = textField; //Update textfield
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void stateChanged(ChangeEvent e) {
		ArrayList<Integer> updated = datamodel.getData();
		int index = 0;
		for (JTextField j : fieldList) {
			j.setText(updated.get(index).toString());
			index++;
		}
	}
}