package org.acme.exceptions;

public class BusinessRuleException extends Exception{
    private int status;
    
    public BusinessRuleException() {
        
    }
    
    public BusinessRuleException(String message) {
        super(message);
    }
    
    public BusinessRuleException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
