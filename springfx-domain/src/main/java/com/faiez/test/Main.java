package com.faiez.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

	@SuppressWarnings("resource")
	public static void main(String...args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		Main main = context.getBean(Main.class);
		main.sayHello("Satesh");
	}

	@Autowired
	private HelloService helloService;

	private void sayHello(String name) {
		System.out.println(helloService.sayHello(name));
	}

}
