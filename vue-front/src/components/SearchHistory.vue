<template>
  <div class="mg-top">
    <!-- <b-button v-on:click="getSearchHistory" variant="warning" size="sm" >HISTORY</b-button> -->
    <h5 class="title">✏️검색 히스토리 <span class="detail">(최근 15)</span></h5>
    <div v-for="(el, idx) in tplHistory" v-if="idx <= 15" class="history-wrap">
      {{ el.keyword }}
      <span class="font-sm"> ({{ el.searchDttm }}) </span>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'SearchHistory',
    data() {
      return {
        apiUrl: 'http://localhost:9000',
        tplHistory: [],
        polling: null
      }
    },

    mounted() {
      this.getSearchHistory();
    },

    created() {
      this.polling = setInterval(() => {
        this.getSearchHistory();
      }, 5000) //5초
    },

    beforeDestroy() {
      clearInterval(this.polling)
    },

    computed: {
      axiosParams() {
        const params = new URLSearchParams();
          params.append('userId', this.$session.get('userId'));
          return params;
      }
    },

    methods: {
      getSearchHistory: function() {
        var that= this;

        this.$axios.get(this.apiUrl + '/history', {params: this.axiosParams})
          .then(function (response) {
            that.tplHistory = response.data;
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
      }
    }
  }
</script>

<style scoped>

.history-wrap {
  text-align: left;
}

span.font-sm {
  font-size: small;
}

h5.title {
  text-align: left;
  font-weight: bold;
}

h5 > span.detail {
  font-size: 0.8rem;
  font-weight: normal;
}
</style>
