import Vue from 'vue'
import { Token } from '../entities/Token'
import axios from 'axios'

export class AuthorizationService {
  static getToken (grantType: string, code: string, redirectUrl: string) {
    const params = new URLSearchParams()
    params.append('grant_type', grantType)
    params.append('code', code)
    params.append('redirect_uri', redirectUrl)

    return Vue.axios.post<Token>(`/openidconnect/token`, params)
  }

  static authorize () {
    window.location.href = `${process.env.VUE_APP_SSO_AUTHORIZATION}?response_type=code&scope=openid%20profile%20email&client_id=${process.env.VUE_APP_SSO_CLIENT_ID}&redirect_uri=${process.env.VUE_APP_SSO_REDIRECT_URL}`
  }
}
