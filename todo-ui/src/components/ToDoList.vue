<template>
  <div class="row todo-list">
    <div class="col-12">
      <table class="table table-borderless table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">ToDo</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>

              <tr v-for="(todo, index) in todos" :key="index">
                  <td>{{ index + 1}}</td>
                  <td>{{ todo.description }}</td>
                  <td>
                    <button class="btn btn-success" @click="doneToDo(todo.id, $event)">Done</button>
                  </td>
              </tr>

        </tbody>
      </table>
    </div>
  </div>
</template>

<script>

import { EventBus } from "../utils/event-bus";
import TodoService from "../utils/todo.service"

export default {
  name: "ToDoList",
  data: function() {
    return {
      todos: []
    }
  },
  mounted() {
    EventBus.$on("new-todo", todo => { this.newToDo(todo)});
  },
  created() {
    this.getToDos();
  },
  methods: {
    getToDos: function() {
        TodoService.getAll()
          .then(response => this.todos = response.data);
    },

    newToDo: function(todo){
      TodoService.create(todo)
          .then(response => this.todos.push(response.data));
    },

    doneToDo: function(id, event){
       TodoService.delete(id)
          .then(response => console.log(response.data));
      event.preventDefault();
      this.todos = this.remove(id);
    },

    remove: function(value){
      return this.todos.filter(function(ele){
            return ele.id != value;
        });
    }
  }
}
</script>

<style>

.todo-list {
  margin-top: 30px;
}

</style>
