package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class AppUser implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private String username;
	private String password;
	private String adress;
	private boolean activated;
	@Enumerated(EnumType.STRING)
	private Role role;
	@JsonManagedReference
    @OneToMany(mappedBy="appUser", fetch = FetchType.EAGER)
    private Set<Accommodation> accommodations = new HashSet<Accommodation>();
	@JsonManagedReference
    @OneToMany(mappedBy="appUser", fetch = FetchType.EAGER)
    private Set<TimeSlot> timeSlot = new HashSet<TimeSlot>();
	@JsonManagedReference
    @OneToMany(mappedBy="appUser", fetch = FetchType.EAGER)
    private Set<UserToken> tokens = new HashSet<UserToken>();

	
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public Set<UserToken> getTokens() {
		return tokens;
	}
	public void setTokens(Set<UserToken> tokens) {
		this.tokens = tokens;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Set<Accommodation> getAccommodations() {
		return accommodations;
	}
	public void setAccommodations(Set<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}
	
	public Set<TimeSlot> getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(Set<TimeSlot> timeSlot) {
		this.timeSlot = timeSlot;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	
}
