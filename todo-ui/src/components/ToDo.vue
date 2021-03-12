<template>
  <div class="row">
    <div class="col-12">
      <div class="card">
      <div class="card-body">
        <h5 class="card-title">ToDo</h5>
        <form @submit="checkForm">
            <div class="form-group">
                <input type="text" class="form-control" @keyup.enter.prevent="checkForm" v-model="todo">
                <small class="form-text text-muted">Add just a simple ToDo</small>
            </div>
            <button class="btn btn-primary" type="submit">Add</button>
        </form>
      </div>
  </div>
</div>

  </div>
</template>

<script>

import { EventBus } from "../utils/event-bus.js";

export default {
  name: 'ToDo',
  data () {
    return {
      todo: ''
    }
  },
  methods: {
    checkForm: function(e){
      EventBus.$emit("new-todo", { description: this.todo });
    }
  }
}
</script>


<style>

</style>
