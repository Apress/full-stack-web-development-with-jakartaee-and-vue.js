<template>
  <div>
    <div class="row">
      <div class="col-sm">
       <router-link to="/user/new" class="btn btn-primary">Create New User</router-link>
      </div>
    </div>
    <div class="row mt-2">
      <div class="col-sm">
        <table class="table  table-dark">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Password</th>
              <th>Role</th>
              <th>Action</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" v-bind:key="user.email">
              <td>{{user.name}}</td>
              <td>{{user.email}}</td>
              <td>{{user.password}}</td>
              <td>{{user.role}}</td>
              <td><router-link  :to="{ name: 'user_update', params: { email: user.email }}">Update</router-link></td>
              <td><a v-on:click="remove(user)" href="#">Delete</a></td>
            </tr>
          </tbody>
        </table>
    </div>
    </div>
  </div>
</template>

<script lang="ts">
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { Component, Vue } from 'vue-property-decorator'
import { User } from '../entities/User'

@Component
export default class UserList extends Vue {
  users:User[] = []

  // lifecycle hook
  mounted () {
    this.getUsers()
  }

  getUsers () {
    this.users = this.$store.state.users
  }

  remove (userToRemove:User) {
    this.$store.commit('removeUser', userToRemove)
  }
}
</script>
