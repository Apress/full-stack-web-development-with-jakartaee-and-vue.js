import { Role } from '../entities/Role'

export class User {
    name: string = ''
    email: string = ''
    password: string = ''
    role: Role = Role.USER

    static emptyUser () {
      let user:User = new User()

      return user
    }

    static newUser (name: string, email: string, password: string, role: Role) {
      let user:User = new User()

      user.name = name
      user.email = email
      user.password = password
      user.role = role

      return user
    }

    copyUser (userToCopy: User) {
      this.name = userToCopy.name
      this.email = userToCopy.email
      this.password = userToCopy.password
      this.role = userToCopy.role
    }
}
