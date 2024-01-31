package ru.sfedu.assessmentHealth;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import ru.sfedu.assessmentHealth.api.*;
import ru.sfedu.assessmentHealth.model.*;
import ru.sfedu.assessmentHealth.utils.CreateObj;
import ru.sfedu.assessmentHealth.utils.PropertyConfig;
import ru.sfedu.assessmentHealth.utils.ServisUtil;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;


public class Main {
    private static final Logger log = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {

        CommandLineParser parser = new DefaultParser();

        try {
            PropertyConfig propertyConfig = new PropertyConfig();
            log.info("------------------- Working program -------------------");
            CommandLine cmd = parser.parse(getAllOptions(),args);

            //--------------------------------env----------------------------------------------
            if(cmd.hasOption(Const.CLI_ENVIRONMENT_PROPERTIES)){
                String[] arguments = cmd.getOptionValues(Const.CLI_ENVIRONMENT_PROPERTIES);
                propertyConfig.setConfigPath(arguments[0]);
            }
            //--------------------------------Лог----------------------------------------------
            if (cmd.hasOption(Const.CLI_LOGGER)) {
                String[] arguments = cmd.getOptionValues(Const.CLI_LOGGER);
                File file = new File(arguments[0]);
                LoggerContext context = (LoggerContext) LogManager.getContext(false);
                context.setConfigLocation(file.toURI());
            }
            //--------------------------------dType----------------------------------------------
            IDataProvider dataProvider = new DataProviderXml();
            if (cmd.hasOption(Const.CLI_TYPE_BD)) {
                String[] arguments = cmd.getOptionValues(Const.CLI_TYPE_BD);
                switch (arguments[0]) {
                    case "XML" -> dataProvider = new DataProviderXml();
                    case "CSV" -> dataProvider = new DataProviderCsv();
                    case "POSTGRES" -> dataProvider = new DataProviderPost();
                    default -> log.info("Такого типа данных нет.");
                }
                log.info("Установлен {} дата провайдер.", dataProvider.getClass().getName());
            }
            //------------------------------------------------------------------------------
            if (cmd.hasOption(Const.ClI_NEW_SCHEDULE)) {
                String[] arguments = cmd.getOptionValues(Const.ClI_NEW_SCHEDULE);
                Schedule schedule = CreateObj.createSchedule(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]);
                log.info("Создание расписания: {}",schedule);
                dataProvider.insertSchedule(schedule);
            }
            if (cmd.hasOption(Const.ClI_NEW_PREPARATION)) {
                String[] arguments = cmd.getOptionValues(Const.ClI_NEW_PREPARATION);
                Preparation preparation = CreateObj.createPreparation(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]);
                log.info("Создание препаратов: {}",preparation);
                dataProvider.insertPreparation(preparation);
            }
            if (cmd.hasOption(Const.ClI_NEW_DOCTOR)) {
                String[] arguments = cmd.getOptionValues(Const.ClI_NEW_DOCTOR);
                Doctor doctor = CreateObj.createDoctor(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7]);
                doctor.setLinkPreparation(ServisUtil.getListPreparation(dataProvider,arguments[8]));
                doctor.setLinkSchedule(ServisUtil.getListSchedule(dataProvider,arguments[9]));
                log.info("Создание врача: {}",doctor);
                dataProvider.insertDoctor(doctor);
            }

            if(cmd.hasOption(Const.ClI_NEW_PATIENT)){
                String[] arguments = cmd.getOptionValues(Const.ClI_NEW_PATIENT);
                Patient patient = CreateObj.createPatient(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7],arguments[8],arguments[9]);
                log.info("Создание пациента: {}",patient);
                dataProvider.insertPatient(patient);
            }

            if(cmd.hasOption(Const.ClI_NEW_CALC_REPORT)){
                String[] arguments = cmd.getOptionValues(Const.ClI_NEW_CALC_REPORT);
                CalcReport calcReport = CreateObj.createCalcReport(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]);
                 List<Patient> patient = dataProvider.selectPatientId(Integer.valueOf(arguments[5])).stream().toList();
                 List<Doctor> doctor = dataProvider.selectDoctorId(Integer.valueOf(arguments[6])).stream().toList();
                calcReport.setPatient(patient);
                calcReport.setDoctor(doctor);
                log.info("Создание отчета: {}",calcReport);
                dataProvider.insertCalcReport(calcReport);
            }

            if(cmd.hasOption(Const.ClI_ALL_DOCTOR)){
                Optional<List<Doctor>> allDoctor = dataProvider.selectAllDoctor();
                allDoctor.get().forEach(i-> log.info("Doctor-->{} \n",i));
            }
            //------------------------------------------------------------------------------

            Servis servis = new Servis();

            if(cmd.hasOption(Const.ClI_VISIT_DOCTOR)){
                log.info("Input id patient process visitDoctor: ");
                String[] arguments = cmd.getOptionValues(Const.ClI_VISIT_DOCTOR);
                Optional<Patient> patient = dataProvider.selectPatientId(Integer.valueOf(arguments[0]));
                log.info("process visitDoctor.....: {}",patient);
                servis.visitDoctor(patient.get());
            }

            if(cmd.hasOption(Const.ClI_ARIVIAL_DOCTOR)){
                log.info("Input id patient process arivialDoctor: ");
                String[] arguments = cmd.getOptionValues(Const.ClI_ARIVIAL_DOCTOR);
                Optional<Patient> patient = dataProvider.selectPatientId(Integer.valueOf(arguments[0]));
                log.info("process arivialDoctor.....: {}",patient);
                servis.arivialDoctor(patient.get(),dataProvider);
            }

            if(cmd.hasOption(Const.ClI_CALCULATE_PRICE)){
                log.info("Input id (patient,doctor) and flag (True or False) process visitDoctor: ");
                String[] arguments = cmd.getOptionValues(Const.ClI_CALCULATE_PRICE);
                Optional<Patient> patient = dataProvider.selectPatientId(Integer.valueOf(arguments[0]));
                Optional<Doctor> doctor = dataProvider.selectDoctorId(Integer.valueOf(arguments[1]));
                Boolean createFile = Boolean.valueOf(arguments[2]);
                log.info("process calculatePrice.....: {}",patient);
                servis.calculatePrice(patient.get(),doctor.get(),createFile);
            }

            //------------------------------------------------------------------------------










        }
        catch (ArrayIndexOutOfBoundsException e){
            log.info("Неверное количество аргументов");
        }catch (NumberFormatException e) {
            log.info("Некорректные данные");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        printHelp(
                getAllOptions(), // опции по которым составляем help
                500, // ширина строки вывода
                "Options", // строка предшествующая выводу
                "-- HELP --", // строка следующая за выводом
                3, // число пробелов перед выводом опции
                5, // число пробелов перед выводом опцисания опции
                true, // выводить ли в строке usage список команд
                System.out // куда производить вывод
        );

        
    }


    /**
     * Метод нужен для генерации опцией командной строки
     * @return Options
     */
    private static Options getAllOptions(){
        Options options = new Options();



        Option optionEnv = new Option(Const.CLI_ENVIRONMENT_PROPERTIES,Const.CLI_ENVIRONMENT_PROPERTIES_LONG,
                true,Const.CLI_ENVIRONMENT_PROPERTIES_DESCRIPTION);
        optionEnv.setArgs(1);

        Option optionLog = new Option(Const.CLI_LOGGER,Const.CLI_LOGGER_LONG,
                true,Const.CLI_LOGGER_DESCRIPTION);
        optionLog.setArgs(1);

        Option optionDataType = new Option(Const.CLI_TYPE_BD,Const.CLI_TYPE_BD_LONG,
                true,Const.CLI_DESCRIPTION_TYPE_BD);
        optionLog.setArgs(1);


        Option optionDoctor = new Option(Const.ClI_NEW_DOCTOR,Const.ClI_NEW_DOCTOR_LONG,
                true,Const.ClI_DESCRIPTION_NEW_DOCTOR);
        optionDoctor.setArgs(10);

        Option optionPatient = new Option(Const.ClI_NEW_PATIENT,Const.ClI_NEW_PATIENT_LONG,
                true,Const.ClI_DESCRIPTION_NEW_PATIENT);
        optionPatient.setArgs(10);


        Option optionPreparation = new Option(Const.ClI_NEW_PREPARATION,Const.ClI_NEW_PREPARATION_LONG,
                true,Const.ClI_DESCRIPTION_NEW_PREPARATION);
        optionPreparation.setArgs(5);


        Option optionSchedule = new Option(Const.ClI_NEW_SCHEDULE,Const.ClI_NEW_SCHEDULE_LONG,
                true,Const.ClI_DESCRIPTION_NEW_SCHEDULE);
        optionSchedule.setArgs(5);

        Option optionCalc = new Option(Const.ClI_NEW_CALC_REPORT,Const.ClI_NEW_CALC_REPORT_LONG,
                true,Const.ClI_DESCRIPTION_NEW_CALC_REPORT);
        optionCalc.setArgs(5);

        Option optionAllDoctor = new Option(Const.ClI_ALL_DOCTOR,Const.ClI_ALL_DOCTOR_LONG,
                false,Const.ClI_DESCRIPTION_ALL_DOCTOR);
        optionAllDoctor.setArgs(0);


        Option visitDoctor = new Option(Const.ClI_VISIT_DOCTOR,
                true,
                Const.ClI_DESCRIPTION_VISIT_DOCTOR);
        visitDoctor.setArgs(1);


        Option totalPrice = new Option(Const.ClI_CALCULATE_PRICE,
                true,
                Const.ClI_DESCRIPTION_CALCULATE_PRICE);
        totalPrice.setArgs(3);

        Option arivialDoctor = new Option(Const.ClI_ARIVIAL_DOCTOR,
                true,
                Const.ClI_DESCRIPTION_ARIVIAL_DOCTOR);
        arivialDoctor.setArgs(1);



        options.addOption(optionEnv);
        options.addOption(optionLog);
        options.addOption(optionDataType);
        options.addOption(optionDoctor);
        options.addOption(optionPatient);
        options.addOption(optionPreparation);
        options.addOption(optionSchedule);
        options.addOption(optionCalc);
        options.addOption(optionAllDoctor);

        options.addOption(visitDoctor);
        options.addOption(totalPrice);
        options.addOption(arivialDoctor);




        return options;
    }
    public static void printHelp(
            final Options options,
            final int printedRowWidth,
            final String header,
            final String footer,
            final int spacesBeforeOption,
            final int spacesBeforeOptionDescription,
            final boolean displayUsage,
            final OutputStream out) {
        final String commandLineSyntax = "java -jar accessHealth.jar";
        final PrintWriter writer = new PrintWriter(out);
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);
        writer.flush();
    }

}