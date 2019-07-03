<template>
  <div>
    <CommonHeader title="Place Search World🔎" :msg="data.info"></CommonHeader>

    <b-container>
      <b-row>
        <b-col cols="8">
          <SearchBody></SearchBody>
        </b-col>
        <b-col cols="4">
          <div>
            <SearchTop10></SearchTop10>
          </div>
          <div>
            <SearchHistory></SearchHistory>
          </div>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>

import CommonHeader from './CommonHeader.vue'
import SearchHistory from './SearchHistory.vue'
import SearchTop10 from './SearchTop10.vue'
import SearchBody from './SearchBody.vue'

export default {
  name: 'Search',
  components: {
    CommonHeader,
    SearchHistory, SearchTop10, SearchBody
  },

  computed: {
    data() {
      return {
        info: '🗓' + new Date().toLocaleString() + '　　👨🏻‍💻👩🏻‍💻' + this.$session.get('userId')
      }
    }
  }
}
</script>
<style scoped>

  .mg-top {
    margin-top: 50px;
  }

  .search-wrap {
    margin-bottom: 30px;
  }

  .detail-wrap {
    margin-top: 50px;
    margin-bottom: 50px;
    text-align: left;
  }

  span.bold {
    font-weight:bold;
  }

</style>
