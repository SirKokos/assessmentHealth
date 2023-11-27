package ru.sfedu.assessmentHealth.model;

public class CalcReport {
    private String PatientName;
    private String NameDoctor;
    private Boolean BloodAnalyse;
    private Boolean GlucoseAnalyse;
    private Boolean HormoneAnalyse;
    private Boolean ArterialAnalyse;
    private double price;


    public String getPatientName(){return PatientName;}
    public String getNameName(){return NameDoctor;}
    public boolean getBloodAnalyse(){return BloodAnalyse;}
    public Boolean getGlucoseAnalyse(){return GlucoseAnalyse;}
    public Boolean getHormoneAnalyse(){return HormoneAnalyse;}
    public Boolean getArterialAnalyse(){return  ArterialAnalyse;}
    public double getPrice(){return price;}

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
}
