package com.solt.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import static com.solt.bean.QuestType.Type.*;

import java.util.Optional;

/*
 * 1. find by type , found it , finished.
 * 2. @Primary 
 * 3. @Qualifier
 * 4. Narrow down by name
 * 5. Throw Error (NoSuchBeanDefinationException, NoUniqueBeanDefinationException)
 */

@Component
public class Knight {
//	@Autowired
//	@QuestType(PRINCESS)
	private Quest princess;
	
	
	/*
	 * required = false
	 * @Nullable
	 * Optional generic
	 */

	@Autowired
	@QuestType(MATROMONY)
	private void setPrincess(Quest princess, 
			Optional<MyClass> myClass) {
		this.princess = princess;
	}

	public String goQuest() {
		return princess.goQuest();
	}
}
