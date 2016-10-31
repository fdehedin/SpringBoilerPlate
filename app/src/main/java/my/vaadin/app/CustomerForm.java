package my.vaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends FormLayout {

	private CustomerService service = CustomerService.getInstance();
	private Customer customer;
	private MyUI myUI;
	private static final String FIRSTNAME = "firstName";
	@PropertyId(FIRSTNAME)
	private TextField firstName = new TextField("First name");
	@PropertyId("lastName")
	private TextField lastName = new TextField("Last name");
	@PropertyId("email")
	private TextField email = new TextField("Email");
	@PropertyId("status")
	private NativeSelect status = new NativeSelect("Status");
	@PropertyId("birthDate")
	private PopupDateField birthdate = new PopupDateField("Birthday");
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");

	public CustomerForm(MyUI myUI) {
		this.myUI = myUI;

		status.addItems(CustomerStatus.values());

		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		save.addClickListener(e -> this.save());

		delete.addClickListener(e -> this.delete());

		setSizeUndefined();

		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		buttons.setSpacing(true);
		addComponents(firstName, lastName, email, status, birthdate, buttons);

	}

	private void delete() {
		service.delete(customer);
		myUI.updateList();
		setVisible(false);
	}

	private void save() {
		service.save(customer);
		myUI.updateList();
		setVisible(false);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		BeanFieldGroup.bindFieldsUnbuffered(customer, this);

		delete.setVisible(customer.isPersisted());
		setVisible(true);
		firstName.selectAll();
	}

}
