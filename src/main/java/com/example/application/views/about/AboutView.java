package com.example.application.views.about;

import com.example.application.entity.Employee;
import com.example.application.service.EmployeeService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    private final EmployeeService employeeService;

    @Autowired
    public AboutView(EmployeeService employeeService) {
        this.employeeService = employeeService;
        setSpacing(false);

        FormLayout formLayout = new FormLayout();
        TextField nameField = new TextField("Name");
        TextField roleField = new TextField("Role");
        Button addButton = new Button("Add Employee");
        formLayout.add(nameField, roleField, addButton);

        Grid<Employee> grid = new Grid<>(Employee.class);
        grid.setColumns("name", "role");

        List<Employee> employees = employeeService.findAll();
        grid.setItems(employees);

        addButton.addClickListener(e -> {
            Employee employee = new Employee();
            employee.setName(nameField.getValue());
            employee.setRole(roleField.getValue());
            employeeService.save(employee.getName(), employee.getRole()); // pass the name and role to the save method
            employees.add(employee);
            grid.setItems(employees);
            nameField.clear();
            roleField.clear();
        });

        //download excel
        Button exportButton = new Button("Export to Excel");
        StreamResource excelStreamResource = new StreamResource("employees.xlsx", () -> {
            return exportToExcel(employeeService.findAll());
        });
        Anchor downloadLink = new Anchor(excelStreamResource, "");
        downloadLink.getElement().setAttribute("download", true);
        downloadLink.add(exportButton);
        formLayout.add(downloadLink);


        add(formLayout, grid);
    }

    private ByteArrayInputStream exportToExcel(List<Employee> employees) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");
        Row headerRow = sheet.createRow(0);
        Cell nameHeaderCell = headerRow.createCell(0);
        nameHeaderCell.setCellValue("Name");
        Cell roleHeaderCell = headerRow.createCell(1);
        roleHeaderCell.setCellValue("Role");

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            Row row = sheet.createRow(i + 1);
            Cell nameCell = row.createCell(0);
            nameCell.setCellValue(employee.getName());
            Cell roleCell = row.createCell(1);
            roleCell.setCellValue(employee.getRole());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
