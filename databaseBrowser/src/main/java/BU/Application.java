package BU;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//---------------------------------------------------------------------------
//
// Database Browser using Java Spring Boot
//
// Author: Iztore Kargabayev
// Date: 05/03/23
// Class: MET CS622
// Issues: None known
//
// Description:
// The entry point of the Spring Boot application.
//
// Assumptions:
// The application will start run the local server.
//
@SpringBootApplication
@Theme(value = "mytodo")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

