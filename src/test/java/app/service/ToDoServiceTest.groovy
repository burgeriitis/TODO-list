package app.service

import app.model.ToDo
import app.repository.ToDoRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class ToDoServiceTest extends Specification {


   ToDoService toDoService = new ToDoService()

    def toDoRepository = Mock(ToDoRepository)

    def setup() {
        toDoService.toDoRepository = toDoRepository
        toDoService.deleteAllToDos()
    }

    def "AddToDo"() {
        setup:
       ToDo todo = new ToDo()
        todo.setTask("Do nothing")
        todo.setId(0)
        todo.setProgress("None")
        todo.setAssignee("Roberts")

        when:
        toDoService.addToDo(todo)

        then:
        1 * toDoRepository.save(todo)
    }

    def "DeleteToDo"() {
    }

    def "GetAllTodos"() {
        ToDo todo = new ToDo()
        todo.setTask("Do nothing")
        todo.setId(0)
        todo.setProgress("None")
        todo.setAssignee("Roberts")

        def mockToDos =[]
        mockToDos << todo
        toDoRepository.findAll() >> mockToDos


        when:
        def toDoList = toDoService.getAllTodos()

        then:
        toDoList.size() == 1
        toDoList.first() == todo
    }

    def "UpdateToDo"() {
    }
}
