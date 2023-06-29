package BU.backend.service;

import BU.backend.entity.School;
import BU.backend.entity.Student;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import java.util.List;


//
// Class: StudentForm extends FormLayout
//
// Description:
// This class represents, A form component for creating or editing Student objects.
//
public class StudentForm extends FormLayout {
    private Student student;
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    EmailField email = new EmailField("Email");
    ComboBox<Student.AcademicStatus> status = new ComboBox<>("Status");
    ComboBox<School> school = new ComboBox<>("School");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Binder<Student> binder = new BeanValidationBinder<>(Student.class);

//
// Constructor: StudentForm
//
// Description:
// Constructs a new ContactForm with the given list of companies,
// all a list of companies to display in the form's company ComboBox
//
    public StudentForm(List<School> all) {
        addClassName("contact-form");
        binder.bindInstanceFields(this);
        school.setItems(all);
        school.setItemLabelGenerator(School::getName);
        status.setItems(Student.AcademicStatus.values());
        add(firstName, lastName, email, school, status, createButtonsLayout());
    }


//////////////////////////////////////////////////////////////////
/// setStudent (student)                                       ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Sets the student to be edited                     ///
//           or created in the form                            ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public void setStudent(Student student) {
        this.student = student;
        binder.readBean(student);
    }

//
// Class: StudentFormEvent extends ComponentEvent
//
// Description:
// This class represents, base class for events that are fired by the StudentForm.
//
    public static abstract class StudentFormEvent extends ComponentEvent<StudentForm> {
        private Student student;

        protected StudentFormEvent(StudentForm source, Student student) {
            super(source, false);
            this.student = student;
        }

        public Student getStudent() {
            return student;
        }
    }


//
// Class: SaveEvent extends StudentFormEvent
//
// Description:
// This class represents An event fired when a Student is saved
//
    public static class SaveEvent extends StudentFormEvent {
        SaveEvent(StudentForm source, Student student) {
            super(source, student);
        }
    }


//
// Class: DeleteEvent extends ContactFormEvent
//
// Description:
// This class represents An event fired when a Student is deleted
//
    public static class DeleteEvent extends StudentFormEvent {
        DeleteEvent(StudentForm source, Student student) { super(source, student); }
    }

//
// Class: CloseEvent extends StudentFormEvent
//
// Description:
// This class represents An event fired when the StudentForm is closed
//
    public static class CloseEvent extends StudentFormEvent {
        CloseEvent(StudentForm source) { super(source, null);  }
    }



//////////////////////////////////////////////////////////////////
/// addListener (eventType, listener, <T>)                     ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: a registration for the listener                   ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus()
                .addListener(eventType, listener);
    }


//////////////////////////////////////////////////////////////////
/// createButtonsLayout ()                                     ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: a layout containing the form's buttons            ///
//                                                             ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    private HorizontalLayout createButtonsLayout() {
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, student)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(save, delete, close);
    }

//////////////////////////////////////////////////////////////////
/// validateAndSave ()                                         ///
/// Input : None                                               ///
/// Output: None                                               ///
/// Returns: Validates the form and fires                      ///
//           a SaveEvent if the validation succeeds            ///
///                                                            ///
//////////////////////////////////////////////////////////////////
    private void validateAndSave() {
        try {
            binder.writeBean(student);
            fireEvent(new SaveEvent(this, student));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}