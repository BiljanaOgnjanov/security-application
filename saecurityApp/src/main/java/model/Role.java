package model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
	GUEST(			
			Set.of(
					Permission.CHANGE_ACCOUNT_INFO,
					Permission.DELETE_ACCOUNT
			)),
	HOST(
			Set.of(
					Permission.CHANGE_ACCOUNT_INFO,
					Permission.DELETE_ACCOUNT,
					Permission.ADD_ACCOMMODATION,
					Permission.LIST_MY_ACCOMMODATIONS,
					Permission.ADD_TIME_SLOT,
					Permission.DELETE_TIME_SLOT
					)
	)
	;
	
	Role(Set<Permission> permissions) {
		this.permissions = permissions;
		
	}

	private final Set<Permission> permissions;
	
	
	
	public Set<Permission> getPermissions() {
		return permissions;
	}



	public List<SimpleGrantedAuthority> getAuthorities(){
		List<SimpleGrantedAuthority> authorities = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return authorities;
	}
}