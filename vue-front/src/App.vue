<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>


<script>
/* eslint-disable */
export default {
  name: 'app',
  mounted() {
    router.push("/login");
    router.push("/search");
    router.push("/");
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
