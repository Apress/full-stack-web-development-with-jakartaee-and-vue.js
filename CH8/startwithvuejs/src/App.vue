<template>
  <div id="app">
    <div class="d-flex justify-content-between">
      <div class="nav">
        <router-link to="/">Home |</router-link>
        <router-link to="/delivery">&nbsp;Delivery |</router-link>
        <router-link to="/about">&nbsp;About</router-link>
      </div>
      <div class="nav" >
        <template v-if="!isLoggedIn"><router-link to="/login">Login</router-link></template>
        <template v-if="isLoggedIn">Welcome {{loggedIn.email}} |&nbsp;<router-link :to="{ name: 'food_service_view', params: { email: loggedIn.email }}">Settings</router-link></template>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class App extends Vue {
  get isLoggedIn () {
    return this.$store.getters.getCurrentFoodServiceLoggedIn().email !== ''
  }
  get loggedIn () {
    return this.$store.getters.getCurrentFoodServiceLoggedIn()
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
.nav {
  padding: 30px;
  padding-left: 30px !important;
}

.nav a {
  font-weight: bold;
  color: #2c3e50;
}

.nav a.router-link-exact-active {
  color: #42b983;
}
</style>
