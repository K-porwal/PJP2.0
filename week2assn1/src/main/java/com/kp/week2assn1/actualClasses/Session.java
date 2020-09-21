package com.kp.week2assn1.actualClasses;

import java.util.ArrayList;
import java.util.List;

public class Session {

	private int id;
	private List<Expression> expressionList;

	public Session(int id) {
		this.id = id;
		this.expressionList = new ArrayList<Expression>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Expression> getExpressionList() {
		return expressionList;
	}

	public void setExpressionList(List<Expression> expressionList) {
		this.expressionList = expressionList;
	}
}