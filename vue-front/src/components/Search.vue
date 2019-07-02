<template>
  <div>

    <MainHeader title="Place Search World🔎" :msg="data.now"></MainHeader>
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
  },
  props: {
      userId : String
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
