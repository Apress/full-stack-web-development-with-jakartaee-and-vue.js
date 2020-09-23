<template>
  <div>Authorization</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Token } from '../../entities/Token'
import { AuthorizationService } from '../../services/AuthorizationService'
import { User } from '../../entities/User'

@Component
export default class AuthorizationCallback extends Vue {
  mounted () {
    let code:string = this.$route.query.code as string

    AuthorizationService.getToken('authorization_code', code, process.env.VUE_APP_SSO_REDIRECT_URL)
      .then(response => {
        let token:Token = response.data

        this.$store.commit('setToken', token)

        Vue.axios.defaults.headers.common['Authorization'] = `Bearer ${token.accessToken}`

        this.$toasted.info(`Welcome ${token.userName}`)

        this.$router.push({ name: 'food_service_view', params: { id: token.userId } })
      })
  }
}
</script>
