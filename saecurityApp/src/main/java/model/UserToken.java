package model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class UserToken {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UserTokenUse status;
	private Date created;
	@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="appuser_id")
    private AppUser appUser;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UserTokenUse getStatus() {
		return status;
	}
	public void setStatus(UserTokenUse status) {
		this.status = status;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
	
	
}
