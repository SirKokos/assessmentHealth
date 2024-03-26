package ru.sfedu.assessmentHealth.lab2.model;










import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "test_entity_name")
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "TEST_ENTITY_NAME", nullable = false)
    private String name;
    @Column(name = "TEST_ENTITY_DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "TEST_ENTITY_DATE_CREATED", nullable = false)
    private Date dateCreated;
    @Column(name = "TEST_ENTITY_CHECK", nullable = false)
    private  Boolean check;

    @Embedded
    private Person person;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Boolean getCheck() {
        return check;
    }
    public void setCheck(Boolean check) {
        this.check = check;
    }
    public Person getPerson() {return person;}

    public void setPerson(Person person) {this.person = person;}


    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", check=" + check +
                ", person=" + person +
                '}';
    }
}
