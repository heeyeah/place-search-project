<template>
  <div>
    <nav class="navbar navbar-expand-sm navbar-custom">
      <a href="/search" class="navbar-brand mb-0 h1">{{ title }}</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCustom">
      </button>
      <div class="navbar-collapse collapse" id="navbarCustom">
        <span class="ml-auto navbar-text">{{ msg }}</span>
      </div>
    </nav>
  </div>
</template>

<script>
  export default {
    name: 'CommonHeader',
    props: {
      title: String,
      msg: String
    },
    computed: {
      data() {
        return {
          now: new Date()
        }
      }
    }
  }
</script>

<style scoped>

.navbar-custom {
    background-color: #ffe400;
}

.navbar-custom .navbar-brand,
.navbar-custom .navbar-text {
    color: black;
}

</style>
