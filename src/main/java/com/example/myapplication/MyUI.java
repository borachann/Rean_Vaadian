package com.example.myapplication;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	private CustomerService service = CustomerService.getInstance();
	private Grid grid = new Grid();
	private TextField txtSearch = new TextField();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	// basic component 
       /* Label lblShow = new Label("Click me to alert value in the field : ");
        TextField txtShow = new TextField();
        Button btnShow = new Button("Show");
        btnShow.addClickListener(e -> Notification.show(txtShow.getValue()));*/
    	
    	Label lblSearch = new Label("Search");
    	txtSearch.setInputPrompt("Name...");
    	Button clearFilterBtn = new Button(FontAwesome.TIMES); //new Button("Clear");
    	clearFilterBtn.addClickListener(e ->{
    		txtSearch.clear();
    		updateList();
    	});
    	
    	txtSearch.addTextChangeListener(e -> {
    		grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, service.findAll(e.getText())));
    	});
    	
    	grid.setColumns("id", "firstName","lastName", "email");
        VerticalLayout layout = new VerticalLayout();
        //HorizontalLayout horizLayout = new HorizontalLayout();
        //horizLayout.addComponents(txtSearch,clearFilterBtn);
        
    	CssLayout horizLayout = new CssLayout();
    	horizLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
    	horizLayout.addComponents(txtSearch,clearFilterBtn);
    	
        layout.addComponents(horizLayout, grid);
        
        updateList();
        
        
        layout.setMargin(true);
        layout.setSpacing(true);
      //  layout.addComponents(lblShow, txtShow, btnShow);
        setContent(layout);
    }

    private void updateList() {
		// TODO Auto-generated method stub
    	List<Customer> findAll = service.findAll();
    	grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, findAll));
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
