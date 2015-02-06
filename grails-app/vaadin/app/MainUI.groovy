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

    println "Handling the request"

    /*  def window = new Window();*/
    def mainLayout = new VerticalLayout()
    def bodyLayout = new VerticalLayout()

    mainLayout.setSizeFull()
    // Set body to 60% of the screen
    bodyLayout.setWidth('60%')
    bodyLayout.setHeight('100%')

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
    panelFooterLayout.setSpacing(true)
    panelFooterLayout.setMargin(true)

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
    todoListTitleLabel.addStyleName('align-center')

    def todoListTitleLayout = new HorizontalLayout()
    todoListTitleLayout.setSizeFull()
    todoListTitleLayout.addComponent(todoListTitleLabel)
    todoListTitleLayout.setComponentAlignment(todoListTitleLabel, Alignment.BOTTOM_CENTER)

    // todosPanel.addComponent(todoListTitleLabel)
    todosPanel.addComponent(panelCaptionLayout)
    todosPanel.addComponent(todoListLayout)
    todosPanel.addComponent(panelFooterLayout)

    bodyLayout.addComponent(todoListTitleLayout)
    bodyLayout.addComponent(todosPanel)
    bodyLayout.setExpandRatio(todoListTitleLayout, 2.0f)
    bodyLayout.setExpandRatio(todosPanel, 8.0f)

    bodyLayout.setSpacing(true)
    bodyLayout.setMargin(true)

    mainLayout.setSpacing(true)
    mainLayout.setMargin(true)

    mainLayout.addComponent(bodyLayout)
    mainLayout.setComponentAlignment(bodyLayout, Alignment.TOP_CENTER)
    // Set content for UI
    setContent(mainLayout)

  }

  def addTodo(todoListLayout){
    def todoRowLayout = new HorizontalLayout()
    def checkBoxLayout = new HorizontalLayout()
    def textFieldLayout = new HorizontalLayout()

    todoRowLayout.setSizeFull()

    checkBoxLayout.setSizeFull()
    // checkBoxLayout.setSpacing(true)
    // checkBoxLayout.setMargin(true)

    textFieldLayout.setSizeFull()
    // textFieldLayout.setSpacing(true)
    // textFieldLayout.setMargin(true)

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
