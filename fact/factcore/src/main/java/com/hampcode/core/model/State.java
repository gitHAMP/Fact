package com.hampcode.core.model;

public enum State {

	EMITIDO(1), ELIMINADO(2), ANULADO(3);
	
	private final int codeState;
	
	State(int codeSate) {
		this.codeState=codeSate;
	}
	
	public int getCodeState() {
		return this.codeState;
	}

}
