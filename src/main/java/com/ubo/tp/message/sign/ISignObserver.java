package main.java.com.ubo.tp.message.sign;

import main.java.com.ubo.tp.message.datamodel.User;

public interface ISignObserver {

	void notifySignIn(User connectedUser);

	void notifySignOut();
}
