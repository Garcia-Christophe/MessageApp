package main.java.com.ubo.tp.message.sign;

import main.java.com.ubo.tp.message.datamodel.User;

public interface ISwitchSignViewObserver {

	void notifySwitchToSignInView();

	void notifySwitchToSignUpView();
}
