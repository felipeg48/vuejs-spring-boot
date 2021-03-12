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

import axios from "axios"
import { EventBus } from "../utils/event-bus.js";


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
        axios.get(process.env.ROOT_API)
          .then(response => this.todos = response.data);
    },

    newToDo: function(todo){
      axios.post(process.env.ROOT_API, todo)
          .then(response => this.todos.push(response.data));
    },

    doneToDo: function(id, event){
       axios.delete(process.env.ROOT_API + "/" + id)
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
