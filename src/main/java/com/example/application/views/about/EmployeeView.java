//package com.example.application.views.about;
//
//import com.example.application.entity.Employee;
//import com.example.application.service.EmployeeService;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//
//import java.util.List;
//
//@Route("employees")
//public class EmployeeView extends VerticalLayout {
//
//    public EmployeeView(EmployeeService employeeService) {
//        FormLayout formLayout = new FormLayout();
//        TextField nameField = new TextField("Name");
//        TextField roleField = new TextField("Role");
//        Button addButton = new Button("Add Employee");
//        formLayout.add(nameField, roleField, addButton);
//
//        Grid<Employee> grid = new Grid<>(Employee.class);
//        grid.setColumns("name", "role");
//
//        List<Employee> employees = employeeService.findAll();
//        grid.setItems(employees);
//
//        addButton.addClickListener(e -> {
//            Employee employee = new Employee();
//            employee.setName(nameField.getValue());
//            employee.setRole(roleField.getValue());
//            employeeService.save(employee);
//            employees.add(employee);
//            grid.setItems(employees);
//            nameField.clear();
//            roleField.clear();
//        });
//
//        add(formLayout, grid);
//    }
//}

//
//private void exportToExcel(List<Employee> employees) {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Employees");
//        Row headerRow = sheet.createRow(0);
//        Cell nameHeaderCell = headerRow.createCell(0);
//        nameHeaderCell.setCellValue("Name");
//        Cell roleHeaderCell = headerRow.createCell(1);
//        roleHeaderCell.setCellValue("Role");
//
//        for (int i = 0; i < employees.size(); i++) {
//        Employee employee = employees.get(i);
//        Row row = sheet.createRow(i + 1);
//        Cell nameCell = row.createCell(0);
//        nameCell.setCellValue(employee.getName());
//        Cell roleCell = row.createCell(1);
//        roleCell.setCellValue(employee.getRole());
//        }
//
//        try (FileOutputStream fileOut = new FileOutputStream("employees.xlsx")) {
//        workbook.write(fileOut);
//        } catch (IOException e) {
//        e.printStackTrace();
//        }
//        }
//
//        Button exportButton = new Button("Export to Excel");
//        exportButton.addClickListener(e -> {
//        List<Employee> employees = employeeService.findAll();
//        exportToExcel(employees);
//        });
//        formLayout.add(exportButton);