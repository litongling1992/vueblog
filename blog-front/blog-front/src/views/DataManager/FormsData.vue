<template>
    <div>
       <el-form class="form-box" :rules="rules" ref="ruleForm" :model="form" label-width="100px">
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
       <el-form-item>
        <el-button @click="submitForm('ruleForm')" type="primary">创建课程</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
       </el-form>
    </div>
</template>

<script lang="ts">
import { Component, Vue ,Provide} from 'vue-property-decorator';

@Component
export default class FormsData extends Vue{
    @Provide() form:Object ={
        title: "",
        type: "",
        level: "",
        count: "",
        date: "",
    };
    @Provide() rules: any = {
    title: [{ required: true, message: "请输入课程名称", trigger: "blur" }],
    level: [{ required: true, message: "请选择课程等级", trigger: "change" }],
    count: [{ required: true, message: "请输入报名人数", trigger: "blur" }],
    date: [
      {
        type: "string",
        required: true,
        message: "请选择日期",
        trigger: "change"
      }
    ],
    type: [
      {
        required: true,
        message: "请选择技术栈",
        trigger: "change"
      }
    ]
  };

  submitForm(formName: string) {
    (this as any).$refs[formName].validate((valid: boolean) => {
      if (valid) {
        (this as any).$axios
          .post(`api/profiles/add`, this.form)
          .then((res: any) => {
              console.log(res.data);
            this.$message({
              message: res.data.msg,
              type: "success"
            });
            this.resetForm(formName);
          });
      }
    });
  }

  resetForm(formName: string) {
    (this as any).$refs[formName].resetFields();
  }

}
</script>


<style lang="scss" scoped>
.form-box {
  .el-input,
  .el-select {
    width: 200px !important;
  }
}
</style>