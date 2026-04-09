package com.solt.bean;

import org.springframework.stereotype.Component;
import static com.solt.bean.QuestType.Type.*;

@Component @QuestType(MATROMONY)
public class GreatMtrimonyQeust implements Quest{
	@Override
	public String goQuest() {
		// TODO Auto-generated method stub
		return "Make American Great Again";
	}
}
