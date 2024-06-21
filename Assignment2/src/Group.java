/* Group Class
 * Composite Pattern
 * 	Components: Users and Groups
 * Visitor Pattern
 */

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class Group implements CompositeNode {
	private DefaultMutableTreeNode groupNode;
	private String groupID;
	private List<CompositeNode> nodes;
	
	public Group(String groupID) {
		groupNode = new DefaultMutableTreeNode(this);
		this.groupID = groupID;
		nodes = new ArrayList<>();
	}
	
	@Override
	public String getID() {
		return this.groupID;
	}

	@Override
	public CompositeNode getRoot() {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.groupNode.getRoot();
		return (CompositeNode) root.getUserObject();
	}

	@Override
	public DefaultMutableTreeNode getCompositeNode() {
		return this.groupNode;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitGroup(this);	
	}
	
	@Override
	public boolean addCompositeNode(CompositeNode cn) {
		if (!hasObject(cn)) {
			nodes.add(cn);
			groupNode.add(cn.getCompositeNode());
			System.out.println(CompositeNode.IDs);
			CompositeNode.IDs.add(cn.getID());
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Group: " + groupID;
	}
	
	private boolean hasObject(CompositeNode cn) {
		for (String id : CompositeNode.IDs) {
			if (id.equals(cn.getID())) {
				return true;
			}
		}
		return false;
	}
	    	
	public List<CompositeNode> getGroupUsers() {
		return this.nodes;
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		for (CompositeNode cn : nodes) {
			if (cn instanceof User) {
				users.add((User) cn);
			}
			else {
				users.addAll(((Group) cn).getAllUsers());
			}
		}
		return users;
	}
}
