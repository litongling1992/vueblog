<!--
 * @Author: your name
 * @Date: 2020-07-29 15:07:45
 * @LastEditTime: 2020-08-29 16:11:57
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\DataManager\TablesData.vue
--> 
<template>
    <div class="table-data">
      <div class="search-box">
        <el-input size="small" v-model="searVal" placeholder="请输入搜索内容"></el-input>
        <el-button @click="handleSearch" :loading="isSearch" size="small" type="primary" icon="el-icon-search">
          搜索
        </el-button>
      </div>
      <el-table :data="tableData" border style="width:100%" :height="tHeight" class="table-box">
        <el-table-column type="index" label="序号" width="60">
        </el-table-column>
        <el-table-column  label="课程名称" prop="title">
        </el-table-column>
        <el-table-column label="课程等级" width="80" prop="level">
        </el-table-column>
        <el-table-column prop="type" label="技术栈" width="120">
        </el-table-column>
        <el-table-column prop="count" label="报名人数" width="100">
        </el-table-column>
        <el-table-column prop="date" label="上线日期" width="160">
        </el-table-column>
        <el-table-column v-if="getUser.key != 'visitor'" label="操作" width="160">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.$index,scope.row)" size="mini">编辑</el-button>
            <el-button @click="handleDelete(scope.$index,scope.row)" size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pages" ref="page-box">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[5,10,20]" :page-size="size" layout="total,sizes,prev,pager,next,jumper" :total="total">
        </el-pagination>
      </div>
      <EditDialog :dialogVisible="dialogVisible" @closeDialog="closeDialog" :form="formData"></EditDialog>
    </div>
</template>

<script lang="ts">
import { Component, Vue , Provide} from 'vue-property-decorator';
import EditDialog from './EditDialog.vue';
import { Getter, Action , State , Mutation } from 'vuex-class';
@Component({
  components:{
    EditDialog
  }
})
export default class TablesData extends Vue{

  @Getter("user") getUser:any;

   @Provide() isSearch:Boolean =false;
   @Provide() searVal:string='';
   @Provide() tHeight:number=document.body.offsetHeight - 220;
   @Provide() tableData:any = [] //表格数据
   @Provide() page:number =1;
   @Provide() size:number =5;
   @Provide() total:number =0;


   //对话编辑框
   @Provide() dialogVisible:Boolean =false;
   @Provide() formData:object ={
     title: "",
     type: "",
     level: "",
     count: "",
     date: ""
   }

   created () {
     this.loadData();
   }

   handleEdit(index:number,row:any):void{
    // console.log(index,row);
    this.formData =row;
    this.dialogVisible =true;
   }

   handleDelete(index:number,row:any){
     (this as any).$axios.delete(`/api/profiles/delete/${row._id}`)
     .then((res:any) => {
              this.$message({
          message: res.data.msg,
          type: "success"
        });

        this.tableData.splice(index, 1);
     })
     .catch((err:any) => {

     })
   }

     closeDialog(){
     this.dialogVisible = false;
   }

   loadData() {
     (this as any).$axios(`/api/profiles/loadMore/${this.page}/${this.size}`).then((res:any)=>{
        console.log(res.data);
        this.tableData = res.data.data.list;
        this.total = res.data.data.total;
     })
     .catch((err:string)=>{
      console.log(err);
     })
   }

   handleSizeChange(val:number):void{
     this.size = val;
     console.log("this.size:"+this.size)
     this.page =1 ;
     this.searVal ? this.loadSearchData() : this.loadData();
   }

   handleCurrentChange(val:number):void{
     this.page = val;
     console.log("this.page:"+this.page)
     this.searVal ? this.loadSearchData() : this.loadData();
   }

   handleSearch():void{
     this.page = 1;
     this.searVal ? this.loadSearchData() : this.loadData();
   }

   loadSearchData():void{
     this.isSearch = true;
    (this as any).$axios(`/api/profiles/search/${this.searVal}/${this.page}/${this.size}`)
     .then((res:any)=>{
        this.isSearch = false;
        console.log(res.data);
        this.tableData = res.data.datas.list;
        this.total = res.data.datas.total;
     })
     .catch((err:string)=>{
        this.isSearch = false;
       console.log(err);
     })
   }

 


}
</script>


<style lang="scss" scoped>
.table-data {
  height: 100%;
  .table-box {
    font-size: 14px;
  }
  .pages {
    background: #fff;
    margin-top: 10px;
    padding: 10px 10px;
    text-align: right;
    height: 55px;
    box-sizing: border-box;
  }
  .search-box {
    background: #fff;
    margin-bottom: 10px;
    padding: 10px 10px;
    border-radius: 4px;
    height: 55px;
    box-sizing: border-box;
    .el-input {
      width: 200px;
      margin-right: 10px;
    }
  }
}
</style>