/* Last Updated User Visitor class
 * Visitor Pattern
 * Assignment 3
 */

import java.util.Date;

public class LastUpdatedUserVisitor implements Visitor {
	private User lastUpdatedUser;

	@Override
	public void visitUser(User user) {
		if (lastUpdatedUser == null) {
			lastUpdatedUser = user;
		}
		else {
			if (user.getLastUpdateTime() > lastUpdatedUser.getLastUpdateTime()) {
				lastUpdatedUser = user;
			}
		}
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
		return lastUpdatedUser.getID() + "\nLast Update: " 
				+ new Date(lastUpdatedUser.getLastUpdateTime());
	}
	
	@Override
	public void clear() {
		lastUpdatedUser = null;
	}
}
