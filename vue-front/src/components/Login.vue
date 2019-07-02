<template>
  <div>
    <MainHeader title="Place Search Login Page" msg=""></MainHeader>

    <b-form @submit="onLogin" class="form-wrap">
      <b-form-group
        label-cols="4"
        label="USERNAME"
        label-align="left"
        label-for="user-id-input">
        <b-form-input id="user-id-input"
                      type="text"
                      v-model="form.userId"
                      required
                      placeholder="Enter your ID">
        </b-form-input>
      </b-form-group>
      <b-form-group
        label-cols-sm="4"
        label="PASSWORD"
        label-align-sm="left"
        label-for="password-input">
        <b-form-input id="password-input"
                      type="password"
                      v-model="form.userPw"
                      placeholder="Password">
        </b-form-input>
      </b-form-group>
      <b-button type="submit" variant="dark" size="sm">LOGIN</b-button>
    </b-form>
  </div>
</template>

<script>
import MainHeader from './MainHeader.vue'
import swal from 'sweetalert2'

export default {
  name: 'Login',
  components: {
    MainHeader
  },
  data() {
    return {
      form: {
        userId : '',
        userPw : ''
      },
      apiUrl: 'http://localhost:9000/login'
    }
  },

  computed: {
    axiosParams() {
      const params = new URLSearchParams();
        params.append('userId', this.form.userId);
        params.append('userPassword', this.form.userPw);
        return params;
    }
  },

  methods: {
    onLogin(evt) {
      evt.preventDefault()
      this.loginService();
    },

    loginService: function() {
      var that = this;

      this.$axios.get(this.apiUrl, {params: this.axiosParams})
      .then(function (response) {
        if(response.status === 200) {
          if(response.data) {
            //route!
            that.$session.start();
            that.$session.set('userId', that.form.userId);
            that.$router.push('/search');
          } else {
            swal.fire({
              type: 'error',
              title: 'Incorrect password',
              text: 'Check your password!',
              showConfirmButton: false,
              timer: 1500
            })
          }
        } else {
          swal.fire({
            type: 'error',
            title: 'Invalid User ID',
            text: 'Check your User ID!',
            showConfirmButton: false,
            timer: 1500
          })
        }
      })
      .catch(function (error) {
        if(error.response) {
          swal.fire({
            type: 'error',
            text: error.response.data.message,
            showConfirmButton: false,
            timer: 1000
          })
        }
      })
      .finally(function () {
        that.form.userId = ''
        that.form.userPw = ''
      })

    }
  }
}
</script>

<style scoped>
h1 {
  padding : 10px;
  color: black;
  background: #ffe400;
}

.form-wrap {
  margin-top: 100px;
  margin-left: 250px;
  margin-right: 250px;
}


</style>
