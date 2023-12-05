package ru.sfedu.assessmentHealth.model;

public class Patient extends Person{
//    private Integer Patient_ID;
    private double CellsBlood;
    private double Hemoglobin;
    private double Platelets;
    private double Testosterone;
    private double Glucose;
    private double Cholesterol;
    private short ArterialPress;
    private StatusVisit statusVisit;

//    public Integer getDoctor_ID(){return Patient_ID;}

    public double getCellsBlood(){return CellsBlood;}
    public double getHemoglobin(){return Hemoglobin;}
    public double getPlatelets(){return Platelets;}
    public double getTestosterone(){return Testosterone;}
    public double getGlucose(){return Glucose;}
    public double getCholesterol(){return Cholesterol;}
    public short getArterialPress(){return ArterialPress;}
    public StatusVisit getStatusVisit(){return statusVisit;}

//    public void setDoctor_ID(Integer Patient_ID){this.Patient_ID = Patient_ID;}
    public void setCellsBlood(double CellsBlood){this.CellsBlood = CellsBlood;}
    public void setHemoglobin(double Hemoglobin){this.Hemoglobin = Hemoglobin;}
    public void setPlatelets(double Platelets){this.Platelets = Platelets;}
    public void setTestosterone(double Testosterone){this.Testosterone = Testosterone;}
    public void setGlucose(double Glucose){this.Glucose = Glucose;}
    public void setCholesterol(double Cholesterol){this.Cholesterol = Cholesterol;}
    public void setArterialPress(short ArterialPress){this.ArterialPress= ArterialPress;}
    public void setStatusVisit(StatusVisit statusVisit){this.statusVisit = statusVisit;}

    @Override
    public String toString() {
        return "Patient{ Name = " + Name +
                ",Surname =  " + Surname +
                ",SecondName = " + SecondName +
                ",Age = " + Age +
                ",Gender = " + Gender +
                ",Status = " + Status +
                ",CellsBlood = " + CellsBlood +
                ",Hemoglobin = " + Hemoglobin +
                ",Platelets = " + Platelets +
                ",Testosterone = " + Testosterone +
                ",Glucose = " + Glucose +
                ",Cholesterol = " + Cholesterol +
                ",ArterialPress = " + ArterialPress +
                ",statusVisit = " + statusVisit +
                " }";
    }


}
