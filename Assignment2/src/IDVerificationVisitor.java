/* ID Verification Visitor class
 * Visitor Pattern
 * Assignment 3
 */

import java.util.ArrayList;
import java.util.List;

public class IDVerificationVisitor implements Visitor {
	private List<String> invalidIDs;
	
	public IDVerificationVisitor() {
		invalidIDs = new ArrayList<>();
	}
	
	@Override
	public void visitUser(User user) {
		this.isValidID(user.getID());
	}

	@Override
	public void visitGroup(Group group) {
		for (CompositeNode cn : group.getGroupUsers()) {
			if (cn instanceof User) {
				visitUser((User) cn);
			}
			else if (cn instanceof Group) {
				visitGroup((Group) cn);
				this.isValidID(group.getID());
			}
		} 
	}
	
	@Override
	public String toString() {
		String str;
		if (invalidIDs.isEmpty()) {
			str = "All IDs are valid.";
		}
		else if (invalidIDs.size() == 1) {
			str = "There is 1 invalid ID: \n" + invalidIDs.get(0);
		}
		else {
			str = "There are " + invalidIDs.size() + " invalid IDs:\n";
			for (String s: invalidIDs) {
				str += s + "\n";
			}
		}
		return str;
	}

	@Override
	public void clear() {
		invalidIDs.clear();
	}
	
	private void isValidID(String id) {
		if (id.contains(" ")) {
			invalidIDs.add(id);
		}
	}
}
