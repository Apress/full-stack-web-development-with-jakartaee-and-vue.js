<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      In this demo you will find a User CRUD
    </p>
    <h3>Requirements</h3>
    <ul>
      <li>We need to create a new user.</li>
      <li>A user can have a username, password, role.</li>
      <li>We need to see the whole list of users</li>
      <li>We need to update an existing user.</li>
      <li>We need to remove an existing user.</li>
      <li>The users should persist only for the time the server is up.</li>
    </ul>
    <p>
      To start, click here:
    </p>
    <router-link to="/user" class="btn btn-primary">User CRUD</router-link>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class HelloWorld extends Vue {
  @Prop() private msg!: string;
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
