/* Total Users Visitor class
 * Visitor Pattern
 */

public class TotalUsersVisitor implements Visitor {
	private int count = 0;
	
	@Override
	public void visitUser(User user) {
		count++;
	}

	@Override
	public void visitGroup(Group group) {
		for (CompositeNode cn : group.getGroupUsers()) {
			if (cn instanceof User) {
				visitUser((User) cn);
			}
			else if (cn instanceof Group) {
				visitGroup((Group) cn);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Total Users: " + this.getTotalUsers();
	}
	
	@Override
	public void clear() {
		count = 0;
	}
	
	public int getTotalUsers() {
		return count;
	}
}
