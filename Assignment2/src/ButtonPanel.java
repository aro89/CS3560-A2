/* Button Panel
 * Panel of JButtons
 */

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	private List<JButton> buttons;
	private CompositeNode root;
	
	public ButtonPanel(LayoutManager layout, CompositeNode root) {
		super(layout);
		buttons = new ArrayList<>();
		this.root = root;
		
		addButton(new JButton("Show User Total"), new TotalUsersVisitor());
		addButton(new JButton("Show Group Total"), new TotalGroupsVisitor());
		addButton(new JButton("Show Messages Total"), new TotalMessagesVisitor());
		addButton(new JButton("Show Positive Percentage"), new PositivePercentageVisitor());
		addButton(new JButton("Show ID Verification"), new IDVerificationVisitor());
		addButton(new JButton("Show User With Last Update"), new LastUpdatedUserVisitor());
	}
	
	public void addButton(JButton button, Visitor visitor) {
		add(button);
		buttons.add(button);
		button.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				root.accept(visitor);
				JOptionPane.showMessageDialog(null, visitor);
			}
		});
	}
}


