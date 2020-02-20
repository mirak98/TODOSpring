package org.udg.pds.springtodo.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "usergroup")
public class Group{
    @Id
	private String name;

    @NotNull
	private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User owner;

	public Group(){
	}

}
