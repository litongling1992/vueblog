<template>
  <div id="app">
     <router-view/>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { Getter, Action , State , Mutation } from 'vuex-class';


@Component({
  components:{

  }
})
export default class App extends Vue{

   //存储user
  @Action("setUser") setUser:any;

  created () {
    this.setUser(localStorage.tsToken);
  }

}
</script>

<style lang="scss">
body {
  width: 100%;
  height: 900px;
}
#app {
  width: 100%;
  height: 100%;
}
</style>
