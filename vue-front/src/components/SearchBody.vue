<template>
  <div>
  <div class="mg-top">
      <b-form @submit="onSubmit">
        <b-input-group
        class="search-wrap">
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
        <b-form-select v-model="bFormSelected" @change="changePageCount" :options="countOption" size="5" />
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
    <div class="detail-wrap">
      <div><span class="bold">장소상세</span></div>
      <ul>
        <li v-for="(value, key) in placeDetail">

         <template v-if="key === '지도 바로가기'">
           {{key}}: <b-link :href="value">{{value}}</b-link>
         </template>
         <template v-else-if="key === '장소명'">
           {{key}}: <b-link :href="placeDetailExc">{{value}}</b-link>
         </template>
         <template v-else>
           {{key}}: {{value}}
         </template>

        </li>
      </ul>
    </div>
  </div>
</template>
<script>
  import swal from 'sweetalert2'

  const pageCountSelect = [
    {value: 5, text: '5'},
    {value: 10, text: '10'},
    {value: 15, text: '15'}
  ]

  export default {
    name: 'SearchBody',
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
        placeDetail: null,
        placeDetailExc: null,
        bFormSelected: 5,
        rows: 1,
        currentPage: 1,
        totalPage: 1,
        perPage: 5,
        countOption: pageCountSelect
      }
    },

    mounted () {
      this.searchPlaceByKeyword(true)
    },

    computed: {
      axiosParams() {
        const params = new URLSearchParams();
          params.append('userId', this.$session.get('userId'));
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

        if(init) {
          if(!this.$session.get('userId')) {
            swal.fire({
              type: 'info',
              text: 'Session Invalid',
              showConfirmButton: false,
              timer: 1000
            }).then(()=>{
              that.$router.push('/login');
            })
          }
          return;
        }

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
        var data = item[0], detail = {};

        detail['장소명'] = data.place_name;
        detail['카테고리'] = data.category_name;
        detail['도로명'] = data.road_address_name + ' (' + data.address_name + ')' ;
        detail['전화번호'] = data.phone;
        detail['지도 바로가기'] = data.redirect_url;

        this.placeDetailExc = data.place_url;
        this.placeDetail = detail;
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

.detail-wrap {
  margin-top: 50px;
  margin-bottom: 50px;
  text-align: left;
}

div .page-count-box {
  width: 80px !important;
}

span.bold {
  font-weight:bold;
}

</style>
