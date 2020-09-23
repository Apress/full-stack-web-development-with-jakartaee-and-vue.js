
export class Token {
    userId: string = ''
    userName: string = ''
    userEmail: string = ''
    accessToken: string = ''
    refreshToken: string = ''
    expiresOn: string = ''

    static emptyToken () {
      let delivery:Token = new Token()

      return delivery
    }

    static newToken (userId: string, userName: string, userEmail: string, accessToken: string, refreshToken: string, expiresOn: string) {
      let token:Token = new Token()

      token.userId = userId
      token.userName = userName
      token.userEmail = userEmail
      token.accessToken = accessToken
      token.refreshToken = refreshToken
      token.expiresOn = expiresOn

      return token
    }

    copyToken (tokenToCopy: Token) {
      this.userId = tokenToCopy.userId
      this.userName = tokenToCopy.userName
      this.userEmail = tokenToCopy.userEmail
      this.accessToken = tokenToCopy.accessToken
      this.refreshToken = tokenToCopy.refreshToken
      this.expiresOn = tokenToCopy.expiresOn
    }
}
