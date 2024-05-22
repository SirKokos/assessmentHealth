package ru.sfedu.assessmentHealth.lab5.OneToOne.GenerateKey.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue(generator = "addressKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name ="addressKeyGenerator",strategy = "foreign",
            parameters =
                @org.hibernate.annotations.Parameter(
                name = "property",value = "person"
            )
    )
    protected Integer id;

    @OneToOne(cascade = CascadeType.ALL,optional = false)
    @PrimaryKeyJoinColumn
    protected Person person;
    @Column
    protected String street;
    @Column
    protected String zipCode;
    @Column
    protected  String city;
}
