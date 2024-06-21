/* Total Users Visitor class
 * Visitor Pattern
 */

public class TotalGroupsVisitor implements Visitor {
	private int count = 0;
	//Does not include Root Group
	
	@Override
	public void visitUser(User user) {
		// do nothing		
	}

	@Override
	public void visitGroup(Group group) {
		for (CompositeNode cn : group.getGroupUsers()) {
            if (cn instanceof Group) {
                count++;
                visitGroup((Group) cn);
            }
        }
	}
	
	@Override
	public String toString() {
		return "Total Groups: " + this.getTotalGroups();
	}
	
	@Override
	public void clear() {
		count = 0;
	}
	
	public int getTotalGroups() {
		return count;
	}

}
