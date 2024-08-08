package com.Dpnetworks.dp.service;

import com.Dpnetworks.dp.model.Collaborator;
import com.Dpnetworks.dp.model.Expense;
import com.Dpnetworks.dp.model.Submission;
import com.Dpnetworks.dp.repo.ExpenseRepository;
import com.Dpnetworks.dp.repo.SubmissionRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ExcelService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SubmissionRepo submissionRepository;

    @Autowired
    private CollabService collabService;

    @Autowired
    private SubmissionService submissionService;

    private static final Logger LOGGER = Logger.getLogger(ExcelService.class.getName());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

    public String saveFileData(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new IOException("The file is empty");
            }
            LOGGER.info("Received file: " + file.getOriginalFilename());
            LOGGER.info("File size: " + file.getSize());
            LOGGER.info("File content type: " + file.getContentType());

            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<Expense> expenses = new ArrayList<>();
            double total = 0.0;

            String name = getCellValue(sheet.getRow(5).getCell(2));
            String firstName = getCellValue(sheet.getRow(6).getCell(2));
            String project = getCellValue(sheet.getRow(7).getCell(2));
            String monthYear = getCellValue(sheet.getRow(8).getCell(2));
            Date submitDate = dateFormat.parse(monthYear);
            System.out.println(project);
            System.out.println(monthYear);

//            if (!isCurrentMonth(submitDate)) {
//                LOGGER.warning("Submission date " + monthYear + " is not compatible with the current month.");
//                return "The submission date month is not compatible with the current month.";
//            }

            for (int i = 0; i < 10; i++) {
                sheet.removeRow(sheet.getRow(i));
            }

            for (int i = 11; i <= 32; i++) {
                Row row = sheet.getRow(i);
                if (row != null && !isRowEmpty(row)) {
                    String dateStr = getCellValue(row.getCell(0));
                    if (isMonthYearCompatible(dateStr, monthYear)) {
                        Expense expense = new Expense();
                        Date expenseDate = dateFormat.parse(dateStr);
                        expense.setMonth(expenseDate);
                        expense.setCategory(getCellValue(row.getCell(1)));
                        expense.setDescription(getCellValue(row.getCell(2)));
                        expense.setAmount(getCellNumericValue(row.getCell(3)));
                        expense.setChantier(project);
                        expense.setJustification(getCellValue(row.getCell(4)));

                        total += expense.getAmount();
                        expenses.add(expense);
                    } else {
                        return "Date " + dateStr + " does not match the specified month and year: " + monthYear;
                    }
                } else if (row == null || isRowEmpty(row)) {
                    break;
                }
            }

            String fullName = name + " " + firstName;
            Optional<Collaborator> existingCollaboratorOpt = Optional.ofNullable(collabService.findCollaboratorsByName(fullName));
            Collaborator collaborator;
            if (existingCollaboratorOpt.isPresent()) {
                collaborator = existingCollaboratorOpt.get();
            } else {
                collaborator = new Collaborator();
                collaborator.setFullName(fullName);
                collaborator = collabService.addCollaborator(collaborator);
            }

            Optional<Submission> existingSubmissionOpt = submissionRepository.findByCollaboratorAndSubmitDate(collaborator, submitDate);
            if (existingSubmissionOpt.isPresent()) {
                LOGGER.info("Submission already exists for collaborator: " + fullName + " for month: " + monthYear);
                return "Submission already exists for this month.";
            }

            Submission submission = new Submission();
            submission.setSubmitted(true);
            submission.setSubmitDate(submitDate);
            submission.setTotal(total);
            submission.setCollaborator(collaborator);
            submissionService.addSubmission(submission);

            for (Expense expense : expenses) {
                expense.setCollaborator(collaborator);
                expenseRepository.save(expense);
            }
            workbook.close();
            return "Upload complete!";
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred while processing the file", e);
            return "An error occurred while processing the file: " + e.getMessage();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred while processing the file", e);
            return "An error occurred while processing the file: " + e.getMessage();
        }
    }

    private boolean isMonthYearCompatible(String dateStr, String monthYear) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;

            String[] parts = monthYear.split("-");
            int monthYearYear = Integer.parseInt(parts[0]);
            int monthYearMonth = Integer.parseInt(parts[1]);

            return year == monthYearYear && month == monthYearMonth;
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Date parsing error", e);
            return false;
        }
    }

    private boolean isRowEmpty(Row row) {
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    private boolean isCurrentMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);

        cal.setTime(date);
        int submitYear = cal.get(Calendar.YEAR);
        int submitMonth = cal.get(Calendar.MONTH);

        return currentYear == submitYear && currentMonth == submitMonth;
    }

    private double getCellNumericValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        return cell.getCellType() == CellType.NUMERIC ? cell.getNumericCellValue() : 0.0;
    }
}
