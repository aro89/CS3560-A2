/* Subject Abstract Class
 * Observer Pattern
 * Subclass
 * 	User
 */

import java.util.HashSet;
import java.util.Set;

public abstract class Subject {
	private Set<Observer> followers = new HashSet<Observer>();
	private UserView connection;

	public void attach(Observer observer) {
		followers.add(observer);
	}
	
	public void notifyObservers(String message) {
		for (Observer observer : this.followers) {
			observer.update(this, message);
		}
	}
	
	public UserView getUserView() {
		return connection;
	}

	public void setUserView(UserView connection) {
		this.connection = connection;
	}
	
	public Set<Observer> getFollowers() {
		return this.followers;
	}
}
