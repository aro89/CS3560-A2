/* Group Class
 * Composite Pattern
 * Observer Pattern
 * Visitor Pattern
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

public class User extends Subject implements CompositeNode, Observer {
	private DefaultMutableTreeNode userNode;
	private String userID;
	private Set<User> followings;
	private List<String> newsFeed;
	private int messageCount;
	private long creationTime;
	private long lastUpdateTime;
    
	public User(String userID) {
		this.userID = userID;
		followings = new HashSet<>();
		newsFeed = new ArrayList<>();
		userNode = new DefaultMutableTreeNode(this);
		creationTime = System.currentTimeMillis();
		lastUpdateTime = creationTime;
    }
    
	@Override
	public String getID() {
		return this.userID;
	}

	@Override
	public DefaultMutableTreeNode getCompositeNode() {
		return this.userNode;
	}
	
	@Override
	public CompositeNode getRoot() {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.userNode.getRoot();
		return (CompositeNode) root.getUserObject();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUser(this);		
	}

	@Override
	public void update(Subject subject, String message) {
		if (subject instanceof User) {
			if (message == null) {
				// do nothing
			}
			else {
				this.newsFeed.add(((User) subject).getID() + ": " + message);
				this.lastUpdateTime = System.currentTimeMillis();
				this.getUserView().update(this, message);
			}
		}
	}
	
	@Override
	public boolean addCompositeNode(CompositeNode cn) {
		return false;
	}
	
	@Override
	public String toString() {
		return "User: " + userID;
	}
	
	public void addMessage(String message) {
		this.newsFeed.add(this.getID() + ": " + message);
		messageCount++;
		this.lastUpdateTime = System.currentTimeMillis();
		this.notifyObservers(message);
	}
	
	public void followUser(User userToFollow) {
		this.followings.add(userToFollow);
		userToFollow.attach(this);
		this.notifyObservers(null);
	}
	
	public int getMessageCount() {
		return this.messageCount;
	}
	
	public Set<User> getFollowings() {
		return this.followings;
	}
	
	public List<String> getNewsFeed() {
		return this.newsFeed;
	}

	public String getFollowingsAsString() {
		String result = "";
		for (User temp : followings) {
			result += temp.toString() + "\n";
		}
		return result;
	}
	
	public String getNewsFeedAsString() {
		String result = "";
		for (String msg : newsFeed) {
			result += msg + "\n";
		}
		return result;
	}
	
	//AS3: getters for new fields
	public long getCreationTime() {
		return this.creationTime;
	}
	
	public long getLastUpdateTime() {
		return this.lastUpdateTime;
	}
}
