package model;


public enum Permission {
	CHANGE_ACCOUNT_INFO("change_account_info"),
	DELETE_ACCOUNT("delete_account"),
	ADD_ACCOMMODATION("add_accommodation"),
	LIST_MY_ACCOMMODATIONS("list_my_accommodations"),
	ADD_TIME_SLOT("add_time_slot"),
	DELETE_TIME_SLOT("delete_time_slot")
	;
	
	Permission(String permission) {
		this.permission = permission;
	}
	private final String permission;
	 
	public String getPermission() {
		return permission;
	}
	
	
}

