<template>
  <div id="app" class="container" style="margin-top: 30px;">
    <to-do/>
    <to-do-list/>
  </div>
</template>

<script>
import ToDo from './components/ToDo'
import ToDoList from './components/ToDoList.vue'

export default {
  name: 'App',
  components: {
    ToDo,ToDoList
  }
}

</script>

<style>

</style>
