package com.solt.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import static com.solt.bean.QuestType.Type;

@Component @QuestType(Type.DRAGON)
public class KillDragonQuest implements Quest {
	@Override
	public String goQuest() {
		// TODO Auto-generated method stub
		return "Knight killed the dragon.";
	}
}
