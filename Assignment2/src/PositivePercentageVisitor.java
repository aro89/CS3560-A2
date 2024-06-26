/* Positive Percentage Visitor class
 * Visitor Pattern
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositivePercentageVisitor implements Visitor {
	private double numPositiveMessages = 0;
	private double totalMessages = 0;
	private List<String> positiveWords = new ArrayList<>
		(Arrays.asList("good", "great", "excellent", "awesome", "nice"));
	
	@Override
	public void visitUser(User user) {
		for (String msg : user.getNewsFeed()) {
			totalMessages++;
			for (String pos : positiveWords) {
				if (msg.toLowerCase().contains(pos)) {
					numPositiveMessages++;
					break;
				}
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
		return "Percentage of Positive Tweets: " + this.getPositivePercentage() + "%";
	}
	
	@Override
	public void clear() {
		numPositiveMessages = 0;
		totalMessages = 0;
	}
	
	public double getPositivePercentage() {
		if (totalMessages == 0) {
			return totalMessages;
		}
		else {
			return Math.round(numPositiveMessages / totalMessages * 100);
		}
	}
}
