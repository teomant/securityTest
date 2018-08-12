package org.teomant.securityTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "user_id")
@Entity
@Table(name = "authorities")
public class AuthoritiesEntity {

    @Id
    @SequenceGenerator( name = "authorities_sequence", sequenceName = "authorities_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "authorities_sequence" )
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    @Column(name = "authority")
    private String authority;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;
}
