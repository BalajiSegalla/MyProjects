import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings{

	static ApplicationContext context;
	
	@Override
	public <A> A getControllerInstance(Class<A> bean) throws Exception {
		
		return context.getBean(bean);
	}
	@Override
	public void onStart(Application arg0) {
		
		
		context = new ClassPathXmlApplicationContext("configuration.xml");
		
	}

	
}
