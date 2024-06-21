/* Text And Button Panel
 * Panel consisting of JTextField and JButton
 */

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextAndButtonPanel extends JPanel {
	private JTextField field;
	private JButton button;
	
	TextAndButtonPanel(String id) {
		super();
		field = new JTextField();
		this.field.setColumns(20);
		button = new JButton(id);
		add(field);
		add(button);
	}

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
}
