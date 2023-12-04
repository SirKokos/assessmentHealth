package ru.sfedu.assessmentHealth.model;

import java.util.List;
import java.util.Objects;

public class CalcReport {
    private Integer Report_ID;
    private List<Integer> FkToDoctor;
    private Integer FkToPatient;
    private String PatientName;
    private String NameDoctor;
    private Boolean BloodAnalyse;
    private Boolean GlucoseAnalyse;
    private Boolean HormoneAnalyse;
    private Boolean ArterialAnalyse;
    private double price;


    public Integer getReport_ID() {return Report_ID;}
    public List<Integer> getFkToDoctor(){return FkToDoctor;}
    public Integer getFkToPatient(){return FkToPatient;}
    public String getPatientName(){return PatientName;}
    public String getNameName(){return NameDoctor;}
    public boolean getBloodAnalyse(){return BloodAnalyse;}
    public Boolean getGlucoseAnalyse(){return GlucoseAnalyse;}
    public Boolean getHormoneAnalyse(){return HormoneAnalyse;}
    public Boolean getArterialAnalyse(){return  ArterialAnalyse;}
    public double getPrice(){return price;}


    public void setReport_ID(Integer Report_ID){this.Report_ID = Report_ID;}
    public void setFkToDoctor(Integer FkToDoctor){this.FkToDoctor.add(FkToDoctor);}
    public void setFkToPatient(Integer FkToPatient){this.FkToPatient = FkToPatient;}
    public void setPatientName(String PatientName){this.PatientName = PatientName;}
    public void setNameName(String NameDoctor){this.NameDoctor = NameDoctor;}
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
