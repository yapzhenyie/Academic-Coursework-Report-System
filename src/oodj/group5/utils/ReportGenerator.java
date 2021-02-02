package oodj.group5.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Intake;
import oodj.group5.objects.Module;
import oodj.group5.objects.Result;
import oodj.group5.objects.users.Admin;
import oodj.group5.objects.users.Lecturer;
import oodj.group5.objects.users.Student;
import oodj.group5.objects.users.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class ReportGenerator {

    public static void generateSummaryUsersReport(boolean open) {
        ArrayList<Paragraph> para = new ArrayList<>();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, Font.NORMAL, Color.BLACK);
        Paragraph para1 = new Paragraph("Admin: " + Admin.getAdmins().size(), font);
        Paragraph para2 = new Paragraph("Lecturer: " + Lecturer.getLecturers().size(), font);
        Paragraph para3 = new Paragraph("Student: " + Student.getStudents().size(), font);
        Paragraph para4 = new Paragraph("Total: " + (Admin.getAdmins().size() + Lecturer.getLecturers().size() + Student.getStudents().size()), font);
        para.add(para1);
        para.add(para2);
        para.add(para3);
        para.add(para4);

        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("Admin", Admin.getAdmins().size());
        dataSet.setValue("Lecturer", Lecturer.getLecturers().size());
        dataSet.setValue("Student", Student.getStudents().size());

        writeChartToPDF(generatePieChart("Total Number of Users", dataSet), 600, 400, "ReportSummaryUsers.pdf", para);

        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportSummaryUsers.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateModulesReport(boolean open) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ReportModules.pdf"));
            document.open();

            Paragraph title = new Paragraph("Modules", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 28, Font.NORMAL, Color.BLACK));
            document.add(title);

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, Font.NORMAL, Color.BLACK);
            Paragraph totalModules = new Paragraph("Total Modules: " + AcademicCourseworkReportSystem.MODULES.size(), font);
            document.add(totalModules);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            //Set Column widths
            float[] columnWidths = {0.1f, 0.4f, 0.75f, 0.37f};
            table.setWidths(columnWidths);

            PdfPCell column1 = new PdfPCell(new Paragraph("No"));
            column1.setHorizontalAlignment(Element.ALIGN_CENTER);
            column1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell column2 = new PdfPCell(new Paragraph("Module Code"));
            column2.setHorizontalAlignment(Element.ALIGN_CENTER);
            column2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell column3 = new PdfPCell(new Paragraph("Module Name"));
            column3.setHorizontalAlignment(Element.ALIGN_CENTER);
            column3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell column4 = new PdfPCell(new Paragraph("Lecturer"));
            column4.setHorizontalAlignment(Element.ALIGN_CENTER);
            column4.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(column1);
            table.addCell(column2);
            table.addCell(column3);
            table.addCell(column4);

            int i = 1;
            for (Module module : AcademicCourseworkReportSystem.MODULES) {
                PdfPCell rowNo = new PdfPCell(new Paragraph(String.valueOf(i++)));
                rowNo.setHorizontalAlignment(Element.ALIGN_CENTER);
                rowNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(rowNo);

                PdfPCell rowModuleCode = new PdfPCell(new Paragraph(module.getModuleCode()));
                rowModuleCode.setHorizontalAlignment(Element.ALIGN_LEFT);
                rowModuleCode.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(rowModuleCode);

                PdfPCell rowmoduleName = new PdfPCell(new Paragraph(module.getModuleName()));
                rowmoduleName.setHorizontalAlignment(Element.ALIGN_LEFT);
                rowmoduleName.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(rowmoduleName);

                PdfPCell rowlecturer = new PdfPCell(new Paragraph(module.getAssignedLecturer().getName()));
                rowlecturer.setHorizontalAlignment(Element.ALIGN_LEFT);
                rowlecturer.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(rowlecturer);
            }
            document.add(table);
            document.close();
            writer.close();

        } catch (FileNotFoundException e) {
            DialogUtils.showErrorMessageDialog(null, "You need to close the file before the new data will be generated.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportModules.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateSummaryStudentsEnrolledReport(boolean open) {
        HashMap<Module, Integer> averageStudents = new HashMap<>();
        for (Student student : Student.getStudents()) {
            for (Module module : student.getModulesEnroll()) {
                if (averageStudents.containsKey(module)) {
                    averageStudents.replace(module, averageStudents.get(module) + 1);
                } else {
                    averageStudents.put(module, 1);
                }
            }
        }

        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Entry<Module, Integer> avg : averageStudents.entrySet()) {
            dataSet.setValue(avg.getKey().getModuleCode(), avg.getValue());
        }

        writeChartToPDF(generatePieChart("Number of Students Enrolled Per Module", dataSet), 600, 400, "ReportSummaryStudentsEnrolled.pdf", null);

        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportSummaryStudentsEnrolled.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateAverageAgeOfStudentsReport(boolean open) {
        HashMap<String, Integer> studentAges = new HashMap<>();
        for (Student student : Student.getStudents()) {
            String birthDate = student.getICNumber().substring(0, 6);
            try {
                Integer.parseInt(student.getICNumber().substring(0, 6));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                continue;
            }
            String birthYearDigit = birthDate.substring(0, 2);
            String birthMonthDigit = birthDate.substring(2, 4);
            String birthDayDigit = birthDate.substring(4, 6);
            String currentYear = String.valueOf(LocalDate.now().getYear()).substring(2, 4);
            int year = ((Integer.parseInt(birthYearDigit) >= 00 && Integer.parseInt(birthYearDigit) <= Integer.parseInt(currentYear)) ? Integer.parseInt("20" + birthYearDigit) : Integer.parseInt("19" + birthYearDigit));
            Period period = null;
            try {
                LocalDate date = LocalDate.of(year, Integer.parseInt(birthMonthDigit), Integer.parseInt(birthDayDigit));
                LocalDate dateNow = LocalDate.now();
                period = Period.between(date, dateNow);
            } catch (DateTimeException e) {
                System.out.println("Student: " + student.getUsername() + " IC number format is wrong.");
            } finally {
                if (period != null) {
                    int age = period.getYears();
                    String key = age + " Year Old";
                    if (studentAges.containsKey(key)) {
                        studentAges.replace(key, studentAges.get(key) + 1);
                    } else {
                        studentAges.put(key, 1);
                    }
                }
            }
        }

        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Entry<String, Integer> avg : studentAges.entrySet()) {
            dataSet.setValue(avg.getKey(), avg.getValue());
        }

        writeChartToPDF(generatePieChart("Average Age of Students", dataSet), 600, 400, "ReportAverageAgeOfStudents.pdf", null);
        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportAverageAgeOfStudents.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateModulesTaughtByLecturersReport(boolean open) {
        HashMap<Lecturer, Integer> modulesTaught = new HashMap<>();
        for (Module module : AcademicCourseworkReportSystem.MODULES) {
            if (module.getAssignedLecturer() == null || !(module.getAssignedLecturer() instanceof Lecturer)) {
                continue;
            }
            Lecturer lecturer = (Lecturer) module.getAssignedLecturer();
            if (modulesTaught.containsKey(lecturer)) {
                modulesTaught.replace(lecturer, modulesTaught.get(lecturer) + 1);
            } else {
                modulesTaught.put(lecturer, 1);
            }
        }

        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Entry<Lecturer, Integer> mT : modulesTaught.entrySet()) {
            dataSet.setValue(mT.getKey().getName(), mT.getValue());
        }

        writeChartToPDF(generatePieChart("Number of Modules Taught by Lecturers", dataSet), 600, 400, "ReportModulesTaughtByLecturers.pdf", null);

        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportModulesTaughtByLecturers.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lecturer Reports Generate the summary of the student result for specific
     * module.
     */
    public static void generateSummaryResultReport(boolean open, Module module) {
        if (module == null) {
            throw new NullPointerException("Module cannot be null");
        }
        HashMap<Grade, Integer> studentGrades = new HashMap<>();
        for (Result result : Result.getResultsByModule(module)) {
            if (studentGrades.containsKey(result.getGrade())) {
                studentGrades.replace(result.getGrade(), studentGrades.get(result.getGrade()) + 1);
            } else {
                studentGrades.put(result.getGrade(), 1);
            }
        }

        DefaultPieDataset dataSet = new DefaultPieDataset();
        DefaultPieDataset dataSet2 = new DefaultPieDataset();
        for (Entry<Grade, Integer> result : studentGrades.entrySet()) {
            dataSet.setValue(result.getKey().getName(), result.getValue());
            dataSet2.setValue(result.getKey().getClassification().getName(), result.getValue());
        }

        PdfWriter writer = null;

        Document document = new Document();

        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("ReportSummaryResult.pdf"));
            document.open();
            // Graph 1
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(600, 400);
            Graphics2D graphics2d = template.createGraphics(600, 400,
                    new DefaultFontMapper());
            Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, 600, 400);
            JFreeChart chart = generatePieChart("Summary Student Result for " + module.getModuleName(), dataSet);
            chart.draw(graphics2d, rectangle2d);
            graphics2d.dispose();

            // Graph 2
            PdfContentByte contentByte1 = writer.getDirectContent();
            PdfTemplate template1 = contentByte1.createTemplate(600, 400);
            Graphics2D graphics2d1 = template1.createGraphics(600, 400,
                    new DefaultFontMapper());
            Rectangle2D rectangle2d1 = new Rectangle2D.Double(0, 0, 600, 400);
            JFreeChart chart1 = generatePieChart("Summary Student Classification for " + module.getModuleName(), dataSet2);
            chart1.draw(graphics2d1, rectangle2d1);
            graphics2d1.dispose();

            Image image = Image.getInstance(template);
            Image image1 = Image.getInstance(template1);

            float fitWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();

            image.scaleToFit(fitWidth, 1000);
            image1.scaleToFit(fitWidth, 1000);

            document.add(image);
            document.add(image1);

        } catch (FileNotFoundException e) {
            DialogUtils.showErrorMessageDialog(null, "You need to close the file before the new data will be generated.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();

        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportSummaryResult.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lecturer Reports Generate the number of students the lecturer teach in
     * specific module report.
     */
    public static void generateStudentNumbersReport(boolean open, Lecturer lecturer) {
        if (lecturer == null) {
            throw new NullPointerException("Lecturer cannot be null");
        }
        HashMap<Module, Integer> studentNumbers = new HashMap<>();
        for (Student student : Student.getStudents()) {
            for (Module mod : student.getModulesEnroll()) {
                if (mod.getAssignedLecturer() == lecturer) {
                    if (studentNumbers.containsKey(mod)) {
                        studentNumbers.replace(mod, studentNumbers.get(mod) + 1);
                    } else {
                        studentNumbers.put(mod, 1);
                    }
                }
            }
        }

        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Entry<Module, Integer> studentNumber : studentNumbers.entrySet()) {
            dataSet.setValue(studentNumber.getKey().getModuleCode(), studentNumber.getValue());
        }

        writeChartToPDF(generatePieChart("Total Number of Students for Modules Taught", dataSet), 600, 400, "ReportStudentNumbers.pdf", null);

        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportStudentNumbers.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lecturer Reports Generate the report of different student intakes for
     * specific module.
     */
    public static void generateStudentIntakesReport(boolean open, Module module) {
        if (module == null) {
            throw new NullPointerException("Module cannot be null");
        }
        HashMap<Intake, Integer> intakes = new HashMap<>();
        for (Student student : Student.getStudents()) {
            for (Module mod : student.getModulesEnroll()) {
                if (mod == module) {
                    if (intakes.containsKey(student.getIntake())) {
                        intakes.replace(student.getIntake(), intakes.get(student.getIntake()) + 1);
                    } else {
                        intakes.put(student.getIntake(), 1);
                    }
                }
            }
        }

        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Entry<Intake, Integer> intake : intakes.entrySet()) {
            dataSet.setValue(intake.getKey().getIntakeCode(), intake.getValue());
        }

        writeChartToPDF(generatePieChart("Student Intakes for " + module.getModuleName(), dataSet), 600, 400, "ReportStudentIntakes.pdf", null);

        try {
            if (open) {
                Desktop.getDesktop().open(new File("ReportStudentIntakes.pdf"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pie chart data.
     */
    public static JFreeChart generatePieChart(String title, DefaultPieDataset dataSet) {
        JFreeChart chart = ChartFactory.createPieChart(title, dataSet, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);

        return chart;
    }

    public static void writeChartToPDF(JFreeChart chart, int width, int height, String fileName, List<Paragraph> paragraphs) {
        PdfWriter writer = null;
        Document document = new Document();

        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(width, height);
            Graphics2D graphics2d = template.createGraphics(width, height,
                    new DefaultFontMapper());
            Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
                    height);
            chart.draw(graphics2d, rectangle2d);

            graphics2d.dispose();

            Image image = Image.getInstance(template);
            float fitWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            image.scaleToFit(fitWidth, 1000);
            document.add(image);

            if (paragraphs != null) {
                for (Paragraph para : paragraphs) {
                    document.add(para);
                }
            }
        } catch (FileNotFoundException e) {
            DialogUtils.showErrorMessageDialog(null, "You need to close the file before the new data will be generated.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    /**
     *
     * Following source code referenced from (Christopher, 2011)
     *
     * Generate log report
     *
     * @param user The user for checking logging records.
     * @param logDuration The duration of the records.
     */
    public static void generateLogReport(User user, EnumLogDuration logDuration) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("LogActivity.pdf"));
            document.open();

            Image image1 = Image.getInstance(ReportGenerator.class.getResource("/oodj/resources/logo.png").toString());
            image1.scaleAbsolute(50, 50);
            document.add(image1);

            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 28);
            cb.showTextAligned(Element.ALIGN_LEFT, "Log Activity", 100, 770, 0);
            cb.endText();

            Font font = FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, Color.BLACK);
            Paragraph paraName = new Paragraph("Name: " + user.getName(), font);
            document.add(paraName);

            Paragraph paraUsername = new Paragraph("Username: " + user.getUsername(), font);
            document.add(paraUsername);

            if (user instanceof Student) {
                Paragraph paraTPNumber = new Paragraph("TP Number: " + ((Student) user).getTPNumber(), font);
                document.add(paraTPNumber);
            }

            Paragraph paraDate = new Paragraph("Log Duration: " + logDuration.getDisplayName(), font);
            document.add(paraDate);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(90);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            //Set Column widths
            float[] columnWidths = {0.15f, 1f, 0.3f};
            table.setWidths(columnWidths);

            PdfPCell column1 = new PdfPCell(new Paragraph("No"));
            column1.setHorizontalAlignment(Element.ALIGN_CENTER);
            column1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell column2 = new PdfPCell(new Paragraph("Date Time"));
            column2.setHorizontalAlignment(Element.ALIGN_CENTER);
            column2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell column3 = new PdfPCell(new Paragraph("Status"));
            column3.setHorizontalAlignment(Element.ALIGN_CENTER);
            column3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(column1);
            table.addCell(column2);
            table.addCell(column3);

            int i = 1;
            try {
                File logsFile = new File("logs.txt");
                Scanner s = new Scanner(logsFile);
                while (s.hasNext()) {
                    String logMessage = s.nextLine();
                    if (logMessage.contains("[Login]") || logMessage.contains("[Logout]")) {
                        if (logMessage.contains("(" + user.getUsername() + ")")) {

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            try {
                                java.util.Date msgDate = simpleDateFormat.parse(logMessage.substring(1, logMessage.indexOf("]")));
                                if (msgDate.after(logDuration.getDateStart()) && msgDate.before(logDuration.getDateEnd())) {//TODO

                                    PdfPCell rowNo = new PdfPCell(new Paragraph(String.valueOf(i++)));
                                    rowNo.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    rowNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                    table.addCell(rowNo);

                                    PdfPCell rowDate = new PdfPCell(new Paragraph(logMessage.substring(1, logMessage.indexOf("]"))));
                                    rowDate.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    rowDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                    table.addCell(rowDate);

                                    boolean isLogin = logMessage.contains("[Login]");
                                    boolean attempLogin = logMessage.contains("attempting");
                                    Paragraph status = new Paragraph(isLogin ? (attempLogin ? "Attempt Login" : "Login") : "Logout");
                                    PdfPCell rowStatus = new PdfPCell(status);
                                    rowStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    rowStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                    table.addCell(rowStatus);
                                }
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                s.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            document.add(table);
            document.close();
            writer.close();

            Desktop.getDesktop().open(new File("LogActivity.pdf"));
        } catch (FileNotFoundException e) {
            DialogUtils.showErrorMessageDialog(null, "You need to close the LogActivity.pdf file before the new data will be generated.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
