import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import User from './views/User.vue'
import UserList from './components/UserList.vue'
import UserNew from './components/UserNew.vue'
import UserUpdate from './components/UserUpdate.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/user',
      component: User,
      children: [
        {
          path: '/',
          name: 'user_list',
          component: UserList
        },
        {
          path: 'new',
          name: 'user_new',
          component: UserNew
        },
        {
          path: ':email',
          name: 'user_update',
          props: (route) => { return { email: route.params.email } },
          component: UserUpdate
        }
      ]
    },
    {
      path: '/about',
      name: 'about',
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }
  ]
})
