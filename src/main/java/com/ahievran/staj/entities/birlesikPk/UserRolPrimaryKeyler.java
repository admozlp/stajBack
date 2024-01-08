package com.ahievran.staj.entities.birlesikPk;

import java.io.Serializable;

import com.ahievran.staj.entities.Rol;
import com.ahievran.staj.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolPrimaryKeyler implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	private Rol rol;
}	
