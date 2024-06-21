/* Visitor Interface for Visitor Pattern
 * 4 Subclasses for operations
 * 	TotalUsers
 * 	TotalGroups
 * 	TotalMessages
 * 	PositivePercentage
 */

public interface Visitor {
	public void visitUser(User user);
	public void visitGroup(Group group);
	public String toString();
	public void clear();
}
