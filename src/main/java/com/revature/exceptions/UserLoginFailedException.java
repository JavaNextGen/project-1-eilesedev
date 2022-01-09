package com.revature.exceptions;

import java.io.IOException;

public class UserLoginFailedException extends IOException{


	public UserLoginFailedException() {
	        super();
	    }

	    public UserLoginFailedException(String message) {
	       super(message); 
	    }

	    public UserLoginFailedException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public UserLoginFailedException(Throwable cause) {
	        super(cause);
	    }


}
