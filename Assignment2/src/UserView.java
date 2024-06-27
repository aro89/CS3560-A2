/* User View
 * Independent Frame For Each User
 * Observer Pattern
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserView extends JFrame implements Observer{
	private TextAndButtonPanel follow, tweet;
	private JTextArea curFollowing, newsFeed;
	private JFrame userFrame;
	private JLabel creationTime, lastUpdateTime;
	
	public UserView(User user) {
		super(user.getID());       
		JPanel main = new JPanel(new GridLayout(3, 1));
		
		//Top Panel for following
		JPanel sub1 = new JPanel(new GridLayout(2, 1));
		follow = new TextAndButtonPanel("Follow User");
		sub1.add(follow);
		curFollowing = new JTextArea(5, 20);
		curFollowing.setEditable(false);
		curFollowing.setLineWrap(true);
		JScrollPane pane1 = new JScrollPane(curFollowing);
		sub1.add(pane1);
		
		follow.getButton().addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = follow.getField().getText();
				List<User> free = ((Group) user.getRoot()).getAllUsers();
				User fcandidate = null;
				for (User temp : free) {
					if (temp.getID().equals(id)) {
						fcandidate = temp;
					}
				}
				if (fcandidate != null && !fcandidate.getID().equals(user.getID())) {
					user.followUser(fcandidate);
					//fcandidate.attach(user);
				}
			}
		}); 
		
		//Middle Panel for News Feed
		JPanel sub2 = new JPanel(new GridLayout(2, 1));
		tweet = new TextAndButtonPanel("Post Tweet");
		sub2.add(tweet);
		newsFeed = new JTextArea(5, 20);
		newsFeed.setEditable(false);
		newsFeed.setLineWrap(true);
		JScrollPane pane2 = new JScrollPane(newsFeed);
		sub2.add(pane2);
		
		tweet.getButton().addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = tweet.getField().getText();
				user.addMessage(msg);
			}
		}); 
		
		
		//Bottom Panel for Time
		JPanel sub3 = new JPanel(new GridLayout(2,1));
		creationTime = new JLabel("Creation Time: " + new Date(user.getCreationTime()));
		lastUpdateTime = new JLabel("Last Update Time: " + new Date(user.getLastUpdateTime()));
		sub3.add(creationTime);
		sub3.add(lastUpdateTime);
		
		curFollowing.setText(user.getFollowingsAsString());
		newsFeed.setText(user.getNewsFeedAsString());
		main.add(sub1);
		main.add(sub2);
		main.add(sub3);
		add(main);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	pack();
        	setLocationRelativeTo(getRootPane());
        	setVisible(true);
	}

	@Override
	public void update(Subject subject, String message) {
		if (subject instanceof User) {
			if (message == null) {
				curFollowing.setText(((User) subject).getFollowingsAsString());
			}
			else {
				newsFeed.setText(((User) subject).getNewsFeedAsString());
				lastUpdateTime.setText("Last Update Time: " 
						+ new Date(((User) subject).getLastUpdateTime()));
			}
				
		}	
	}
}


