/*package pc.conf;

import java.util.Set;

import org.reflections.Reflections;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import pc.payments.IPaymentExtensionPoint;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() throws ClassNotFoundException {
    	Reflections paymentTypeR = new Reflections("pc");
		//Reflections controllerR = new Reflections("pc.controllers");

		Set<Class<? extends IPaymentExtensionPoint>> paymentImplementations = paymentTypeR.getSubTypesOf(IPaymentExtensionPoint.class);
		//Set<Class<? extends IPaymentExtensionPoint>> factoryClass = paymentTypeR.getSubTypesOf(IPaymentExtensionPoint.class);

		Class<?> c = Class.forName("pc.payments.IPaymentExtensionPointFactory");
		
		System.out.println("FABRIKA " + c);

		for(Class<?> a : paymentImplementations){
			System.out.println(a.getName());
			//Class.forName(a.getName());
			CustomClassLoader cl = new CustomClassLoader();
			cl.loadClass(a.getName(), true);
			//this.getClass().getClassLoader().loadClass(a.getName());
		}

    }
}
*/