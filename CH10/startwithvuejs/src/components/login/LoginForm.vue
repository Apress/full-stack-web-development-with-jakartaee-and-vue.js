<template>
<div>
  Redirecting
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { AuthorizationService } from '../../services/AuthorizationService'
import { User } from '../../entities/User'

@Component
export default class LoginForm extends Vue {
  mounted () {
    AuthorizationService.authorize()
  }
}
</script>
