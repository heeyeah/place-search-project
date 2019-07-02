import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueSession from 'vue-session'

import BootstrapVue from 'bootstrap-vue'
import 'expose-loader?$!expose-loader?jQuery!jquery'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import axios from 'axios'

Vue.use(BootstrapVue)
Vue.use(VueSession)


Vue.config.productionTip = false
Vue.prototype.$http=axios // ?

Object.defineProperty(Vue.prototype, '$axios', {value: axios})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
