package ru.sfedu.assessmentHealth.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.assessmentHealth.CONST;

import java.util.List;
import java.util.Objects;

@Root
public class CalcReport {
    @Element()
    @CsvBindByPosition(position = 0)
    private Integer Report_ID;
    @Element()
    @CsvBindByPosition(position = 1)
    private Integer FkToDoctor;
    @Element()
    @CsvBindByPosition(position = 2)
    private Integer FkToPatient;
    @Element()
    @CsvBindByPosition(position = 3)
    private String PatientName;
    @Element()
    @CsvBindByPosition(position = 4)
    private String NameDoctor;
    @Element()
    @CsvBindByPosition(position = 5)
    private Boolean BloodAnalyse;
    @Element()
    @CsvBindByPosition(position = 6)
    private Boolean GlucoseAnalyse;
    @Element()
    @CsvBindByPosition(position = 7)
    private Boolean HormoneAnalyse;
    @Element()
    @CsvBindByPosition(position = 8)
    private Boolean ArterialAnalyse;
    @Element()
    @CsvBindByPosition(position = 9)
    private double price;


    public Integer getReport_ID() {return Report_ID;}
    public Integer getFkToDoctor(){return FkToDoctor;}
    public Integer getFkToPatient(){return FkToPatient;}
    public String getPatientName(){return PatientName;}
    public String getNameDoctor(){return NameDoctor;}
    public boolean getBloodAnalyse(){return BloodAnalyse;}
    public Boolean getGlucoseAnalyse(){return GlucoseAnalyse;}
    public Boolean getHormoneAnalyse(){return HormoneAnalyse;}
    public Boolean getArterialAnalyse(){return  ArterialAnalyse;}
    public double getPrice(){return price;}


    public void setReport_ID(Integer Report_ID){this.Report_ID = Report_ID;}
    public void setFkToDoctor(Integer FkToDoctor){this.FkToDoctor = (FkToDoctor);}
    public void setFkToPatient(Integer FkToPatient){this.FkToPatient = FkToPatient;}
    public void setPatientName(String PatientName){this.PatientName = PatientName;}
    public void setNameDoctor(String NameDoctor){this.NameDoctor = NameDoctor;}
    public void setBloodAnalyse(Boolean BloodAnalyse){ this.BloodAnalyse = BloodAnalyse;}
    public void setGlucoseAnalyse(Boolean GlucoseAnalyse){this.GlucoseAnalyse = GlucoseAnalyse;}
    public void setHormoneAnalyse(Boolean HormoneAnalyse){this.HormoneAnalyse = HormoneAnalyse;}
    public void setArterialAnalyse(Boolean ArterialAnalyse){this.ArterialAnalyse = ArterialAnalyse ;}
    public void setPrice(double price){this.price = price;}

    @Override
    public String toString() {
        return "CalcReport{PatientName = " + PatientName +
                ",NameDoctor = " + NameDoctor +
                ",BloodAnalyse =  " + BloodAnalyse +
                ",GlucoseAnalyse = " + GlucoseAnalyse +
                ",HormoneAnalyse = " + HormoneAnalyse +
                ",ArterialAnalyse = " + ArterialAnalyse +
                ",price =" + price +
                "  }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalcReport that)) return false;
        return Double.compare(that.price, price) == 0 && Objects.equals(Report_ID, that.Report_ID) && Objects.equals(FkToDoctor, that.FkToDoctor) && Objects.equals(FkToPatient, that.FkToPatient) && Objects.equals(PatientName, that.PatientName) && Objects.equals(NameDoctor, that.NameDoctor) && Objects.equals(BloodAnalyse, that.BloodAnalyse) && Objects.equals(GlucoseAnalyse, that.GlucoseAnalyse) && Objects.equals(HormoneAnalyse, that.HormoneAnalyse) && Objects.equals(ArterialAnalyse, that.ArterialAnalyse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Report_ID, FkToDoctor, FkToPatient, PatientName, NameDoctor, BloodAnalyse, GlucoseAnalyse, HormoneAnalyse, ArterialAnalyse, price);
    }
}
