/* Tree Panel
 * User and Group Tree
 */

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class TreePanel extends JPanel {
	private CompositeNode root;
	private JTree tree;
	private DefaultMutableTreeNode node;
	private DefaultMutableTreeNode groupNode;
    
	public TreePanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		root = new Group("Root");
		groupNode = root.getCompositeNode();
		tree = new JTree(root.getCompositeNode());
    	
    		//Sample Data
		add(new User("John"));
		add(new User("Bob"));
		add(new User("Steve"));
		add(new Group("CS356"));

		setTreeListener();
		JScrollPane view = new JScrollPane(tree);
		JLabel label = new JLabel("Tree View: ");
		add(label);
		add(view);
    	}
    
    	public void setTreeListener()  {
    		tree.addTreeSelectionListener((TreeSelectionListener) new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode temp = 
					(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (temp == null) {
					return;
				}
				
				node = temp;
				if (temp.getUserObject() instanceof User) {
					groupNode = (DefaultMutableTreeNode) temp.getParent();
				}
				else {
					groupNode = temp;
				}
			}
    		});
    	}
    
    	public void add(CompositeNode cn) {
  		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
    		if (((CompositeNode) groupNode.getUserObject()).addCompositeNode(cn)) {
    			model.nodesWereInserted(groupNode, new int[] {groupNode.getChildCount() - 1});
    			tree.scrollPathToVisible(new TreePath(cn.getCompositeNode().getPath()));
    		}
    	}
    
    	public CompositeNode getNodeObject() {
    		if (node == null) {
    			return null;
    		}
    		return (CompositeNode) node.getUserObject();
    	}
    
    	public CompositeNode getRoot() {
    		return this.root;
    	}
}
