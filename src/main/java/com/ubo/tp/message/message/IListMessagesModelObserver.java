package main.java.com.ubo.tp.message.message;

import main.java.com.ubo.tp.message.datamodel.Message;

import java.util.Set;

public interface IListMessagesModelObserver {

	void listMessagesChanged(Set<Message> referenceList);
}
