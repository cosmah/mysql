package com.example.application.views.about;

import com.example.application.entity.Employee;
import com.example.application.service.EmployeeService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

//        addButton.addClickListener(e -> {
//            Employee employee = employeeService.save(nameField.getValue(), roleField.getValue());
//            employees = employeeService.findAll();
//            grid.setItems(employees);
//            nameField.clear();
//            roleField.clear();
//        });

        add(formLayout, grid);


    }
}
