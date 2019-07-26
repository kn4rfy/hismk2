package com.hisd3.hismk2.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Type
import org.joda.time.DateTime

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
@Table(name = "T_USER")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    String login

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(length = 60)
    String password

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    String firstName

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    String lastName 

    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    String email

    @Column(nullable = false)
    boolean activated = false

    @Size(min = 2, max = 5)
    @Column(name = "lang_key", length = 5)
    String langKey

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    String activationKey

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    String resetKey


    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "reset_date", nullable = true)
    DateTime resetDate

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(name = "T_USER_AUTHORITY",
            joinColumns = [ @JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [@JoinColumn(name = "authority_name", referencedColumnName = "name")])
    Set<Authority> authorities = []


    @JsonIgnore
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "user")
    Set<PersistentToken> persistentTokens = []


    @Transient
    List<String> getRoles(){

        def roles = []
        if (authorities != null)
            authorities.each { auth ->

                roles.add(auth.name)
            }

        return roles
    }


}
