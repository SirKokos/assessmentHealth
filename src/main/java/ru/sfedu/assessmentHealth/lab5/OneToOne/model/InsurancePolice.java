package ru.sfedu.assessmentHealth.lab5.OneToOne.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.Objects;


@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class InsurancePolice {
//    @GeneratedValue(generator = "addPoliceGenerator")
//    @GenericGenerator(
//       name = "addPoliceGenerator",
//            strategy = "foreign",
//            parameters = @Parameter(
//                    name = "property",
//                    value = "user"
//            )
//    )
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer Id;
    @NotNull
    protected String PoliceNumber;
    @NotNull
    protected Date BeginningOfInsurance;
    @NotNull
    protected Date EndOfInsurance;
    @NotNull
    protected Float InsuranceAmount;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        InsurancePolice that = (InsurancePolice) object;
        return Objects.equals(Id, that.Id) && Objects.equals(PoliceNumber, that.PoliceNumber) && Objects.equals(BeginningOfInsurance, that.BeginningOfInsurance) && Objects.equals(EndOfInsurance, that.EndOfInsurance) && Objects.equals(InsuranceAmount, that.InsuranceAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, PoliceNumber, BeginningOfInsurance, EndOfInsurance, InsuranceAmount);
    }
}
