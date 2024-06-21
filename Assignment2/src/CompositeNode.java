/* CompositeNode Interface
 * Composite Pattern
 * 	Unify User and Group
 * Visitor Pattern
 * 	Accept Visitor
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public interface CompositeNode {
	public static List<String> IDs = new ArrayList<>(Arrays.asList("Root"));
	public String getID();
	public DefaultMutableTreeNode getCompositeNode();
	public CompositeNode getRoot();
	public void accept(Visitor visitor);
	public boolean addCompositeNode(CompositeNode cn);
	public String toString();
}
