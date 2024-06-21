/* Administrator Control Panel
 * GUI
 * Singleton Pattern
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminControlPanel extends JFrame {
	private static AdminControlPanel appFrame = null;
	
	public static AdminControlPanel getInstance() {
		if (appFrame == null) {
			appFrame = new AdminControlPanel();
		}
		return appFrame;
	}
	
	//Private Fields
    private JPanel main, right;
    private JButton userView;

	private TreePanel tree;
    private TextAndButtonPanel user, group;
    private ButtonPanel data;
	
	//Private Constructor
	private AdminControlPanel() {
		JFrame frame = new JFrame("Mini Twitter");
       
        //GridLayout(int rows, int columns)
        main = new JPanel(new GridLayout(1, 2));
        tree = new TreePanel();
        main.add(tree);
        right = new JPanel(new GridLayout(3, 1));
        JPanel rightTextAndButton = new JPanel(new GridLayout(2, 1));
        
        //Add Text and Buttons on the top Right Panel
        user = new TextAndButtonPanel("Add User");
        user.getButton().addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = user.getField().getText();
				if (!id.isEmpty()) {
					tree.add(new User(id));
				}
			}
        });
        rightTextAndButton.add(user);
        
        group = new TextAndButtonPanel("Add Group");
        group.getButton().addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = group.getField().getText();
				if (!id.isEmpty()) {
					tree.add(new Group(id));
				}
			}
        });
        rightTextAndButton.add(group);
        right.add(rightTextAndButton);
        
        
        //Add Open User View Option
        JPanel rightUserViewButton = new JPanel(new GridLayout(2, 1));
        userView = new JButton("Open User View");
        userView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompositeNode cn = tree.getNodeObject();
				if (cn != null && cn instanceof User) {
					User temp = (User) cn;
					UserView newFrame = new UserView(temp);
					temp.attach(newFrame);
					temp.setUserView(newFrame);
				}
			}
        });
        rightUserViewButton.add(userView);
        right.add(rightUserViewButton);

        //Add Operation Buttons
        data = new ButtonPanel(new GridLayout(2, 2), tree.getRoot());
        right.add(data);
        main.add(right);
        frame.getContentPane().add(main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
	}
}
