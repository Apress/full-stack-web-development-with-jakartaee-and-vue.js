<template>
  <div>
    <UserForm v-on:userFilled="update($event)" :user="user" :type="type"></UserForm>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import UserForm from '@/components/UserForm.vue'
import { User } from '../entities/User'

@Component({
  components: {
    UserForm
  }
})
export default class UserUpdate extends Vue {
  @Prop() private readonly email!: string

  private user:User = User.emptyUser()
  private type:string = 'update'

  mounted () {
    this.getUser(this.email)
  }

  getUser (email:string) {
    let userToUpdate:User = this.$store.getters.getUserByEmail(email)

    this.user.copyUser(userToUpdate)
  }

  update (user:User) {
    this.$store.commit('updateUser', user)

    this.$router.push({ name: 'user_list' })
  }
}
</script>
