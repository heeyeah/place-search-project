<template>
  <div class="mg-top">
    <h5 class="title">✨인기 키워드
<!-- <b-spinner class="spinner-wrap" label="Spinning" variant="warning"></b-spinner> -->
    </h5>
    <!-- <b-button v-on:click="getStatisticsGroupByKeyword"
      variant="warning" size="sm" >TOP10</b-button> -->
    <div v-for="(el, idx) in tpl10" class="top-wrap">
      <span class="bold">{{idx + 1}}위</span>
      {{ el.keyword }} <span class="font-sm">({{ el.count }})</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'SearchTop10',
    data() {
      return {
        apiUrl: 'http://localhost:9000',
        tpl10: [],
        polling: null
      }
    },

    created() {
      this.polling = setInterval(() => {
        this.getStatisticsGroupByKeyword();
      }, 5000) //5초
    },

    beforeDestroy() {
      clearInterval(this.polling)
    },

    mounted() {
      this.getStatisticsGroupByKeyword();
    },

    methods: {
      getStatisticsGroupByKeyword: function() {
        var that= this;

        this.$axios.get(this.apiUrl + '/statistics')
          .then(function (response) {
            var top10 = response.data;

            that.tpl10 = top10;


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

.top-wrap {
  text-align: left;
}

span.bold {
  font-weight:bold;
}

span.font-sm {
  font-size: small;
}

h5.title {
  text-align: left;
  font-weight: bold;
}

/* .spinner-wrap {
  width: 1.7rem;
  height: 1.7rem;
} */
</style>
