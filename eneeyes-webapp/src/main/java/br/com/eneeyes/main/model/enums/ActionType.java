package br.com.eneeyes.main.model.enums;

public enum ActionType {
	UPDATE(0),
    CREATE(1),
    DELETE(2),
    SELECT(3),
    ASSOCIATED(4),
    REMOVED(5);
    
    private final int code;
	
	ActionType(int code) { this.code = code; }

    int code() { return code; }

}
