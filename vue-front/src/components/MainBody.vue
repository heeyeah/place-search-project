rows<template>
  <div>
    <b-container>
      <b-row>
        <b-col cols="8">
          <div class="mg-top">
            <b-form @submit="onSubmit">
              <b-input-group class="search-wrap">
              <b-form-input
              type="text"
              v-model="form.keyword"
              placeholder="🏠Enter your keyword."></b-form-input>
              <b-input-group-append>
                <b-button type="submit" variant="outline-secondary">SEARCH</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form>
          </div>

          <!-- table -->
          <div>
            <div class="page-count-box">
              <b-form-select v-model="bFormSelected" @change="changePageCount" :options="countOption" size="10" />
            </div>
            <div>
              <b-table
                selectable
                select-mode="single"
                selectedVariant="secondary"
                :small=true head-variant="dark" hover
                :items="documents"
                :fields="fields"
                :show-empty=true
                @row-selected="rowSelected"
                empty-text="There are no records to show."
                empty-filtered-text="There are no records to show.">
              </b-table>
            </div>
            <b-pagination align="center" size="sm"
              v-model="currentPage"
              :total-rows="rows"
              :per-page="perPage"
              @change="changecurrentPage"
              >
            </b-pagination>
          </div>
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
  import SearchHistory from './SearchHistory.vue'
  import SearchTop10 from './SearchTop10.vue'

  import swal from 'sweetalert2'

  const pageCountSelect = [
    {value: 5, text: '5'},
    {value: 10, text: '10'},
    {value: 15, text: '15'}
  ]

  export default {
    name: 'MainBody',
    components: {
      SearchHistory, SearchTop10
    },
    data() {
      return {
        form: {
          userId: '',
          keyword: ''
        },
        apiUrl: 'http://localhost:9000',
        fields: [
          {key:'id', label: 'ID'},
          {key:'place_name', label: '장소명'},
          {key:'road_address_name', label: '도로명'},
          {key:'category_name', label : '카테고리'}
        ],

        documents: [],
        bFormSelected: 10,
        rows: 1,
        currentPage: 1,
        totalPage: 1,
        perPage: 10,
        countOption: pageCountSelect
      }
    },

    mounted () {
      this.searchPlaceByKeyword(true)
    },

    computed: {
      axiosParams() {
        const params = new URLSearchParams();
          params.append('keyword', this.form.keyword);
          params.append('page', this.currentPage);
          params.append('size', this.perPage);
          return params;
      }
    },

    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        this.searchPlaceByKeyword();
      },

      searchPlaceByKeyword: function(init) {
        var that= this;

        if(init) return;

        this.$axios.get(this.apiUrl + '/search', {params: this.axiosParams})
          .then(function (response) {
            that.documents = response.data.documents;
            that.rows = response.data.pageableCount;
            that.totalPage = response.data.totalPage;
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
      },

      rowSelected: function(item) {

      },

      changecurrentPage: function (page) {
        this.currentPage = page
        this.searchPlaceByKeyword()
      },
      changePageCount: function (count) {
        this.perPage = count
        this.searchPlaceByKeyword()
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

div .page-count-box {
  width: 80px !important;
}

</style>
