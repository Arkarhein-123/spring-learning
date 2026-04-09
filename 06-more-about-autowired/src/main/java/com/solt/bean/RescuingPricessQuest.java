package com.solt.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import static com.solt.bean.QuestType.Type;



@Component @QuestType(Type.PRINCESS)
public class RescuingPricessQuest implements Quest {
	@Override
	public String goQuest() {
		return "Knight is going to rescue the pricess";
	}
}
