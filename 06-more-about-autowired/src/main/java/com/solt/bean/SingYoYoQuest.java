package com.solt.bean;

import org.springframework.stereotype.Component;
import static com.solt.bean.QuestType.Type.*;

@Component @QuestType(YOYO)
public class SingYoYoQuest implements Quest {
	@Override 
	public String goQuest() {
		// TODO Auto-generated method stub
		return "Singing Yo Yo Song";
	}
}
