import Vue from 'vue'
import Vuex from 'vuex'
import { User } from './entities/User'
import { Role } from './entities/Role'
import { State } from './entities/State'

Vue.use(Vuex)

const stateBase: State = {
  users: [
    User.newUser('Daniel Pelaez', 'danielpelaez@email.com', 'password123', Role.ADMIN),
    User.newUser('Hector Hurtado', 'hectorhurtado@email.com', 'password123', Role.USER),
    User.newUser('William Agreda', 'williamagreda@email.com', 'password123', Role.USER),
    User.newUser('Roger Salazar', 'rogersalazar@email.com', 'password123', Role.USER)
  ]
}

export default new Vuex.Store<State>({
  state: stateBase,
  mutations: {
    saveUser (state:State, user:User) {
      state.users.push(user)
    },
    removeUser (state:State, userToRemove:User) {
      const index = state.users.findIndex(user => user.email === userToRemove.email)

      Vue.delete(state.users, index)
    },
    updateUser (state:State, userToUpdate:User) {
      let user:User | undefined = state.users.find(user => user.email === userToUpdate.email)

      user!.copyUser(userToUpdate)
    }
  },
  actions: {

  },
  getters: {
    getUserByEmail: (state) => (email:string) => {
      return state.users.find(user => user.email === email)
    }
  }
})
