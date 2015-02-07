package app

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.server.FontAwesome
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
* @author Tharindu Abeydeera
*/
@Theme("scop")
class MainUI extends UI {

  @Override
  protected void init(VaadinRequest vaadinRequest) {

    println "Handling the request"

    def mainLayout = new VerticalLayout()
    def bodyLayout = new VerticalLayout()

    mainLayout.setWidth('100%')
    mainLayout.setHeightUndefined() // This activates the vertical scrollbar when needed
    // Set body to 60% of the screen
    bodyLayout.setWidth('60%')
    bodyLayout.setHeight('100%')

    def appTitleLayout = createAppTitle()
    appTitleLayout.addStyleName('titleview')

    def todosPanelLayout = createTodoListPanel()
    todosPanelLayout.addStyleName('appview')

    bodyLayout.addComponent(appTitleLayout)
    bodyLayout.addComponent(todosPanelLayout)

    bodyLayout.setExpandRatio(appTitleLayout, 2.0f)
    bodyLayout.setExpandRatio(todosPanelLayout, 8.0f)

    bodyLayout.setSpacing(true)
    bodyLayout.setMargin(true)

    mainLayout.setSpacing(true)
    mainLayout.setMargin(true)

    // bodyLayout.addStyleName('appview')

    mainLayout.addComponent(bodyLayout)
    mainLayout.setComponentAlignment(bodyLayout, Alignment.TOP_CENTER)
    // Set content for UI
    setContent(mainLayout)
  }

  def createAppTitle(){
    def appTitleLabel = new Label('The Todo App')
    appTitleLabel.addStyleName('h1')
    appTitleLabel.addStyleName('bold')
    appTitleLabel.addStyleName('white')
    appTitleLabel.addStyleName('align-center')

    def appTitleLayout = new HorizontalLayout()
    appTitleLayout.setSizeFull()
    appTitleLayout.addComponent(appTitleLabel)
    appTitleLayout.setComponentAlignment(appTitleLabel, Alignment.BOTTOM_CENTER)
    return appTitleLayout
  }

  def createTodoListPanel(){
    def todosPanelLayout = new CssLayout()
    todosPanelLayout.setWidth('100%')
    todosPanelLayout.addStyleName('card')
    // Create Panel Header
    def panelHeaderLayout = createTodoListPanelHeader()
    // Create Panel Content - Todo List
    def panelContentLayout = createTodoListPanelContent()
    // Create Panel Footer
    def panelFooterLayout = createTodoListPanelFooter(panelContentLayout)

    todosPanelLayout.addComponent(panelHeaderLayout)
    todosPanelLayout.addComponent(panelContentLayout)
    todosPanelLayout.addComponent(panelFooterLayout)
    return todosPanelLayout
  }

  def createTodoListPanelHeader(){
    def panelHeaderLayout = new HorizontalLayout()
    panelHeaderLayout.setWidth('100%')
    panelHeaderLayout.addStyleName('v-panel-caption')
    def panelTitleLabel = new Label('Todo List')
    panelTitleLabel.addStyleName('h2')
    panelTitleLabel.addStyleName('bold')
    panelHeaderLayout.addComponent(panelTitleLabel)
    panelHeaderLayout.setExpandRatio(panelTitleLabel, 1)
    return panelHeaderLayout
  }

  def createTodoListPanelContent(){
    def panelContentLayout = new VerticalLayout()
    panelContentLayout.setSizeFull()
    createTodo(panelContentLayout)
    return panelContentLayout
  }

  def createTodoListPanelFooter(panelContentLayout){
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
      createTodo(panelContentLayout)
      // if(panelContentLayout.getComponent(0))
    })
    panelFooterLayout.addComponent(addNewTodoButton)
    panelFooterLayout.setComponentAlignment(addNewTodoButton, Alignment.MIDDLE_RIGHT)
    return panelFooterLayout
  }

  def createTodo(panelContentLayout){
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
    todoTextField.setInputPrompt('What needs to be done ?...')
    textFieldLayout.addComponent(todoTextField)
    textFieldLayout.setComponentAlignment(todoTextField, Alignment.MIDDLE_RIGHT)

    todoCheckBox.addValueChangeListener({event ->
      if (todoTextField.value) {
        if(todoCheckBox.value) {
          Notification.show("${todoTextField.value} is done!", Notification.Type.HUMANIZED_MESSAGE)
          todoTextField.setEnabled(false)
        } else {
          Notification.show("${todoTextField.value} is not done yet!", Notification.Type.HUMANIZED_MESSAGE)
          todoTextField.setEnabled(true)
        }
      } else {
        Notification.show("Please add a task first!", Notification.Type.WARNING_MESSAGE)
        todoCheckBox.value = false
      }
    })

    def todoEditButtonLayout = createTodoEditButton(todoTextField)
    def todoDeleteButtonLayout = createTodoDeleteButton(panelContentLayout, todoRowLayout)

    todoRowLayout.addComponent(checkBoxLayout)
    todoRowLayout.addComponent(textFieldLayout)
    todoRowLayout.addComponent(todoEditButtonLayout)
    todoRowLayout.addComponent(todoDeleteButtonLayout)
    todoRowLayout.addStyleName('add-todo-animation')
    todoRowLayout.addStyleName('todo-row')

    todoRowLayout.setExpandRatio(checkBoxLayout, 0.5f)
    todoRowLayout.setExpandRatio(textFieldLayout, 8.5f)
    todoRowLayout.setExpandRatio(todoEditButtonLayout, 0.5f)
    todoRowLayout.setExpandRatio(todoDeleteButtonLayout, 0.5f)
    panelContentLayout.addComponentAsFirst(todoRowLayout)
  }

  def createTodoDeleteButton(panelContentLayout, todoRowLayout){
    def todoDeleteButton = new Button()
    todoDeleteButton.setIcon(FontAwesome.TIMES_CIRCLE)
    todoDeleteButton.addStyleName('borderless-colored')
    todoDeleteButton.addStyleName('small')
    todoDeleteButton.addStyleName('icon-only')
    todoDeleteButton.addStyleName('hidden')
    todoDeleteButton.addClickListener({event ->
      todoRowLayout.removeStyleName('add-todo-animation')
      todoRowLayout.addStyleName('remove-todo-animation')
      panelContentLayout.removeComponent(todoRowLayout)
      Notification.show("Todo removed!")
      if (panelContentLayout.getComponentCount().equals(0)){
        createTodo(panelContentLayout)
      }
    })

    def todoDeleteButtonLayout = new HorizontalLayout()
    todoDeleteButtonLayout.addComponent(todoDeleteButton)
    return todoDeleteButtonLayout
  }

  def createTodoEditButton(todoTextField){
    def todoEditButton = new Button()
    todoEditButton.setIcon(FontAwesome.PENCIL)
    todoEditButton.addStyleName('borderless-colored')
    todoEditButton.addStyleName('small')
    todoEditButton.addStyleName('icon-only')
    todoEditButton.addStyleName('hidden')

    todoEditButton.addClickListener({event ->
      todoTextField.setEnabled(true)
    })

    def todoEditButtonLayout = new HorizontalLayout()
    todoEditButtonLayout.addComponent(todoEditButton)
    return todoEditButtonLayout
  }
}
