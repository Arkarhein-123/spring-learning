package com.example.demo.register;

import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

import com.example.demo.bean.DbServer;
import com.example.demo.bean.FileServer;
import com.example.demo.bean.Server;

public class ServerRegistrar implements BeanRegistrar {
	
	@Override
	public void register(BeanRegistry registry, Environment env) {
		String serverName = env.getRequiredProperty("server.name");
		Class<? extends Server> className = switch(serverName) {
			case "file" -> FileServer.class;
			case "db" 	-> DbServer.class;
			default -> throw new IllegalArgumentException(serverName);
		};
		
		registry.registerBean(className);
	}
}
