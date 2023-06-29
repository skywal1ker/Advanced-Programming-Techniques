package BU.frontend;

import BU.backend.service.SchoolService;
import BU.backend.service.StudentForm;
import BU.backend.service.StudentService;
import BU.backend.entity.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

//
// Class: MainView
//
// Description:
// This class represents the main view of the application.
// It displays a list of contacts and provides functionality for adding, editing, and deleting contacts.
//
@Route("")
public class BrowserView extends VerticalLayout {
    private StudentService studentService;
    private Grid<Student> studentCell = new Grid<>(Student.class);
    private TextField textFilter = new TextField();
    private StudentForm form;

///////////////////////////////////////////////////////////////////
/// BrowserView (studentService, schoolService)                ////
/// Input : data to be inserted                                 ///
/// Output: None                                                ///
/// Returns This represents view from main page                 ///
///                                                             ///
///////////////////////////////////////////////////////////////////
    public BrowserView(StudentService studentService, SchoolService schoolService) {
        this.studentService = studentService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configFilt();
        form = new StudentForm(schoolService.findAll());
        form.addListener(StudentForm.SaveEvent.class, this::saveStudent);
        form.addListener(StudentForm.DeleteEvent.class, this::deleteStudent);
        form.addListener(StudentForm.CloseEvent.class, e -> this.closeEditor());
        closeEditor();
        Div content = new Div(studentCell, form);
        content.addClassName("content");
        content.setSizeFull();
        add(getToolbar(), content);
        updateStudentList();
    }

///////////////////////////////////////////////////////////////////
/// configFilt ()                                              ////
/// Input : No input                                            ///
/// Output: None                                                ///
/// Returns: text field for filtering contacts by name          ///
///                                                             ///
///////////////////////////////////////////////////////////////////
    private void configFilt() {
        textFilter.setPlaceholder("Filter by name...");
        textFilter.setClearButtonVisible(true);
        textFilter.setValueChangeMode(ValueChangeMode.LAZY);
        textFilter.addValueChangeListener(e -> updateStudentList());
    }


///////////////////////////////////////////////////////////////////
/// saveStudent (contact form)                                  ////
/// Input : No input                                            ///
/// Output: None                                                ///
/// Returns: updated form                                       ///
///                                                             ///
///////////////////////////////////////////////////////////////////
    private void saveStudent(StudentForm.SaveEvent event) {
        studentService.save(event.getStudent());
        updateStudentList();
        closeEditor();
    }

//////////////////////////////////////////////////////////////////
/// deleteStudent (contact form)                               ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: Handles the delete event of the contact form      ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    private void deleteStudent(StudentForm.DeleteEvent event) {
        studentService.delete(event.getStudent());
        updateStudentList();
        closeEditor();
    }


//////////////////////////////////////////////////////////////////
/// configureGrid ()                                           ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: Configures the grid for displaying contacts       ///
///           and allows editing of selected contact           ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    private void configureGrid() {
        studentCell.asSingleSelect().addValueChangeListener(event -> editStudent(event.getValue()));
    }

//////////////////////////////////////////////////////////////////
/// editStudent ()                                           ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: Opens the contact form for editing                ///
///           the specified contact                            ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void editStudent(Student student) {
        if (student == null) {
            closeEditor();
        } else {
            form.setStudent(student);
            form.setVisible(true);
            addClassName("editing");
        }
    }


//////////////////////////////////////////////////////////////////
/// closeEditor ()                                             ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: Closes the contact form and clears                ///
///          the selected contact                              ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    private void closeEditor() {
        form.setStudent(null);
        form.setVisible(false);
        removeClassName("editing");
    }

//////////////////////////////////////////////////////////////////
/// closeEditor ()                                             ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: Creates the toolbar for the main view,            ///
///          including the filter text field and               ///
//           add contact button                                ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    private HorizontalLayout getToolbar() {
        textFilter.setPlaceholder("Filter by name...");
        textFilter.setClearButtonVisible(true);
        textFilter.setValueChangeMode(ValueChangeMode.LAZY);
        textFilter.addValueChangeListener(e -> updateStudentList());
        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addStudent());
        HorizontalLayout toolbar = new HorizontalLayout(textFilter, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

//////////////////////////////////////////////////////////////////
/// addStudent ()                                              ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: Clears the selected contact and opens             ///
///          the contact form for adding a new student         ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    void addStudent() {
        studentCell.asSingleSelect().clear();
        editStudent(new Student());
    }

//////////////////////////////////////////////////////////////////
/// updateStudentList ()                                       ///
/// Input : No input                                           ///
/// Output: None                                               ///
/// Returns: after adding it show updated list                 ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    private void updateStudentList() {
        studentCell.setItems(studentService.findAll(textFilter.getValue()));
    }


}