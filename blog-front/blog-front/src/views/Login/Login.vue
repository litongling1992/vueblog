<template>
  <div class="login">
    <LoginHeader>
      <el-form
        slot="container"
        ref="loginForm"
        :rules="rules"
        :model="loginForm"
        label-position="left"
        label-width="0px"
      >
        <div class="title">
          <h3>账号密码登录</h3>
        </div>
        <el-form-item prop="username">
          <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input
            type="password"
            v-model="loginForm.pwd"
            auto-complete="off"
            placeholder="密码"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            :loading="isLoding"
            style="width: 100%"
            type="primary"
            class="form-confirm"
            @click.native.prevent="handleSubmit"
          >登录</el-button>
        </el-form-item>
        <!-- 七天自动登录和忘记密码 -->
        <el-form-item>
          <el-checkbox
            style="float:left;"
            v-model="loginForm.autoLogin"
            :checked="loginForm.autoLogin"
          >七天自动登录</el-checkbox>
          <el-button type="text" class="forget" @click="$router.push('/password')">忘记密码?</el-button>
        </el-form-item>
      </el-form>
    </LoginHeader>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Provide } from "vue-property-decorator";
import { Getter, Action , State , Mutation } from 'vuex-class';
import LoginHeader from "@/views/Login/LoginHeader.vue";

@Component({
  components: {
    LoginHeader,
  },
})
export default class Login extends Vue {
  //存储user
  @Action("setUser") setUser:any;

  @Provide() isLoding:boolean = false;

  @Provide() loginForm: {
    username: String;
    pwd: String;
    autoLogin: boolean;
  } = {
    username: "",
    pwd: "",
    autoLogin: true,
  };

  @Provide() rules = {
    userName: [{ required: true, message: "请输入账号", trigger: "blur" }],
    password: [{ required: true, message: "请输入密码", trigger: "blur" }],
  };

  /**登录提交事件
   * @description: 
   * @param {type} 
   * @return: 
   */
  handleSubmit(): void {
    (this.$refs["loginForm"] as any).validate((validate: boolean) => {
      //如果校验通过
      if (validate) {
        console.log("校验通过");
        this.isLoding = true;
        //console.log("this.loginForm"+" username="+this.loginForm.username+";pwd="+this.loginForm.pwd);
        (this as any).$axios.post("/api/users/login", this.loginForm)
          .then((res: any) => {
            this.isLoding = false;
             console.log(res.data);
            // 存储token
            localStorage.setItem("tsToken", res.data.token);
            // 存储到vuex中
            this.setUser(res.data.token);

            // 登录成功 跳转 /
            this.$router.push("/");
          })
          .catch((err:string) => {
            console.log("失败"+err);
            this.isLoding = false;
          });
      } else {
         this.isLoding = false;
         console.log("校验失败");
      }
    });
  }
}
</script>


<style lang="scss" scope>
.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

i {
  font-size: 14px;
  margin-left: 8px;
}
.forget {
  float: right;
}
</style>