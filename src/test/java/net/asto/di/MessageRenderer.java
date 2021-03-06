package net.asto.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageRenderer {
	private MessageProvider messageProvider;
	
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}

	public void render(){
		System.out.println(messageProvider.getMessage());
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("di.xml");
		MessageRenderer renderer = (MessageRenderer) ac.getBean("messageRenderer");
		renderer.render();
	}
}
