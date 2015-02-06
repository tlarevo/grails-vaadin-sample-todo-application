package app

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest
import com.vaadin.grails.Grails

import com.vaadin.ui.UI

import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.CssLayout

import com.vaadin.ui.Alignment
import com.vaadin.ui.Window
import com.vaadin.ui.Panel
import com.vaadin.ui.Label
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.CheckBox
import com.vaadin.ui.TextField

/**
*
*
* @author
*/
@Theme("scop")
class MainUI extends UI {

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    // setTheme('mytheme')

    println "Handling the request"

    /*  def window = new Window();*/
    def mainLayout = new VerticalLayout()
    def bodyLayout = new VerticalLayout()

    mainLayout.setSizeFull()
    // Set body to 60% of the screen
    bodyLayout.setWidth('60%')
    bodyLayout.setHeight('100%')
    /*  window.setMainWindow(window)*/

    // String homeLabel = Grails.i18n("default.home.label")
    // def homeLabel = Grails.i18n("default.home.label")

    // Label label = new Label(homeLabel)
    // def label = new Label(homeLabel)
    // body.addComponent(label)

    // example of how to get Grails service and call a method
    // List<User> users = Grails.get(UserService).getListOfUsers()
    //    for (User user : users) {
    //      layout.addComponent(new Label(user.name))
    // }
    // Button myButton = new Button("Click me");
    // ----- Java Implementation of ClickListener ----- //
    /*myButton.addClickListener(new Button.ClickListener() {
    public void buttonClick(ClickEvent event) {
    layout.addComponent(new Label("Thank you for clicking"));
    Notification.show("button clicked!", "This is the description", Notification.Type.TRAY_NOTIFICATION);
  }
});*/

    // ----- Groovy Implementation of ClickListener ----- //

    // ----- Method 1 ----- //
    /*myButton.addClickListener({event ->
    layout.addComponent(new Label("Thank you"))
    Notification.show("button clicked!", "This is some description", Notification.Type.TRAY_NOTIFICATION)
    } as Button.ClickListener)*/

    // ----- Method 2 ----- //
    // myButton.addClickListener({event ->
    //   def notification = new Label("Thank you")
    //   body.addComponent(notification)
    //   body.setComponentAlignment(notification, Alignment.MIDDLE_CENTER)
    //   Notification.show("button clicked!", "This is some description", Notification.Type.TRAY_NOTIFICATION)
    // })
    // Add components to layouts
    // body.addComponent(myButton)
    // mainLayout.addComponent(body)

    // Set Alignments of the components, components must be added to layouts before setting the alignments
    // mainLayout.setComponentAlignment(bodyLayout, Alignment.MIDDLE_CENTER)
    // bodyLayout.setComponentAlignment(myButton, Alignment.MIDDLE_CENTER)
    // def todoRowLayout = new HorizontalLayout()
    // def checkBoxLayout = new HorizontalLayout()
    // def textFieldLayout = new HorizontalLayout()
    //
    // todoRowLayout.setSizeFull()
    // checkBoxLayout.setSizeFull()
    // textFieldLayout.setSizeFull()
    //
    // def todoCheckBox = new CheckBox()
    // def todoTextField = new TextField()
    //
    // checkBoxLayout.addComponent(todoCheckBox)
    // checkBoxLayout.setComponentAlignment(todoCheckBox, Alignment.MIDDLE_RIGHT)
    //
    //
    // todoTextField.setWidth('100%')
    // todoTextField.addStyleName('borderless')
    // todoTextField.setInputPrompt('What is to be done ?...')
    // textFieldLayout.addComponent(todoTextField)
    // textFieldLayout.setComponentAlignment(todoTextField, Alignment.MIDDLE_RIGHT)
    //
    // todoRowLayout.addComponent(checkBoxLayout)
    // todoRowLayout.addComponent(textFieldLayout)
    //
    // todoCheckBox.addValueChangeListener({event ->
    //   Notification.show("${todoTextField.value} is done!")
    // })
    //
    // todoRowLayout.setExpandRatio(checkBoxLayout, 0.5f)
    // todoRowLayout.setExpandRatio(textFieldLayout, 9.5f)

    def todoListLayout = new VerticalLayout()
    todoListLayout.setSizeFull()
    // todoListLayout.addComponent(todoRowLayout)
    addTodo(todoListLayout)

    // def todosPanel = new Panel('Todos')
    def todosPanel = new CssLayout()
    todosPanel.setWidth('100%')
    todosPanel.addStyleName('card')

    def panelCaptionLayout = new HorizontalLayout()
    panelCaptionLayout.setWidth('100%')
    panelCaptionLayout.addStyleName('v-panel-caption')
    def panelTitleLabel = new Label('Todo List')
    panelTitleLabel.addStyleName('h2')
    panelTitleLabel.addStyleName('bold')
    panelCaptionLayout.addComponent(panelTitleLabel)
    panelCaptionLayout.setExpandRatio(panelTitleLabel, 1)

