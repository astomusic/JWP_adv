package net.asto.di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/di.xml")
public class MessageRendererTest {
	@Autowired
	private MessageRenderer messagrRenderer;
	
	@Test
	public void renderer() {
		messagrRenderer.render();
	}

}
