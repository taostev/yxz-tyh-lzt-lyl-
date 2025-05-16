/* eslint-disable vue/multi-word-component-names */
<template>
  <div>
    <h2>用户登录</h2>
    <form @submit.prevent="onLogin">
      <input v-model="form.username" placeholder="用户名/手机号/邮箱" required />
      <input v-model="form.password" type="password" placeholder="密码" required />
      <button type="submit">登录</button>
    </form>
    <p>{{ msg }}</p>
    <p>
      还没有账号？<a href="javascript:void(0)" @click="goRegister">注册用户</a>
    </p>
  </div>
</template>
<script>
import { login } from '@/api/user';
export default {
  name: 'UserLogin',
  data() {
    return {
      form: { username: '', password: '' },
      msg: ''
    }
  },
  methods: {
    onLogin() {
      login(this.form).then(res => {
        if (res.data.startsWith('登录成功')) {
          const token = res.data.split('token:')[1];
          localStorage.setItem('token', token);
          this.msg = '登录成功';
          this.$router.push('/dashboard');
        } else {
          this.msg = res.data;
        }
      });
    },
    goRegister() {
      this.$router.push('/register');
    }
  }
}
</script>