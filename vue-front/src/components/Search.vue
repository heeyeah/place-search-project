<template>
  <div>
    <MainHeader msg="Place Search🗺"></MainHeader>
    <MainBody></MainBody>
  </div>
</template>

<script>

import MainHeader from './MainHeader.vue'
import MainBody from './MainBody.vue'

export default {
  name: 'Search',
  components: {
    MainHeader,
    MainBody
  }
}
</script>
