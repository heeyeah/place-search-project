<template>
  <div class="mg-top">
    <b-button v-on:click="getSearchHistory"
      variant="warning" size="sm" >HISTORY</b-button>
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
        userId: 'USER00',
        apiUrl: 'http://localhost:9000',
        tplHistory: []
      }
    },

    mounted() {
      this.getSearchHistory();
    },

    computed: {
      axiosParams() {
        const params = new URLSearchParams();
          params.append('userId', this.userId);
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
</style>
