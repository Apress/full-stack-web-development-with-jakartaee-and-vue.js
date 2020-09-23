
export class User {
    email: string = ''
    password: string = ''

    static emptyUser () {
      let user:User = new User()

      return user
    }

    static newUser (email: string, password: string) {
      let user:User = new User()

      user.email = email
      user.password = password

      return user
    }

    copyUser (userToCopy: User) {
      this.email = userToCopy.email
      this.password = userToCopy.password
    }
}
