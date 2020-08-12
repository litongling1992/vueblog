<!--
 * @Author: your name
 * @Date: 2020-08-03 18:18:45
 * @LastEditTime: 2020-08-07 16:32:23
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\DataManager\EditDialog.vue
--> 
<template>
  <el-dialog title="课程编辑" :visible.sync="dialogVisible" :show-close="close">
    <el-form ref="ruleForm" :model="form" label-width="100px" size="small" class="form-box">
      <el-form-item label="课程名称" prop="title">
        <el-input v-model="form.title" placeholder="请输入课程名称"></el-input>
      </el-form-item>
      <el-form-item label="课程等级" prop="level">
        <el-select v-model="form.level" placeholder="请选择课程等级">
          <el-option label="初级" value="初级"></el-option>
          <el-option label="中级" value="中级"></el-option>
          <el-option label="高级" value="高级"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="报名人数" prop="count">
        <el-input v-model="form.count" placeholder="请输入报名人数"></el-input>
      </el-form-item>
      <el-form-item label="上线日期" prop="date">
        <el-date-picker type="date" v-model="form.date" value-format="yyyy-MM-dd"></el-date-picker>
      </el-form-item>
      <el-form-item label="技术栈" prop="type">
        <el-radio-group v-model="form.type">
          <el-radio label="Vue" name="type"></el-radio>
          <el-radio label="React" name="type"></el-radio>
          <el-radio label="Node" name="type"></el-radio>
          <el-radio label="小程序" name="type"></el-radio>
          <el-radio label="angular" name="type"></el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dilog-footer">
        <el-button size="small">取消</el-button>
        <el-button type="primary" size="small">确定</el-button>
    </span>
  </el-dialog>
</template>

<script lang="ts">
import { Component, Vue, Prop, Provide } from "vue-property-decorator";

@Component
export default class EditDialog extends Vue {
  //加感叹号 强制解析
  @Prop(Boolean) dialogVisible!: boolean;

  @Prop(Object) form!: {
    title: string;
    type: string;
    level: string;
    count: string;
    date: string;
    _id: string;
  };
}
</script>


<style scope>
</style>