    def panelFooterLayout = new HorizontalLayout()
    panelFooterLayout.addStyleName('v-panel-footer')
    panelFooterLayout.setWidth('100%')
    panelFooterLayout.setHeight('50%')
    panelFooterLayout.addStyleName('v-panel-caption')

    def addNewTodoButton = new Button('Add New')
    addNewTodoButton.addStyleName('primary')
    addNewTodoButton.addStyleName('small')
    addNewTodoButton.addStyleName('v-panel-footer-button')
    addNewTodoButton.addClickListener({event ->
      addTodo(todoListLayout)
    })


    panelFooterLayout.addComponent(addNewTodoButton)
    panelFooterLayout.setComponentAlignment(addNewTodoButton, Alignment.MIDDLE_RIGHT)

    def todoListTitleLabel = new Label('Sample Todo List Application')
    todoListTitleLabel.addStyleName('h1')
    todoListTitleLabel.addStyleName('v-centered')

    todosPanel.addComponent(todoListTitleLabel)
    todosPanel.addComponent(panelCaptionLayout)
    todosPanel.addComponent(todoListLayout)
    todosPanel.addComponent(panelFooterLayout)

    // def todoAppTitleLayout = new VerticalLayout()
    // todoAppTitleLayout.setHeight('20%')
    // todoAppTitleLayout.setWidth('100%')
    //

    //
    // todoAppTitleLayout.addComponent(todoListTitleLabel)
    // todoAppTitleLayout.setComponentAlignment(todoListTitleLabel, Alignment.MIDDLE_CENTER)
    //
    // bodyLayout.addComponent(todoAppTitleLayout)
    // bodyLayout.setComponentAlignment(todoAppTitleLayout, Alignment.TOP_CENTER)
    // bodyLayout.addComponent(todoListTitleLabel)
    bodyLayout.addComponent(todosPanel)
    bodyLayout.setComponentAlignment(todosPanel, Alignment.MIDDLE_CENTER)
    mainLayout.addComponent(bodyLayout)
    mainLayout.setComponentAlignment(bodyLayout, Alignment.TOP_CENTER)
    // Set content for UI
    setContent(mainLayout)

    // def todoRowLayout = new HorizontalLayout()
    // todoRowLayout.setSpacing(true)
    //
    // todoRowLayout.setHeight('10%')
    // todoRowLayout.setWidth('100%')
    //
    // def todoCheckBox = new CheckBox()
    // def todoTextField = new TextField()
    //
    // todoCheckBox.setHeight('100%')
    // todoCheckBox.setWidth('5%')
    //
    // todoTextField.addStyleName('borderless')
    // todoTextField.setInputPrompt('What is to be done ?...')
    //
    // todoTextField.setHeight('100%')
    // todoTextField.setWidth('95%')
    //
    // todoRowLayout.addComponent(todoCheckBox)
    // todoRowLayout.addComponent(todoTextField)
    //
    // def todosPanel = new Panel('Todos')
    // todosPanel.setContent(todoRowLayout)
    // todoRowLayout.setExpandRatio(todoCheckBox, 0.5f)
    // todoRowLayout.setExpandRatio(todoTextField, 9.5f)

    // bodyLayout.addComponent(todosPanel)
    // bodyLayout.setComponentAlignment(todosPanel, Alignment.TOP_CENTER)
    // mainLayout.addComponent(bodyLayout)
    // mainLayout.setComponentAlignment(bodyLayout, Alignment.TOP_CENTER)
    // Set content for UI
    // setContent(mainLayout)
  }

  def addTodo(todoListLayout){
    def todoRowLayout = new HorizontalLayout()
    def checkBoxLayout = new HorizontalLayout()
    def textFieldLayout = new HorizontalLayout()

    todoRowLayout.setSizeFull()
    checkBoxLayout.setSizeFull()
    textFieldLayout.setSizeFull()

    def todoCheckBox = new CheckBox()
    def todoTextField = new TextField()

    checkBoxLayout.addComponent(todoCheckBox)
    checkBoxLayout.setComponentAlignment(todoCheckBox, Alignment.MIDDLE_RIGHT)


    todoTextField.setWidth('100%')
    todoTextField.addStyleName('borderless')
    todoTextField.setInputPrompt('What is to be done ?...')
    textFieldLayout.addComponent(todoTextField)
    textFieldLayout.setComponentAlignment(todoTextField, Alignment.MIDDLE_RIGHT)

    todoRowLayout.addComponent(checkBoxLayout)
    todoRowLayout.addComponent(textFieldLayout)

    todoCheckBox.addValueChangeListener({event ->
      Notification.show("${todoTextField.value} is done!")
      todoTextField.setReadOnly(true)
    })

    todoRowLayout.setExpandRatio(checkBoxLayout, 0.5f)
    todoRowLayout.setExpandRatio(textFieldLayout, 9.5f)
    todoListLayout.addComponent(todoRowLayout)
  }
}
