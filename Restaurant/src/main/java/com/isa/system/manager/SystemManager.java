package com.isa.system.manager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.isa.system.manager.Preset;
import com.isa.user.User;
@Entity
@Table(name="system_managers")
public class SystemManager extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sys_manager_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column (name = "preset_manager")
	private Preset preset;

	SystemManager() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Preset getPreset() {
		return preset;
	}

	public void setPreset(Preset preset) {
		this.preset = preset;
	}

	public SystemManager(Long id, Preset preset) {
		super();
		this.id = id;
		this.preset = preset;
	}
	
	
	
	
}
