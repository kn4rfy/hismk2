package com.hisd3.hismk2.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
@Table(name = "T_AUTHORITY")
class Authority {

    @NotNull
    @Size(min = 0, max = 50)
    @Id
    @Column(length = 50)
    String name
}
