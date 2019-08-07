import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ApplicationView extends JFrame implements ChangeListener {
	private TextModel text;
	private ArrayList<JLabel> textFields;
	private Container textContainer;
	
	/**
	 * Creates a new Application View
	 * @param tm The Text Model
	 */
	public ApplicationView(TextModel tm) {
		text = tm;
		textFields = new ArrayList<>();
		setLayout(new BorderLayout()); //Single Vertical Column
		
		JTextField inputField = new JTextField(); //User Input Field
		
		textContainer = new Container(); //Text Display Field
		textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
		
		JButton addButton = new JButton("add"); //Add Button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checkInput = inputField.getText();
				if (checkInput.equals("")) return; //Disable empty input
				text.update(checkInput);
				inputField.setText("");
			}
		});
		
		add(addButton, BorderLayout.NORTH);
		add(textContainer, BorderLayout.CENTER);
		add(inputField, BorderLayout.SOUTH);
		
		setMinimumSize(new Dimension(200, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * Get new model data and update fields
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		ArrayList<String> newData = text.getData(); //Get new model data
		for (int i = textFields.size(); i < newData.size(); i++) { //Add text fields to visible area if there are new ones
			JLabel temp = new JLabel(newData.get(i));
			textFields.add(temp);
		}
		for (JLabel jl : textFields) textContainer.add(jl); //Add 
		pack(); //pack again to fit updated container size
	}
}