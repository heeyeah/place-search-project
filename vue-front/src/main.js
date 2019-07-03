import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueSession from 'vue-session'

import BootstrapVue from 'bootstrap-vue'
import 'expose-loader?$!expose-loader?jQuery!jquery'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// import LoadScript from 'vue-plugin-load-script'

import axios from 'axios'

Vue.use(BootstrapVue)
Vue.use(VueSession)
// Vue.use(LoadScript)

// Vue.loadScript("https://maps.googleapis.com/maps/api/js?key=5aeb3e1bbcf9a04c491766a95b47fb43")
//     .then(() => {
//       // Script is loaded, do something
//     })
//     .catch(() => {
//       // Failed to fetch script
//     });

Vue.config.productionTip = false
Vue.prototype.$http=axios // ?

Object.defineProperty(Vue.prototype, '$axios', {value: axios})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
