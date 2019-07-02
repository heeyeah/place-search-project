import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Search from '../components/Search'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    { path: '/login', component: Login },
    { path: '/search', component: Search},
    { path: '/', redirect: '/login'}
  ]
})

export default router
