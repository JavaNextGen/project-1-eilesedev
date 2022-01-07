package com.revature.models;

/**
 * Users within the ERS application are categorized within the following roles:
 * <ul>
 *     <li>Employee</li>
 *     <li>Finance Manager</li>
 * </ul>
 *
 * Employees are the standard role for Users within the application.
 *
 * Finance Managers have additional permissions to process reimbursement requests.
 * <ul>
 *     <li>Finance Managers can submit reimbursement requests</li>
 *     <li>Finance Managers cannot process their own requests</li>
 * </ul>
 *
 * @author Center of Excellence
 */
public enum Role {

    EMPLOYEE(1) {
        @Override
        public String toString() {
            return "Employee";
        }
    },
    FINANCE_MANAGER(2) {
        @Override
        public String toString() {
            return "Finance Manager";
        }
    };
	
	private int i; 

	Role(int i) {
		this.i = i; 
	}

	public int getI() {
		return i;
	}
	
//	public static Color convertIntToColor(int iColor) {
//    for (Color color : Color.values()) {
//        if (color.getColorAsInt() == iColor) {
//            return color;
//        }
//    }
//    return null;
//}
	
	public static Role convertInttoRole(int dbint) {
		for(Role role : Role.values()) {
			if(role.getI() == dbint) {
				return role; 
		}
	}
		return null; 
	}

//
//	public Role setI(int i) {
//		this.i = i; 
//		return Role.values()[i]; 
//	}

}


