package main.java.com.ubo.tp.message.user;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

import java.util.Set;

public interface IListUsersModelObserver {

	void listUsersChanged(Set<User> referenceList);
}
