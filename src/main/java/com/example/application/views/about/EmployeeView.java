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
